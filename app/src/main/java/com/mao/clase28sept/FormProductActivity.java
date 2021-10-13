package com.mao.clase28sept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mao.clase28sept.models.Category;
import com.mao.clase28sept.models.Product;
import com.mao.clase28sept.sqlite.DbCategory;
import com.mao.clase28sept.sqlite.DbProduct;

public class FormProductActivity extends AppCompatActivity {
  EditText etxtNameProd, etxtTrademarkProd, etxtModelProd, etxtStockProd, etxtPriceProd;
  Spinner spCatProd;
  Button btnProd;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form_product);

    etxtNameProd = findViewById(R.id.etxtNameProd);
    etxtTrademarkProd = findViewById(R.id.etxtTrademarkProd);
    etxtModelProd = findViewById(R.id.etxtModelProd);
    etxtStockProd = findViewById(R.id.etxtStockProd);
    etxtPriceProd = findViewById(R.id.etxtPriceProd);
    spCatProd = findViewById(R.id.spCatProd);
    btnProd = findViewById(R.id.btnProd);

    chargeSpinner();

    btnProd.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String name = etxtNameProd.getText().toString();
            String trademark = etxtTrademarkProd.getText().toString();
            String model = etxtModelProd.getText().toString();
            int stock = Integer.parseInt(etxtStockProd.getText().toString());
            int price = Integer.parseInt(etxtPriceProd.getText().toString());
            Category category = (Category) spCatProd.getSelectedItem();

            Product p = new Product(name, trademark, model, stock, price, category);

            DbProduct dbProduct = new DbProduct(getApplicationContext());
            long id = dbProduct.insertProduct(p);

            if (id >= 0) {
              Toast.makeText(FormProductActivity.this, name + " insertado", Toast.LENGTH_SHORT)
                  .show();
              etxtNameProd.setText("");
              etxtTrademarkProd.setText("");
              etxtModelProd.setText("");
              etxtStockProd.setText("");
              etxtPriceProd.setText("");
            } else {
              Toast.makeText(FormProductActivity.this, "Error al insertar", Toast.LENGTH_SHORT)
                  .show();
            }
          }
        });
  }

  public void chargeSpinner() {
    ArrayAdapter<Category> adapter =
        new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            new DbCategory(this).getCategories());
    spCatProd.setAdapter(adapter);
  }
}
