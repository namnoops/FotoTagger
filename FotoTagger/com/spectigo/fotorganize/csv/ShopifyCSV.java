package com.spectigo.fotorganize.csv;

import java.io.IOException;
import java.util.ArrayList;

public class ShopifyCSV extends OutputCSV {
    private static final String[] STRINGS_TYPE_13 = new String[]{"\"13x17cm Glossy\"", "\"13x17cm Matte\"", "\"20x27cm Glossy\"", "\"20x27cm Matte\"", "\"30x40cm Poster\""};
    private static final String[] STRINGS_TYPE_15 = new String[]{"\"13x19cm Glossy\"", "\"13x19cm Matte\"", "\"20x30cm Glossy\"", "\"20x30cm Matte\"", "\"30x45cm Poster\""};
    private static final String[] STRINGS_SKU = new String[]{"MP", "MM", "LP", "LM", "P"};
    private static final double[] PRINT_DELTAS = new double[]{0.5, 0.5, 2.0, 2.0, 5.0};

	@Override
	public String writeTitle() throws IOException {
		return "Handle,Title,\"Body (HTML)\",Vendor,Type,Tags,Published,\"Option1 Name\",\"Option1 Value\",\"Option2 Name\",\"Option2 Value\",\"Option3 Name\",\"Option3 Value\",\"Variant SKU\",\"Variant Grams\",\"Variant Inventory Tracker\",\"Variant Inventory Qty\",\"Variant Inventory Policy\",\"Variant Fulfillment Service\",\"Variant Price\",\"Variant Compare At Price\",\"Variant Requires Shipping\",\"Variant Taxable\",\"Variant Barcode\",\"Image Src\",\"Image Alt Text\",\"Gift Card\",\"Google Shopping / MPN\",\"Google Shopping / Age Group\",\"Google Shopping / Gender\",\"Google Shopping / Google Product Category\",\"SEO Title\",\"SEO Description\",\"Google Shopping / AdWords Grouping\",\"Google Shopping / AdWords Labels\",\"Google Shopping / Condition\",\"Google Shopping / Custom Product\",\"Google Shopping / Custom Label 0\",\"Google Shopping / Custom Label 1\",\"Google Shopping / Custom Label 2\",\"Google Shopping / Custom Label 3\",\"Google Shopping / Custom Label 4\",\"Variant Image\",\"Variant Weight Unit\"\n";
	}
    
    public ArrayList<String> write(String imageURL, String productName, String productIndex, String category, String description, String photographer, String aspectRatio, String price, String tags) throws IOException {
        ArrayList<String> retVal = new ArrayList<String>();
    	
    	double aspect = Double.parseDouble(aspectRatio);
        String[] typeStrings = aspect == 1.5 ? STRINGS_TYPE_15 : STRINGS_TYPE_13;
        String handle = String.valueOf(productName) + "-" + productIndex;
        String title = "\"" + productName + " " + productIndex + "\"";
        String html = "\"" + description + "\"";
        String productType = "\"Digital download\"";
        String variantSKU = String.valueOf(productName) + "_" + productIndex;
        double basePrice = Double.parseDouble(price);
        if (category.contains(",")) {
            category = "\"" + category + "\"";
        }
        if (photographer.contains(",")) {
            photographer = "\"" + photographer + "\"";
        }
        if (tags.contains(",")) {
            tags = "\"" + tags + "\"";
        }
        retVal.add(String.valueOf(handle) + "," + title + "," + html + "," + photographer + "," + category + "," + tags + "," + "TRUE,Type," + productType + ",,,,," + variantSKU + "D,0,,100,deny,manual," + price + ",,FALSE,FALSE,," + imageURL + ",,FALSE,,,,,,,,,,,,,,,,,\n");
        int i = 0;
        while (i < 5) {
            String variantPrice = Double.toString(basePrice + PRINT_DELTAS[i]);
            String weight = i == 4 ? "101" : "0";
            retVal.add(String.valueOf(handle) + ",,,,," + ",,Type," + typeStrings[i] + ",,,,," + variantSKU + STRINGS_SKU[i] + "," + weight + ",,100,deny,manual," + variantPrice + ",,TRUE,FALSE,,,,,,,,,,,,,,,,,,,,,\n");
            ++i;
        }
        
        return retVal;
    }

}

