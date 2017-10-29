package com.example.anton2.productsearch;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Anton on 9/29/2017.
 */

public class Product implements Parcelable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesctription() {
        return Desctription;
    }

    public void setDesctription(String desctription) {
        Desctription = desctription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getProductUrlURL() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    private String name;
    private String Desctription;
    private String price;
    private String imageURL;
    private String productUrl;

    public Product(String name, String desctription, String price, String imageURL, String productUrl) {
        this.name = name;
        this.Desctription = desctription;
        this.price = price;
        this.imageURL = imageURL;
        this.productUrl = productUrl;
    }

    protected Product(Parcel in) {
        name = in.readString();
        Desctription = in.readString();
        price = in.readString();
        imageURL = in.readString();
        productUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(Desctription);
        dest.writeString(price);
        dest.writeString(imageURL);
        dest.writeString(productUrl);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
