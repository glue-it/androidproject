package com.example.anton2.productsearch.Fetcher.DataReaderImpl;

import android.content.Context;


import com.example.anton2.productsearch.Fetcher.IDataReader;
import com.example.anton2.productsearch.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Anton on 9/29/2017.
 */

public class DataReader implements IDataReader {

    private Context context;
    public DataReader(Context context) {
        this.context=context;
    }

    public String readData(String query) {
        String textData = null;

        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.search);


            byte[] b = new byte[inputStream.available()];

            inputStream.read(b);
            inputStream.close();
            textData = new String(b, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textData;

    }
}