package com.example.anton2.productsearch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Anton on 9/29/2017.
 */

public class SearchRVAdapter extends RecyclerView.Adapter<SearchRVAdapter.ViewHolder> {
    private List<Product> products;
    private ViewType viewTypeRequest= null;

    public SearchRVAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public SearchRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(viewType,parent,false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(SearchRVAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }


    public void updateViewType(ViewType viewType) {
        this.viewTypeRequest = viewType;
    }

    @Override
    public int getItemViewType(int position){
        int layout = R.layout.searchgrid;;
        switch (viewTypeRequest){
            case GRID_VIEW:
                layout = R.layout.searchgrid;
                break;
            case LIST_VIEW:
                layout = R.layout.searchrow;
                break;
        }
        return layout;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtProductName;
        TextView txtProductDescription;
        ImageView ivProductImage;

        public ViewHolder(View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.productname);
            txtProductDescription = itemView.findViewById(R.id.productdescription);
            ivProductImage = itemView.findViewById(R.id.productimage);
            itemView.setOnClickListener(this);
        }
        public void bind(Product product)
        {
            String image = product.getImageURL();

            txtProductName.setText(product.getName());
            txtProductDescription.setText(product.getDesctription());

            new ImageLoadTask(image,ivProductImage).execute();
        }

        @Override
        public void onClick(View view) {

            handleOnClick(view);
        }

        private void handleOnClick(View view) {
            Intent intent = new Intent(view.getContext(), ProductPageActivity.class);
            Bundle bundle = setBundle();

            intent.putExtras(bundle);
            view.getContext().startActivity(intent);
        }

        private Bundle setBundle ()
        {
            Bundle bundle = new Bundle();
            Product product = products.get(getAdapterPosition());

            bundle.putParcelable("product",product);
            return bundle;
        }
    }
}
