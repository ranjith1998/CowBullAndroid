package com.thejoker.cowbull;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thejoker.cowbull.user.MultiplayerGame2;
import com.thejoker.cowbull.user.MultiplayerGame2Service;

public class PlayActivity extends AppCompatActivity {
    private TextView playUserId;
    private TextView opponentUserId;
    private TextView userMoves;
    private TextView opponentMoves;
    private TextView gameId;
    private ListView listView;
    private TextView gameNumber;
    private int chances_took = 0;
    ArrayAdapter<String> stringArrayAdapter;
    MultiplayerGame2Service multiplayerGame2Service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        playUserId = (TextView) findViewById(R.id.play_userId);
        opponentUserId = (TextView) findViewById(R.id.opponent_userId);
        userMoves = (TextView) findViewById(R.id.user_moves);
        opponentMoves = (TextView) findViewById(R.id.opponent_moves);
        gameId = (TextView) findViewById(R.id.play_game_id);
        gameNumber = (TextView) findViewById(R.id.play_game_number);
        gameId.setVisibility(View.GONE);
        gameNumber.setVisibility(View.GONE);
        listView = (ListView) findViewById(R.id.user_entries_listView);
        stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.post_list);
        multiplayerGame2Service = new MultiplayerGame2Service(
                this, playUserId, opponentUserId, userMoves, opponentMoves, gameId, gameNumber);
        multiplayerGame2Service.startMultiplayerGame2();
        addListenerOnCheck();
    }

    private void addListenerOnCheck() {
        Button practice = (Button) findViewById(R.id.play_check);
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enteredNumber = (EditText) findViewById(R.id.play_number);
                String number = enteredNumber.getText().toString();
                check_game(number);
            }
        });
    }

    public  void check_game(String number) {
        multiplayerGame2Service.updateChancesTook(number, this);
    }

    public void updateGame(MultiplayerGame2 multiplayerGame2, String number){
        chances_took++;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        String user = dataBaseHelper.getUser();
        if(multiplayerGame2.isGameOn()) {
            Boolean flag = validate_number(number);
            if (flag) {
                int cows = this.get_cows(number), bulls = this.get_bulls(number);
                stringArrayAdapter.add(number + " | cow : " + cows + " | bull : " + bulls + "|");
                listView.setAdapter(stringArrayAdapter);
                Toast.makeText(this, "Bulls: " + bulls + ", Cows: " + cows, Toast.LENGTH_SHORT).show();
                if (bulls == number.length()) {
//                    Toast.makeText(this, "You WON!!!!", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(this, "You took " + chances_took + " chances to guess!!!", Toast.LENGTH_SHORT).show();
                    multiplayerGame2Service.endGame(number, this);
                }
            } else {
                show_number_not_correct_error_message();
            }
        }else{
            disable_editor();
            int moves = multiplayerGame2.getUser2Entries(), op_moves = multiplayerGame2.getUser1Entries();
            if(user.equals(multiplayerGame2.getUser1())){
                moves = multiplayerGame2.getUser1Entries();
                op_moves = multiplayerGame2.getUser1Entries();
            }
            if(multiplayerGame2.getWin() == 1 && multiplayerGame2.getUser1().equals(user) ||
                    multiplayerGame2.getWin() == 2 && multiplayerGame2.getUser2().equals(user)){
                Toast.makeText(this, "You WON!!!", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "You earned +" + (100 / moves) + " trophies!!!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "You LOST :|", Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "You lost -" + (50 / op_moves) + " trophies...", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void disable_editor(){
        Button practice = (Button) findViewById(R.id.play_check);
        practice.setVisibility(View.GONE);
    }
    public void enable_editor(){
//        EditText enteredNumber = (EditText) findViewById(R.id.entered_number);
//        enteredNumber.setFocusable(true);
//        enteredNumber.setFocusableInTouchMode(true); // user touches widget on phone with touch screen
//        enteredNumber.setClickable(true);
    }


    private int get_bulls(String number) {
        int result = 0;
        String game_number = gameNumber.getText().toString();
//        Toast.makeText(this, "game1: " + game_number + " | " + number, Toast.LENGTH_SHORT).show();
        for(int i = 0; i < number.length(); i++){
            if(number.charAt(i) == game_number.charAt(i)) result++;
        }
        return result;
    }

    private int get_cows(String number) {
        int result = 0;
        String game_number = gameNumber.getText().toString();
//        Toast.makeText(this, "game1: " + game_number + " | " + number, Toast.LENGTH_SHORT).show();
        for(int i = 0; i < number.length(); i++){
            for(int j = 0; j < number.length(); j++){
                if(i != j){
                    if(number.charAt(i) == game_number.charAt(j)) result++;
                }
            }
        }
        return result;
    }

    private void show_number_not_correct_error_message() {
        Toast.makeText(this,"number is not valid, please enter correct number", Toast.LENGTH_SHORT).show();
    }

    Boolean validate_number(String number){
        String game_number = gameNumber.getText().toString();
        if(number.length() > game_number.length()) return false;
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
