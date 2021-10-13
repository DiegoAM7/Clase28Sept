package com.mao.clase28sept.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.mao.clase28sept.models.Category;
import com.mao.clase28sept.models.Product;

import java.util.ArrayList;

public class DbCategory extends DbHelper {
  Context context;

  public DbCategory(@Nullable Context context) {
    super(context);
    this.context = context;
  }

  public long insertCategory(Category category) {
    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put("name", category.getName());

    return db.insert(DbHelper.TABLE_CATEGORIES, null, values);
  }

  public Category getCategory(int id) {
    Cursor cursor;

    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getReadableDatabase();

    cursor =
        db.rawQuery(
            "SELECT * FROM " + TABLE_CATEGORIES + " WHERE id = ?",
            new String[] {String.valueOf(id)});

    if (cursor.moveToFirst()) {
      return new Category(cursor.getInt(0), cursor.getString(1));
    }

    return null;
  }

  public ArrayList<Category> getCategories() {
    ArrayList<Category> categories = new ArrayList<>();
    Cursor cursor = null;
    Category category = null;

    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getReadableDatabase();

    cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_CATEGORIES, null);

    if (cursor.moveToFirst()) {
      do {
        category = new Category(cursor.getInt(0), cursor.getString(1));

        categories.add(category);
      } while (cursor.moveToNext());
    }

    return categories;
  }

  public int updateCategory(
      int id, String name, String trademark, String model, int stock, double price) {
    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put("name", name);

    return db.update(TABLE_CATEGORIES, values, "id = ?", new String[] {String.valueOf(id)});
  }

  public int deleteCategory(int id) {
    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getWritableDatabase();

    return db.delete(TABLE_CATEGORIES, "id = ?", new String[] {String.valueOf(id)});
  }
}
