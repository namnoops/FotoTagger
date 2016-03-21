/*
 * Decompiled with CFR 0_110.
 */
package com.spectigo.fotorganize;

import com.spectigo.fotorganize.Photo;
import com.spectigo.fotorganize.csv.OutputCSV;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class ImportData {

	public static final int MAX_LINES = 200;
	
	// This determines whether or not to watermark and move the files to the 'output' directory
	// If false, only CSV output occurs
	private boolean csvOnly;
	
	private File dirLocation;
    private File outputDir;
    private File[] tagFiles;
    private OutputCSV csvWriter;
    private FileWriter fw;
    private ArrayList<Photo> photos = new ArrayList<Photo>();
    private Integer linesWritten;
    
    private Integer skipLines = 0;
    private Integer fileIndex = 0;
    
    public void initialize(File target, OutputCSV csvWriter, boolean csvOnly) throws Exception {
    	this.csvWriter = csvWriter;
    	this.csvOnly = csvOnly;
    	
    	this.dirLocation = target;
    	this.outputDir = new File(this.dirLocation + File.separator + "output");
    	
    	tagFiles = this.dirLocation.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("tags") && name.endsWith(".txt");
			}
		});
		
        if (this.tagFiles.length == 0) {
            throw new Exception("Directory doesn't have a 'tags.txt' file!");
        }
        
        this.outputDir.mkdir();
        openNewCSV();        
        this.parseTagsFile();
    }

	private void openNewCSV() throws IOException {
		
		this.linesWritten = 0;
		
		if (fw != null)
			fw.close();
		else
			this.linesWritten = skipLines;
		
		String indexString = String.format("%02d", this.fileIndex++);
		this.fw = new FileWriter(new File(String.valueOf(this.outputDir.getAbsolutePath()) + File.separator + "import" + indexString + ".csv"));
        this.fw.write(this.csvWriter.writeTitle());
        this.fw.flush();
	}

    private void parseTagsFile() {
        try {
        	for (File tagFile : this.tagFiles) {
        		List<String> fileData = Files.readAllLines(tagFile.toPath());
                for (String line : fileData) {
                    Photo temp = new Photo(this.dirLocation, line);
                    this.photos.add(temp);
                }	
    		}
            
        }
        catch (IOException fileData) {
            // empty catch block
        }
    }

    public int getNumberOfPhotos() {
        return this.photos.size();
    }

    public Photo getPhoto(int index) {
        return this.photos.get(index);
    }

    public void handlePhoto(int index) {
        Photo current = this.photos.get(index);
        this.writePhoto(current);
        if (!csvOnly) {
        	this.movePhoto(current);
        	this.addWatermark(current);
        }
        try {
            if (index == this.photos.size() - 1) {
                fw.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePhoto(Photo photo){
        try {
            String url = "https://dl.dropboxusercontent.com/u/44359968/SpectigoUploads/" + photo.getPhotoName();
            ArrayList<String> csvData = this.csvWriter.write(url, photo.getProductName(), photo.getProductIndex(), photo.getProductCategory(), photo.getDescription(), photo.getPhotographer(), photo.getAspectRatio(), photo.getPrice(), photo.getTags());
            for (String line : csvData) {
            	fw.write(line);
            	linesWritten++;
			}
            fw.flush();
            
            if (linesWritten >= MAX_LINES)
            	openNewCSV();
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void movePhoto(Photo photo) {
        String photoName = photo.getPhotoName();
        File currFile = new File(this.dirLocation + File.separator + photoName);
        currFile.renameTo(new File(this.outputDir + File.separator + photoName));
    }

    public void addWatermark(Photo photo) {
        String photoName = photo.getPhotoName();
        try {
            BufferedImage bimg = ImageIO.read(new File(this.outputDir + File.separator + photoName));
            int width = bimg.getWidth();
            int height = bimg.getHeight();
            String type = width > height ? "Landscape" : "Portrait";
            String[] params = new String[]{"C:\\Program Files (x86)\\IrfanView\\i_view32.exe", String.valueOf(this.outputDir.getAbsolutePath()) + File.separator + photoName, "/advancedbatch", "/ini=C:\\Users\\Tal Sela\\Desktop\\Watermark\\" + type + "\\", "/convert=" + photoName};
            Runtime.getRuntime().exec(params);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

