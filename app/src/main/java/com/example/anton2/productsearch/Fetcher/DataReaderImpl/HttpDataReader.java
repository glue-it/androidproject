package com.example.anton2.productsearch.Fetcher.DataReaderImpl;
import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.anton2.productsearch.Fetcher.IDataReader;

/**
 * Created by Anton on 10/1/2017.
 */

public class HttpDataReader  implements IDataReader {


    @Override
    public String readData(String query) {


        StringBuilder stringBuilder = new StringBuilder();

        try {
            final String  DATA_ENDPOINT = "https://platform.shopyourway.com/products/search" +
                    "?q=" + query+
                    "&page=1" +
                    "&token=0_20975_253402300799_1_" +
                    "39c0fd9abf524b96985688e78892212c05f34203a46ac36a4117f211b41c7f5d" +
                    "&hash=16eba7802b35f6cb1b03dbf6262d4db0808f437a14f070019a6fa98da45b3d90";
            BufferedReader reader  = null;
            URL url = new URL (DATA_ENDPOINT);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setReadTimeout(5*1000);
            connection.connect();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine())!=null)
            {
                stringBuilder.append(line+"\n|")  ;
            }
        }catch (Exception ex){
        }
        return stringBuilder.toString();
    }
}
