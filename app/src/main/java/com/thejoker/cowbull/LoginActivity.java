package com.thejoker.cowbull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText userId;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userId = (EditText) findViewById(R.id.login_user_id);
        password = (EditText) findViewById(R.id.user_password);
        login = (Button) findViewById(R.id.login_sign_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });
    }

    private void signin() {
        UserService userService = new UserService(this);
        userService.authUser(userId.getText().toString(), password.getText().toString());
    }
}
