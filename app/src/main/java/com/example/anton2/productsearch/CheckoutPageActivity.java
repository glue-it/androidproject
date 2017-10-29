package com.example.anton2.productsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

/**
 * Created by Anton on 10/15/2017.
 */

public class CheckoutPageActivity extends AppCompatActivity {
    TextView txtProducturl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("checkout");
        setContentView(R.layout.checkout_activity);

        Bundle bundle = this.getIntent().getExtras();
        Product product = bundle.getParcelable("product");
        String productUrl = "https://shopyourway.com"+product.getProductUrlURL();
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(productUrl);
        myWebView.getSettings().setJavaScriptEnabled(true);

    }

}

