package com.example.anton2.productsearch.Fetcher.parserimpl;

import com.example.anton2.productsearch.Fetcher.IDataReader;
import com.example.anton2.productsearch.Fetcher.IParser;
import com.example.anton2.productsearch.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by Anton on 9/29/2017.
 */

public class ProductParser implements IParser<Product> {
    public List<Product> parseData(String data){
        JSONObject jsonResponse;
        List<Product> products = new ArrayList<>();

        try {

            /****** Creates a new JSONObject with name/value mappings from the JSON string. ********/
            jsonResponse = new JSONObject(data);

            /***** Returns the value mapped by name if it exists and is a JSONArray. ***/
            /*******  Returns null otherwise.  *******/
            JSONArray jsonMainNode = jsonResponse.getJSONArray("products");

            /*********** Process each JSON Node ************/
            int lengthJsonArr = jsonMainNode.length();

            for(int i=0; i < lengthJsonArr; i++) {
                JSONObject jsonProductObject = jsonMainNode.getJSONObject(i);
                String productName = jsonProductObject.getString("name");
                String productDescription = jsonProductObject.getString("description");
                String productPrice = jsonProductObject.getString("price");
                String productImageURL = jsonProductObject.getString("imageUrl");
                String productUrl = jsonProductObject.getString("productUrl");

                Product product = new Product(productName,productDescription,productPrice
                        ,productImageURL,productUrl);
                products.add(product);


            }

        } catch (JSONException e) {

            e.printStackTrace();
        }
        return products;
    }

}
