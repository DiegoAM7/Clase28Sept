package com.mao.clase28sept.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
  public static final String DB_NAME = "tienda";
  public static final int DB_VERSION = 1;
  public static final String TABLE_USERS = "users";
  public static final String TABLE_CATEGORIES = "categories";
  public static final String TABLE_PRODUCTS = "products";
  public static final String TABLE_BUYS = "buys";
  public static final String TABLE_BUYS_PRODUCTS = "buys_has_products";

  public DbHelper(@Nullable Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    // *** Crear Tablas ***

    // Usuarios
    sqLiteDatabase.execSQL(
        "CREATE TABLE "
            + TABLE_USERS
            + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,"
            + "lastname TEXT NOT NULL,"
            + "email TEXT NOT NULL,"
            + "password TEXT NOT NULL, "
            + "type TEXT NOT NULL)");

    // Categorias
    sqLiteDatabase.execSQL(
        "CREATE TABLE "
            + TABLE_CATEGORIES
            + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL)");

    // Productos
    sqLiteDatabase.execSQL(
        "CREATE TABLE "
            + TABLE_PRODUCTS
            + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,"
            + "trademark TEXT NOT NULL,"
            + "model TEXT NOT NULL,"
            + "stock INTEGER NOT NULL,"
            + "price INTEGER NOT NULL,"
            + "category_id INTEGER NOT NULL,"
            + "FOREIGN KEY (category_id) REFERENCES categories(id))");

    // Compras
    sqLiteDatabase.execSQL(
        "CREATE TABLE "
            + TABLE_BUYS
            + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "date INTEGER NOT NULL)");

    // Compras y Productos
    sqLiteDatabase.execSQL(
        "CREATE TABLE "
            + TABLE_BUYS_PRODUCTS
            + " ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "buy_id INTEGER NOT NULL,"
            + "product_id INTEGER NOT NULL,"
            + "quantity INTEGER NOT NULL,"
            + "FOREIGN KEY (buy_id) REFERENCES buys(id),"
            + "FOREIGN KEY (product_id) REFERENCES products(id))");
  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    // Se ejecuta cuando cambia la version de la base de datos.
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BUYS);
    sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_BUYS_PRODUCTS);
    onCreate(sqLiteDatabase);
  }
}
