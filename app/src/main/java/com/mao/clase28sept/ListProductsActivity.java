package com.mao.clase28sept;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.mao.clase28sept.adapter.ProductAdapter;
import com.mao.clase28sept.models.Category;
import com.mao.clase28sept.models.Product;
import com.mao.clase28sept.sqlite.DbCategory;
import com.mao.clase28sept.sqlite.DbProduct;

import java.util.ArrayList;

public class ListProductsActivity extends AppCompatActivity {
  Button btnFilter, btnNoFilter;
  RecyclerView recyclerView;
  Spinner spFilterProd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_products);

    btnFilter = findViewById(R.id.btnFilter);
    btnNoFilter = findViewById(R.id.btnNoFilter);
    recyclerView = findViewById(R.id.recyclerProducts);
    spFilterProd = findViewById(R.id.spFilterProd);

    ArrayList<Category> categoryArrayList = new DbCategory(this).getCategories();
    ArrayAdapter<Category> categoryArrayAdapter =
        new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categoryArrayList);
    spFilterProd.setAdapter(categoryArrayAdapter);

    ArrayList<Product> productArrayList = new DbProduct(this).getProducts();

    btnFilter.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            ArrayList<Product> productArrayListFiltered = new ArrayList<>();
            Category category = (Category) spFilterProd.getSelectedItem();

            for (Product p : productArrayList) {
              if (p.getCategory().getId() == category.getId()) {
                productArrayListFiltered.add(p);
              }
            }

            ProductAdapter productsFiltered = new ProductAdapter(productArrayListFiltered);
            recyclerView.setAdapter(productsFiltered);
          }
        });

    btnNoFilter.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            ProductAdapter productAdapter = new ProductAdapter(productArrayList);
            recyclerView.setAdapter(productAdapter);
          }
        });

    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    ProductAdapter productAdapter = new ProductAdapter(productArrayList);
    recyclerView.setAdapter(productAdapter);
  }
}
