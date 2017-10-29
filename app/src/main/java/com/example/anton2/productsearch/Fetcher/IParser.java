package com.example.anton2.productsearch.Fetcher;

import java.util.List;

/**
 * Created by Anton on 9/30/2017.
 */

public interface IParser <T>{
    List<T> parseData (String data);
}
