package com.example.anton2.productsearch;

import java.util.Set;

/**
 * Created by Anton on 10/29/2017.
 */

public interface IHistoryRepository {

    void addSearchQuery(String query);
    Set<String> getSearchHistory();
}
