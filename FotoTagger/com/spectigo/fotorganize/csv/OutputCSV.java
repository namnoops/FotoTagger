package com.spectigo.fotorganize.csv;

import java.io.IOException;
import java.util.ArrayList;

public abstract class OutputCSV {
    
    public abstract String writeTitle() throws IOException;

    public abstract ArrayList<String> write(String imageURL, String productName, String productIndex, String category, String description, String photographer, String aspectRatio, String price, String tags) throws IOException;
}

