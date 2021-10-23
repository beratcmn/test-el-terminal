package com.example.el_terminali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    ServiceLayer serviceLayer = new ServiceLayer();
    Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        serviceLayer.context = this;
        btn_Login = findViewById(R.id.loginButton);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    serviceLayer.Login();
                    btn_Login.setEnabled(false);

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    //LogInMainActivity(); //TODO make it happen

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void LogInMainActivity() {
        if (serviceLayer.loggedIn) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        } else {
            LogInMainActivity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_loginSettings:
                startActivity(new Intent(LoginActivity.this, LoginSettingsActivity.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}