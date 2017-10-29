package com.example.anton2.productsearch;


import com.example.anton2.productsearch.Fetcher.ProductsDataFetcher;

/**
 * Created by Anton on 10/6/2017.
 */

public class DataFetcherFactory {

    private static DataFetcherFactory dataFactoryInstance;

    private DataFetcherFactory(){}

    public static DataFetcherFactory getDataFetcherFactory(){
        if (dataFactoryInstance == null)
        {
            dataFactoryInstance= new DataFetcherFactory();
        }
        return dataFactoryInstance;
    }

    public IDataFetcher getDataFetcher(String dataType)
    {
        IDataFetcher dataFetcher = null;
        if (dataType.equals("products"))
        {
            dataFetcher = new ProductsDataFetcher();
        }
        return dataFetcher;
    }
}
