package com.thejoker.cowbull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LeadeboardActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leadeboard);
        setListView();
    }

    public void setListView(){

        listView = (ListView) findViewById(R.id.leaderboard_table);
        LeaderBoard leaderBoard = new LeaderBoard(this);
        leaderBoard.getLeaderBoard(listView);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String post = (String) listView.getItemAtPosition(i);
                        Toast.makeText(LeadeboardActivity.this, post, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
