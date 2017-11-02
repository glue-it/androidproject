package com.example.anton2.productsearch;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.anton2.productsearch.HistoryRepositoryImpl.HistoryRepository;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    //@BindView(R.id.searchbutton)
    private Button btnSearchButton;
    //@BindView(R.id.productsearchrdittext)
    private EditText etSearchField;
    //@BindView(R.id.recyclerviewlist)
    private RecyclerView rvSearchResults;
    private DataFetcherFactory dataFetcherFactory;
    private IHistoryRepository historyPresistanceService;
    private SearchRVAdapter adapter;
    private ViewType viewType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Product Search");
        dataFetcherFactory = DataFetcherFactory.getDataFetcherFactory();
        historyPresistanceService = new HistoryRepository(getApplicationContext());
        viewType = ViewType.LIST_VIEW;
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

        switch (view.getId()) {
            case R.id.searchbutton:
                String query = etSearchField.getText().toString();
                handleOnClickSearch(query);
                break;
            case R.id.linearbtn:
                viewType = ViewType.LIST_VIEW;
                updateRVView();
                break;

            case R.id.gridbtn:
                viewType = ViewType.GRID_VIEW;
                updateRVView();
                break;
        }
    }


    private void updateRVView() {
        RecyclerView.LayoutManager layout= null;
        switch (viewType) {
            case LIST_VIEW:
                layout = new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false);
                viewType = ViewType.LIST_VIEW;
                break;
            case GRID_VIEW:
                layout = new GridLayoutManager(getApplicationContext(), 2);
                viewType = ViewType.GRID_VIEW;
                break;
        }

            setRVLayout(layout);
    }

    private void setRVLayout(RecyclerView.LayoutManager layout) {
        adapter.updateViewType(viewType);
        rvSearchResults.setLayoutManager(layout);
        rvSearchResults.setAdapter(adapter);

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
        adapter = new SearchRVAdapter(products);
        updateRVView();
    }
}

