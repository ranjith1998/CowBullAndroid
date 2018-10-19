package com.thejoker.cowbull;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PracticeActivity extends AppCompatActivity {
    private int[] game_number;
    final Context context = this;
    int chances_took = 0;
    ListView listView;
    ArrayAdapter<String> stringArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        init_game_number();
        addListenerOnCheck();
        addListenerOnPlayAgain();
        listView = (ListView) findViewById(R.id.entered_numbers_listview);
        stringArrayAdapter = new ArrayAdapter<>(context, R.layout.post_list);
    }

    private void addListenerOnPlayAgain() {
        Button play_again = (Button) findViewById(R.id.play_again);
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
            }
        });
    }

    private void playAgain() {
        enable_editor();
        init_game_number();
        stringArrayAdapter.clear();
        listView.setAdapter(stringArrayAdapter);
    }

    private void init_game_number(){
        chances_took = 0;
        update_chances_took();
        this.game_number = numberGenerator();
        String gn = "";
        for(int i = 0; i < game_number.length; i++){
            gn = gn + game_number[i];
        }
        //Toast.makeText(context,"game_number: " + gn, Toast.LENGTH_SHORT).show();
        Toast.makeText(context,"new Game has been Loaded!!!", Toast.LENGTH_SHORT).show();
    }

    private void update_chances_took() {
        TextView ck = (TextView)findViewById(R.id.chances_took);
        String up = "" + chances_took;
        ck.setText(up);
    }

    public static int[] numberGenerator() {
        Random randy = new Random();
        int[] randArray = {10,10,10,10};

        for(int i=0;i<randArray.length;i++){
            int temp = randy.nextInt(8) + 1;
            while(temp == randArray[0] || temp == randArray[1] || temp == randArray[2] || temp == randArray[3]){
                temp=randy.nextInt(8) + 1;
            }
            randArray[i]=temp;
        }
        return randArray;
    }

    private void addListenerOnCheck() {
        Button practice = (Button) findViewById(R.id.check_button);
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enteredNumber = (EditText) findViewById(R.id.entered_number);
                String number = enteredNumber.getText().toString();
                /*if(number == 1234){
                    Toast.makeText(context,"four BULL!!!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"Not Correct...", Toast.LENGTH_LONG).show();
                }*/
                check_game(number);
            }
        });
    }

    private void check_game(String number) {
        chances_took++;
        update_chances_took();
        Boolean flag = validate_number(number);
        if(flag){
            int cows = get_cows(number), bulls = get_bulls(number);
            stringArrayAdapter.add(number + " | cow : " + cows + " | bull : " + bulls + "|");
            listView.setAdapter(stringArrayAdapter);
            Toast.makeText(context,"Bulls: " + bulls + ", Cows: " + cows, Toast.LENGTH_SHORT).show();
            if(bulls == number.length()){
                Toast.makeText(context,"You WON!!!!", Toast.LENGTH_SHORT).show();
                Toast.makeText(context,"You took " + chances_took + " chances to guess!!!", Toast.LENGTH_SHORT).show();
                disable_editor();
            }
        }else{
            show_number_not_correct_error_message();
        }
    }

    public void disable_editor(){
        EditText enteredNumber = (EditText) findViewById(R.id.entered_number);
        enteredNumber.setFocusable(false);
        enteredNumber.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
        enteredNumber.setClickable(false);
    }
    public void enable_editor(){
        EditText enteredNumber = (EditText) findViewById(R.id.entered_number);
        enteredNumber.setFocusable(true);
        enteredNumber.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
        enteredNumber.setClickable(true);
    }


    private int get_bulls(String number) {
        int result = 0;
        for(int i = 0; i < number.length(); i++){
            if(number.charAt(i) == '0' + game_number[i]) result++;
        }
        return result;
    }

    private int get_cows(String number) {
        int result = 0;
        for(int i = 0; i < number.length(); i++){
            for(int j = 0; j < number.length(); j++){
                if(i != j){
                    if(number.charAt(i) == '0' + game_number[j]) result++;
                }
            }
        }
        return result;
    }

    private void show_number_not_correct_error_message() {
        Toast.makeText(context,"number is not valid, please enter correct number", Toast.LENGTH_SHORT).show();
    }

    Boolean validate_number(String number){
        if(number.length() > game_number.length) return false;
        for(int i = 0; i < number.length(); i++){
            char c = number.charAt(i);
            if(c < '1' || c > '9'){
                return false;
            }
            for(int j = 0; j < i; j++){
                if(number.charAt(j) == c) return false;
            }
        }
        return true;
    }
}
