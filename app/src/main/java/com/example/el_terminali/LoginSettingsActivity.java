package com.example.el_terminali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginSettingsActivity extends AppCompatActivity {
    EditText editText_IP;
    EditText editText_UserName;
    EditText editText_Password;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editText_IP = findViewById(R.id.ipTextField);
        editText_UserName = findViewById(R.id.usernameTextField);
        editText_Password = findViewById(R.id.passwordTextField);
        saveButton = findViewById(R.id.saveLoginInfoButton);

        final SharedPreferences preferences = this.getSharedPreferences("LoginParams", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();

        editText_IP.setText(preferences.getString("URL", ""));
        editText_UserName.setText(preferences.getString("UserName", ""));
        editText_Password.setText(preferences.getString("Password", ""));


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("URL", editText_IP.getText().toString());
                editor.putString("UserName", editText_UserName.getText().toString());
                editor.putString("Password", editText_Password.getText().toString());
                editor.commit();
                finish();
            }
        });
    }
}