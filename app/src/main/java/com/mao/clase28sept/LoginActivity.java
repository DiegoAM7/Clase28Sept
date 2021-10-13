package com.mao.clase28sept;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mao.clase28sept.models.User;
import com.mao.clase28sept.sqlite.DbUser;

public class LoginActivity extends AppCompatActivity {
  EditText etxtEmailLogin, etxtPassLogin;
  Button btnLogin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    etxtEmailLogin = findViewById(R.id.etxtEmailLogin);
    etxtPassLogin = findViewById(R.id.etxtPassLogin);
    btnLogin = findViewById(R.id.btnLogin);

    btnLogin.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            String email = etxtEmailLogin.getText().toString();
            String pass = etxtPassLogin.getText().toString();

            DbUser userDB = new DbUser(LoginActivity.this);
            User user = userDB.login(email, pass);

            if (user != null) {
              Toast.makeText(LoginActivity.this, "Bienvenido " + user.getName(), Toast.LENGTH_LONG)
                  .show();

              Bundle data = new Bundle();
              data.putInt("id", user.getId());
              data.putString("name", user.getName());
              data.putString("lastname", user.getLastname());
              data.putString("email", user.getEmail());
              data.putString("password", user.getPassword());
              data.putString("type", user.getType());

              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
              intent.putExtras(data);
              startActivity(intent);
              finish();
            } else {
              Toast.makeText(LoginActivity.this, "Credenciales no válidas", Toast.LENGTH_LONG)
                  .show();
            }
          }
        });
  }
}
