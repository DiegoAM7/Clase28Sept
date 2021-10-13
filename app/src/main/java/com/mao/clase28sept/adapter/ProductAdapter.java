package com.mao.clase28sept.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.clase28sept.R;
import com.mao.clase28sept.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
  ArrayList<Product> productArrayList;

  public ProductAdapter(ArrayList<Product> productArrayList) {
    this.productArrayList = productArrayList;
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtNameProduct, txtPrice, txtStock;
    ImageView imgProduct;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      txtNameProduct = itemView.findViewById(R.id.txtNameProduct);
      txtPrice = itemView.findViewById(R.id.txtPrice);
      txtStock = itemView.findViewById(R.id.txtStock);
      imgProduct = itemView.findViewById(R.id.imgProduct);
    }

    public void chargeData (Product product) {
      txtNameProduct.setText(product.getName());
      txtPrice.setText(String.valueOf(product.getPrice()));
      txtStock.setText(String.valueOf(product.getStock()));
      imgProduct.setImageResource(R.drawable.ic_launcher_background);
    }
  }

  @NonNull
  @Override
  public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
    holder.chargeData(productArrayList.get(position));
  }

  @Override
  public int getItemCount() {
    return productArrayList.size();
  }
}
