package com.thejoker.cowbull;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        addListenerOnPractice();
        addListenerOnLeaderboard();
        addListenerOnPlay();
        addListenerOnHelp();
    }

    public void addListenerOnHelp(){
        Button Help = (Button) findViewById(R.id.help);
        final Intent intent = new Intent(this, HelpActivity.class);
        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void addListenerOnPlay() {
        Button Play = (Button) findViewById(R.id.play);
        final Intent intent = new Intent(this, PlayActivity.class);
        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void addListenerOnLeaderboard() {
        Button leaderboard = (Button) findViewById(R.id.leaderboard);
        final Intent intent = new Intent(this, LeadeboardActivity.class);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    private void addListenerOnPractice() {
        Button practice = (Button) findViewById(R.id.practice_bt);
        final Intent intent = new Intent(this, PracticeActivity.class);
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}
