package com.mao.clase28sept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.mao.clase28sept.models.Product;
import com.mao.clase28sept.models.User;
import com.mao.clase28sept.sqlite.DbHelper;
import com.mao.clase28sept.sqlite.DbProduct;

public class MainActivity extends AppCompatActivity {
  Spinner spinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    User user = null;

    Bundle data = this.getIntent().getExtras();

    if (data != null) {
      int id = data.getInt("id");
      String name = data.getString("name");
      String lastname = data.getString("lastname");
      String email = data.getString("email");
      String password = data.getString("password");
      String type = data.getString("type");

      user = new User(id, name, lastname, email, password, type);
    }

    if (user == null) {
      Intent intent = new Intent(this, LoginActivity.class);
      startActivity(intent);
      finish();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    // condicional - switch

    switch (item.getItemId()){ // tomamos el id del item seleccionado
      case R.id.menuCategory:
        Intent intent = new Intent(this, FormCategoryActivity.class);
        startActivity(intent);
        return true;

      case R.id.menuProducts:
        Intent intent1 = new Intent(this, FormProductActivity.class);
        startActivity(intent1);
        return true;

      case R.id.menuListProducts:
        Intent intent2 = new Intent(this,ListProductsActivity.class);
        startActivity(intent2);
        return true;

      default:
        return super.onOptionsItemSelected(item);

    }
  }
}
