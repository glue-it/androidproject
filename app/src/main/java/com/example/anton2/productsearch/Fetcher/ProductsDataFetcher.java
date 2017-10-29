package com.example.anton2.productsearch.Fetcher;

import android.content.Context;

import com.example.anton2.productsearch.Fetcher.DataReaderImpl.HttpDataReader;
import com.example.anton2.productsearch.Fetcher.parserimpl.ProductParser;
import com.example.anton2.productsearch.IDataFetcher;
import com.example.anton2.productsearch.Product;

import java.util.List;

/**
 * Created by Anton on 9/29/2017.
 */

public class ProductsDataFetcher implements IDataFetcher<Product> {
    private IDataReader dataLoader;
    private IParser parser;

    public ProductsDataFetcher()
    {
        this.dataLoader = new HttpDataReader();
        this.parser = new ProductParser();

    }
    @Override
    public List<Product> fetchData(String query) {

        return parser.parseData(dataLoader.readData(query));
    }

}
