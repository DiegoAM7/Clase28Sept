package com.mao.clase28sept;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mao.clase28sept.models.Category;
import com.mao.clase28sept.sqlite.DbCategory;

public class FormCategoryActivity extends AppCompatActivity {
  EditText etxtNameCat;
  Button btnCat;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_form_category);

    etxtNameCat = findViewById(R.id.etxtNameCategory);
    btnCat = findViewById(R.id.btnCat);

    btnCat.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String name = etxtNameCat.getText().toString();
            Category c = new Category(name);

            DbCategory dbCategory = new DbCategory(getApplicationContext());
            long id = dbCategory.insertCategory(c);

            if (id > 0) {
              Toast.makeText(FormCategoryActivity.this, name + " creada", Toast.LENGTH_SHORT)
                  .show();
              etxtNameCat.setText("");
            } else {
              Toast.makeText(FormCategoryActivity.this, "Error al insertar", Toast.LENGTH_SHORT)
                  .show();
            }
          }
        });
  }
}
