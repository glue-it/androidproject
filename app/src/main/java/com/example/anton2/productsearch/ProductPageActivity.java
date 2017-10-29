package com.example.anton2.productsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anton on 10/15/2017.
 */

public class ProductPageActivity extends AppCompatActivity  {
    ImageView imgProductImage;
    TextView txtView;
    Bundle bundle;
    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        Product product = getProduct();
        bindUI(product);

    }

    private void loadImageToImageView(Product product) {
        String image = product.getImageURL();
        new ImageLoadTask(image,imgProductImage).execute();
    }

    private Product getProduct() {
        bundle = this.getIntent().getExtras();
        return getIntent().getParcelableExtra("product");
    }

    private void bindUI(Product product) {
        setTitle(product.getName());
        imgProductImage = (ImageView) findViewById(R.id.productpageimage);
        txtView = (TextView) findViewById(R.id.productpagedescription);
        btnCheckout = (Button) findViewById(R.id.checkoutbutton);
        txtView.setText(product.getDesctription());
        loadImageToImageView(product);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButtonClick(view);
            }

            private void handleButtonClick(View view) {
                Intent intent = new Intent(ProductPageActivity.this, CheckoutPageActivity.class);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

}
