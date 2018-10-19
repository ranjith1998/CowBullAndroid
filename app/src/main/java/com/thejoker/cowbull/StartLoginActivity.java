package com.thejoker.cowbull;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartLoginActivity extends AppCompatActivity {
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_login);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        if(dataBaseHelper.getUser() != null){
            Toast.makeText(context, dataBaseHelper.getUser(), Toast.LENGTH_SHORT).show();
            final Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        }
        addListenerOnSignIn();
        addListenerOnSignUp();
    }

    private void addListenerOnSignUp() {
        Button practice = (Button) findViewById(R.id.sign_up);
        final Intent intent = new Intent(this, SignUpActivity.class);
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void addListenerOnSignIn() {
        Button practice = (Button) findViewById(R.id.sign_in);
        final Intent intent = new Intent(this, LoginActivity.class);
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
