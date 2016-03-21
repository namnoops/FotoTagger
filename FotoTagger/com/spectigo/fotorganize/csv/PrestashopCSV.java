package com.spectigo.fotorganize.csv;

import java.io.IOException;
import java.util.ArrayList;

public class PrestashopCSV extends OutputCSV {

	@Override
	public String writeTitle() throws IOException {
		return "\"ID\",\"Active (0/1)\",Name,\"Categories (x,y,z...)\",\"Price tax excluded or Price tax included\","
				+ "\"Tax rules ID\",\"Wholesale price\",\"On sale (0/1)\",\"Discount amount\",\"Discount percent\","
				+ "\"Discount from (yyyy-mm-dd)\",\"Discount to (yyyy-mm-dd)\",\"Reference #\",\"Supplier reference #\","
				+ "Supplier,Manufacturer,EAN13,UPC,Ecotax,Width,Height,Depth,Weight,Quantity,\"Minimal quantity\","
				+ "Visibility,\"Additional shipping cost\",\"Unit for the unit price\",\"Unit price\",\"Short description\","
				+ "Description,\"Tags (x,y,z...)\",\"Meta title\",\"Meta keywords\",\"Meta description\",\"URL rewritten\","
				+ "\"Text when in stock\",\"Text when backorder allowed\",\"Available for order (0 = No, 1 = Yes)\","
				+ "\"Product availability date\",\"Product creation date\",\"Show price (0 = No, 1 = Yes)\","
				+ "\"Image URLs (x,y,z...)\",\"Delete existing images (0 = No, 1 = Yes)\","
				+ "\"Feature (Name:Value:Position:Customized)\",\"Available online only (0 = No, 1 = Yes)\",Condition,"
				+ "\"Customizable (0 = No, 1 = Yes)\",\"Uploadable files (0 = No, 1 = Yes)\",\"Text fields (0 = No, 1 = Yes)\","
				+ "\"Action when out of stock\",\"ID / Name of shop \",\"Advanced Stock Management \",\"Depends on stock \","
				+ "Warehouse\n";
	}

	@Override
	public ArrayList<String> write(String imageURL, String productName, String productIndex, String category, String description,
			String photographer, String aspectRatio, String price, String tags) throws IOException {

		ArrayList<String> retVal = new ArrayList<String>();
		
		String name = String.valueOf(productName) + "-" + productIndex;
		String html = "\"" + description + "\"";
		if (tags.contains(",")) {
            tags = "\"" + tags + "\"";
        }
		if (category.contains(",")) {
            category = "\"" + category + "\"";
        }
		
		// Remove parentheses - "Helsinki City Marathon (2015)"
		category = category.replaceAll("[()]", "");
		
		// Remove hashtags (not allowed)
		photographer = photographer.replaceAll("#", "");
		
		retVal.add(",1," + name + "," + category + "," + price + ",,,,,,,,,," + photographer + ",,,,,,,,,,,search,,,,," + html + "," + tags + ",,,,,,,1,,,1," + imageURL + ",,,1,,,,,,,,,\n");
		return retVal;
	}

}
