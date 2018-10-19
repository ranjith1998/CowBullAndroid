package com.thejoker.cowbull;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.thejoker.cowbull.user.User;

public class SignUpActivity extends AppCompatActivity {
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        dataBaseHelper.clearUser();
        addListenerOnSignUp();
    }

    private void addListenerOnSignUp() {
        final Button practice = (Button) findViewById(R.id.user_signup);
        final Intent intent = new Intent(this, StartActivity.class);
        final EditText userName = (EditText) findViewById(R.id.user_name);
        final EditText userId = (EditText) findViewById(R.id.user_id);
        final EditText password = (EditText) findViewById(R.id.user_password);
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(userId.getText().toString(), userName.getText().toString(), 0, password.getText().toString());
                practice.setVisibility(View.INVISIBLE);
                signUp(user, practice);
            }
        });
    }
    private boolean signUp(User user, Button prButton) {
        UserService userService = new UserService(this);
        return userService.userSignUp(user, prButton);
    }


}
