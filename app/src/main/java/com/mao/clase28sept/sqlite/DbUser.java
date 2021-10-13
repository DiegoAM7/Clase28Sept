package com.mao.clase28sept.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.mao.clase28sept.models.User;

public class DbUser extends DbHelper {
  Context context;

  public DbUser(@Nullable Context context) {
    super(context);
    this.context = context;
  }

  public User login(String email, String password) {
    User user = null;
    Cursor cursor;

    DbHelper helper = new DbHelper(context);
    SQLiteDatabase db = helper.getReadableDatabase();

    cursor =
        db.rawQuery(
            "SELECT * FROM users WHERE email = ? and password = ?", new String[] {email, password});

    if (cursor.moveToFirst()) {
      user =
          new User(
              cursor.getInt(0),
              cursor.getString(1),
              cursor.getString(2),
              cursor.getString(3),
              cursor.getString(4),
              cursor.getString(5));
    }

    return user;
  }
}
