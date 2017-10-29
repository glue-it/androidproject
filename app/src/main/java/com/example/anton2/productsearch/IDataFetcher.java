package com.example.anton2.productsearch;


import java.util.List;

/**
 * Created by Anton on 9/29/2017.
 */

public interface IDataFetcher<T> {
    List<T> fetchData(String query);

}
