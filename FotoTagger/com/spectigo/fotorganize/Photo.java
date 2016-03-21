package com.spectigo.fotorganize;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

public class Photo {
    public static final int RESIZE_LE_ORIG = 3600;
    public static final int RESIZE_LE_WTHB = 800;
    public static final Scalr.Rotation[] ROTATION;
    private File photoFile;
    private BufferedImage photo;
    private String path;
    private boolean isProcessed = false;
    private boolean isDeleted = false;
    private String productName;
    private String productIndex;
    private String productCategory;
    private String description;
    private String photographer;
    private String price;
    private String tags;
    private String aspectRatio;

    static {
        Scalr.Rotation[] arrrotation = new Scalr.Rotation[9];
        arrrotation[3] = Scalr.Rotation.CW_180;
        arrrotation[6] = Scalr.Rotation.CW_90;
        arrrotation[8] = Scalr.Rotation.CW_270;
        ROTATION = arrrotation;
    }

    Photo(File parentDir, String photoData) {
        String[] data = photoData.split("\\|");
        String fileName = data[0];
        String productName = data[1];
        String productIndex = data[2];
        String category = data[3];
        String description = data[4];
        String photographer = data[5];
        String aspect = data[6];
        String price = data[7];
        String tags = data[8];
        this.process(productName, productIndex, category, description, photographer, price, tags);
        this.aspectRatio = aspect;
        this.path = String.valueOf(parentDir.getAbsolutePath()) + File.separator + fileName;
        this.photoFile = new File(this.path);
    }

    Photo(File file) throws IOException, ImageProcessingException {
        this.photoFile = file;
        this.path = file.getAbsolutePath();
        this.photo = ImageIO.read(this.photoFile);
        this.photo = Scalr.resize(this.photo, 800, new BufferedImageOp[0]);
        this.aspectRatio = String.valueOf(this.calcAspectRatio());
        int orientation = this.getPhotoOrientation();
        if (orientation == 3 || orientation == 6 || orientation == 8) {
            this.photo = Scalr.rotate(this.photo, ROTATION[orientation], new BufferedImageOp[0]);
        }
    }

    public BufferedImage getDisplayImage() throws IOException {
        return this.photo;
    }

    public String getPhotoName() {
        return this.path.substring(this.path.lastIndexOf("\\") + 1);
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public String getProductCategory() {
        return this.productCategory;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPhotographer() {
        return this.photographer;
    }

    public String getPrice() {
        return this.price;
    }

    public String getTags() {
        return this.tags;
    }

    public int getWidth() {
        return this.photo.getWidth();
    }

    public int getHeight() {
        return this.photo.getHeight();
    }

    private double calcAspectRatio() {
        double ratio = this.getWidth() > this.getHeight() ? (double)this.getWidth() / (double)this.getHeight() : (double)this.getHeight() / (double)this.getWidth();
        return (double)Math.round(ratio * 100.0) / 100.0;
    }

    public String getAspectRatio() {
        return this.aspectRatio;
    }

    public void process(String productName, String productIndex, String productCategory, String description, String photographer, String price, String tags) {
        this.isProcessed = true;
        this.isDeleted = false;
        this.productName = productName;
        this.productIndex = productIndex;
        this.productCategory = productCategory;
        this.description = description;
        this.photographer = photographer;
        this.price = price;
        this.tags = tags;
    }

    public boolean isProcessed() {
        return this.isProcessed;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void delete() {
        this.isDeleted = true;
    }

    private int getPhotoOrientation() throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(this.photoFile);
        Object directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
        int orientation = 1;
        try {
            orientation = ((ExifIFD0Directory)directory).getInt(274);
        }
        catch (MetadataException me) {
            me.printStackTrace();
        }
        return orientation;
    }
}

