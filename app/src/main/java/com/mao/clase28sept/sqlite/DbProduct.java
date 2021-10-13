package com.mao.clase28sept.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.mao.clase28sept.models.Category;
import com.mao.clase28sept.models.Product;

import java.util.ArrayList;

public class DbProduct extends DbHelper {
  Context context;

  public DbProduct(@Nullable Context context) {
    super(context);
    this.context = context;
  }

  public long insertProduct(Product product) {
    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put("name", product.getName());
    values.put("trademark", product.getTrademark());
    values.put("model", product.getModel());
    values.put("stock", product.getStock());
    values.put("price", product.getPrice());
    values.put("category_id", product.getCategory().getId());

    return db.insert(DbHelper.TABLE_PRODUCTS, null, values);
  }

  public ArrayList<Product> getProducts() {
    ArrayList<Product> products = new ArrayList<>();
    Cursor cursor = null;
    Product product = null;
    DbCategory category = new DbCategory(context);

    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getReadableDatabase();

    cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_PRODUCTS, null);

    if (cursor.moveToFirst()) {
      do {
        Category cat = category.getCategory(cursor.getInt(6));

        product =
            new Product(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cat);

        products.add(product);
      } while (cursor.moveToNext());
    }

    return products;
  }

  public int updateProduct(
      int id, String name, String trademark, String model, int stock, double price, Category category) {
    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put("name", name);
    values.put("trademark", trademark);
    values.put("model", model);
    values.put("stock", stock);
    values.put("price", price);
    values.put("category", category.getId());

    return db.update(TABLE_PRODUCTS, values, "id = ?", new String[] {String.valueOf(id)});
  }

  public int deleteProduct(int id) {
    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getWritableDatabase();

    return db.delete(TABLE_PRODUCTS, "id = ?", new String[] {String.valueOf(id)});
  }
}
