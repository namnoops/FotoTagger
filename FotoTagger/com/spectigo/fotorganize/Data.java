/*
 * Decompiled with CFR 0_110.
 */
package com.spectigo.fotorganize;

import com.drew.imaging.ImageProcessingException;
import com.spectigo.fotorganize.Output;
import com.spectigo.fotorganize.Photo;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Data {
    public static final int BUFFER_SIZE = 15;
    public static final int BATCH_SIZE = 5;
    public static final int CURR_PACE_BASED_OPR = 10;
    static final FilenameFilter PHOTO_FILTER = new FilenameFilter(){

        @Override
        public boolean accept(File directory, String fileName) {
            if (!fileName.toLowerCase().endsWith(".jpg") && !fileName.toLowerCase().endsWith(".jpeg")) {
                return false;
            }
            return true;
        }
    };
    private File dirLocation;
    private String[] fileList;
    private int nextFile = 0;
    private int opsTotal = 0;
    private int opsCurrBatch = 0;
    public Output output = null;
    private long startTime;
    private int execIndex = 0;
    private boolean execCurrPaceValid = false;
    private long[] executionTimeStamps = new long[10];
    private Photo currentPhoto = null;
    private Photo previousPhoto = null;
    private Photo nextPhoto = null;
    private BlockingQueue<Photo> photoBuffer = new LinkedBlockingQueue<Photo>();
    private BlockingQueue<String> preBuffer = new LinkedBlockingQueue<String>();

    public void initialize(File directory) {
        this.dirLocation = directory;
        this.fileList = this.dirLocation.list(PHOTO_FILTER);
        int i = 0;
        while (i < 15) {
            this.loadPhotoToBuffer();
            ++i;
        }
        this.startTime = new Date().getTime();
        Thread bufferer = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    String path;
                    while ((path = (String)Data.this.preBuffer.take()) != null) {
                        Photo rp = new Photo(new File(path));
                        Data.this.photoBuffer.put(rp);
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
                catch (ImageProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        bufferer.start();
    }

    public boolean processPhoto(String productName, String productIndex, String productCategory, String description, String photographer, String price, String tags) {
        Photo outOfPipe = this.previousPhoto;
        this.previousPhoto = this.currentPhoto;
        this.currentPhoto.process(productName, productIndex, productCategory, description, photographer, price, tags);
        boolean done = this.increaseCounters();
        if (outOfPipe != null) {
            this.commitPhoto(outOfPipe);
        }
        if (done) {
            this.commitPhoto(this.currentPhoto);
        }
        return done;
    }

    public boolean deletePhoto() {
        Photo outOfPipe = this.previousPhoto;
        this.currentPhoto.delete();
        this.previousPhoto = this.currentPhoto;
        boolean done = this.increaseCounters();
        if (outOfPipe != null) {
            this.commitPhoto(outOfPipe);
        }
        if (done) {
            this.commitPhoto(this.currentPhoto);
        }
        return done;
    }

    private void commitPhoto(Photo photo) {
        try {
            if (this.output == null) {
                this.output = new Output(this.dirLocation);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if (photo.isDeleted()) {
            System.out.println("Deleting: " + photo.getPhotoName() + " [" + photo.getTags() + "]");
        } else {
            System.out.println("Saving: " + photo.getPhotoName() + " [" + photo.getTags() + "]");
            try {
                this.output.write(photo.getPhotoName(), photo.getProductName(), String.valueOf(photo.getProductIndex()), photo.getProductCategory(), photo.getDescription(), photo.getPhotographer(), String.valueOf(photo.getAspectRatio()), photo.getPrice(), photo.getTags());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void finalize() {
        try {
            this.output.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Photo undo() {
        --this.opsTotal;
        --this.opsCurrBatch;
        if (--this.execIndex < 0) {
            this.execIndex = this.executionTimeStamps.length - 1;
        }
        this.nextPhoto = this.currentPhoto;
        this.currentPhoto = this.previousPhoto;
        this.previousPhoto = null;
        return this.currentPhoto;
    }

    private boolean increaseCounters() {
        this.insertExec();
        if (++this.opsCurrBatch > 5) {
            this.opsCurrBatch = 1;
        }
        if (++this.opsTotal >= this.fileList.length) {
            return true;
        }
        return false;
    }

    public Photo getPhoto() {
        if (this.nextPhoto != null) {
            this.currentPhoto = this.nextPhoto;
            this.nextPhoto = null;
            return this.currentPhoto;
        }
        this.loadPhotoToBuffer();
        try {
            this.currentPhoto = this.photoBuffer.take();
            return this.currentPhoto;
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadPhotoToBuffer() {
        if (this.nextFile >= this.fileList.length) {
            return;
        }
        String photoPath = String.valueOf(this.dirLocation.toString()) + File.separator + this.fileList[this.nextFile++];
        this.preBuffer.add(photoPath);
    }

    private void insertExec() {
        this.executionTimeStamps[this.execIndex++] = new Date().getTime();
        if (this.execIndex >= this.executionTimeStamps.length) {
            this.execIndex = 0;
            this.execCurrPaceValid = true;
        }
    }

    public String getCurrentPace() {
        if (this.execCurrPaceValid) {
            int prevIdx = this.execIndex == 0 ? this.executionTimeStamps.length - 1 : this.execIndex - 1;
            int pace = 3600000 / ((int)(this.executionTimeStamps[prevIdx] - this.executionTimeStamps[this.execIndex]) / 10);
            return String.valueOf(pace);
        }
        return "N/A";
    }

    public String getAvgPace() {
        long now = new Date().getTime();
        if (this.opsTotal > 0) {
            int pace = 3600000 / ((int)(now - this.startTime) / this.opsTotal);
            return String.valueOf(pace);
        }
        return "N/A";
    }

    public Photo getCurrentPhoto() {
        return this.currentPhoto;
    }

    public int getTotalNumberOfPhotos() {
        return this.fileList.length;
    }

    public int getNumberOfCompletedPhotos() {
        return this.opsTotal;
    }

    public int getNumberOfCompletedPhotosInBatch() {
        return this.opsCurrBatch;
    }

}

