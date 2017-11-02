package com.example.anton2.productsearch.HistoryRepositoryImpl;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.anton2.productsearch.IHistoryRepository;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Anton on 10/29/2017.
 */

public class HistoryRepository implements IHistoryRepository{
    private SharedPreferences sharedPref;

    public  HistoryRepository (Context context){
        sharedPref = context.getSharedPreferences("SearchHistory", Context.MODE_PRIVATE);
    }
    @Override
    public void addSearchQuery(String query) {
        Set<String> searchesQueries = sharedPref.getStringSet("SearchHistory", null);
        if (searchesQueries==null)
        {
            searchesQueries = new LinkedHashSet<>();
        }
        searchesQueries.add(query);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putStringSet("SearchHistory",searchesQueries);
        editor.apply();
    }

    @Override
    public Set<String> getSearchHistory() {
        return null;
    }
}
