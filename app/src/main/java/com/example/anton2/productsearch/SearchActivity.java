package com.example.anton2.productsearch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    //@BindView(R.id.searchbutton)
    private Button btnSearchButton;
    //@BindView(R.id.productsearchrdittext)
    private EditText etSearchField;
    //@BindView(R.id.recyclerviewlist)
    private RecyclerView rvSearchResults;
    private DataFetcherFactory dataFetcherFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Product Search");
        dataFetcherFactory = DataFetcherFactory.getDataFetcherFactory();
        bindUI();
    }

    private void bindUI() {

        btnSearchButton = (Button) findViewById(R.id.searchbutton);
        rvSearchResults = (RecyclerView) findViewById(R.id.recyclerviewlist);
        etSearchField = (EditText) findViewById(R.id.productsearchrdittext);
        Button btnGrnd = (Button) findViewById(R.id.gridbtn);
        Button btnList = (Button) findViewById(R.id.linearbtn);
        setListeners(btnGrnd, btnList);
    }

    private void setListeners(Button btnGrnd, Button btnList) {
        btnSearchButton.setOnClickListener(this);
        btnGrnd.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String query = etSearchField.getText().toString();

        switch (view.getId()) {
            case R.id.searchbutton:
                handleOnClickSearch(query);
                break;
            case R.id.gridbtn:
                changeToGridView();
                break;

            case R.id.linearbtn:
                changeToLinearView();
                break;
        }
    }

    private void changeToLinearView() {
        rvSearchResults.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
    }

    private void changeToGridView() {
        rvSearchResults.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    private void handleOnClickSearch(final String query) {
        new AsyncTask<Void, Void, List<Product>>() {
            @Override
            protected void onPostExecute(List<Product> products) {
                super.onPostExecute(products);
                initializeRvAdapter(products);
            }

            @Override
            protected List<Product> doInBackground(Void... voids) {
                return dataFetcherFactory.getDataFetcher("products").fetchData(query);
            }
        }.execute();
    }

    private void initializeRvAdapter(List<Product> products) {
        SearchRVAdapter adapter = new SearchRVAdapter(products);
        rvSearchResults.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvSearchResults.setLayoutManager(layoutManager);
    }
}

