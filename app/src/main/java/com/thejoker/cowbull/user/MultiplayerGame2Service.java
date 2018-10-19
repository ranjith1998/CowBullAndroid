package com.thejoker.cowbull.user;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejoker.cowbull.Constants.GlobalConstants;
import com.thejoker.cowbull.DataBaseHelper;
import com.thejoker.cowbull.PlayActivity;
import com.thejoker.cowbull.R;
import com.thejoker.cowbull.StartActivity;
import com.thejoker.cowbull.UserSignupResponse;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class MultiplayerGame2Service{
    private RequestQueue queue;
    private ObjectMapper objectMapper;
    private TextView playUserId;
    private TextView opponentUserId;
    private TextView userMoves;
    private TextView opponentMoves;
    private TextView gameId;
    private TextView gameNumber;
    Context context;

    public MultiplayerGame2Service(Context context, TextView playUserId, TextView opponentUserId,
                                   TextView userMoves, TextView opponentMoves, TextView gameId, TextView gameNumber){
        this.context = context;
        this.playUserId = playUserId;
        this.opponentMoves = opponentMoves;
        this.opponentUserId = opponentUserId;
        this.userMoves = userMoves;
        this.gameId = gameId;
        this.gameNumber = gameNumber;
        queue = Volley.newRequestQueue(context);
        objectMapper = new ObjectMapper();
    }

    public void startMultiplayerGame2() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String url = GlobalConstants.URL + GlobalConstants.START_MULTIPLAYER_GAME2 + dataBaseHelper.getUser();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        trystartMultiplayerGame2();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(stringRequest);
    }

    public void trystartMultiplayerGame2() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String url = GlobalConstants.URL + GlobalConstants.GET_MULTIPLAYER_GAME2 + dataBaseHelper.getUser();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        if(response.length() == 0){
                            Toast.makeText(context, "waiting....", Toast.LENGTH_SHORT).show();
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e) {

                            }
                            trystartMultiplayerGame2();
                        }else{
                            Toast.makeText(context, "found!!!", Toast.LENGTH_SHORT).show();
                            try {
                                MultiplayerGame2 multiplayerGame2 = objectMapper.readValue(response, MultiplayerGame2.class);
                                initMultiplayerGame2(multiplayerGame2);

                            } catch (Exception e) {
                                Toast.makeText(context, "failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(stringRequest);
    }

    private void initMultiplayerGame2(MultiplayerGame2 multiplayerGame2){
        int p = 1;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String userId = dataBaseHelper.getUser();
        if(multiplayerGame2.getUser2().equals(userId)) p = 2;
        gameId.setText("" + multiplayerGame2.getGameId());
        gameNumber.setText("" + multiplayerGame2.getNumber());
        if(p == 1){
            this.playUserId.setText(multiplayerGame2.getUser1());
            this.opponentUserId.setText(multiplayerGame2.getUser2());
            this.userMoves.setText("" + multiplayerGame2.getUser1Entries());
            this.opponentMoves.setText("" + multiplayerGame2.getUser2Entries());
        }else{
            this.playUserId.setText(multiplayerGame2.getUser2());
            this.opponentUserId.setText(multiplayerGame2.getUser1());
            this.userMoves.setText("" + multiplayerGame2.getUser2Entries());
            this.opponentMoves.setText("" + multiplayerGame2.getUser1Entries());
        }
    }

    public void updateChancesTook(final String number, final PlayActivity playActivity) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String gameId = this.gameId.getText().toString();
        String playerId = dataBaseHelper.getUser();
        String url = GlobalConstants.URL + GlobalConstants.UPDATE_CHANCES_TOOK + playerId + "/" + gameId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        if(response.length() == 0){
                            Toast.makeText(context, "waiting....", Toast.LENGTH_SHORT).show();
                        }else{
                            try {
                                MultiplayerGame2 multiplayerGame2 = objectMapper.readValue(response, MultiplayerGame2.class);
                                initMultiplayerGame2(multiplayerGame2);
                                playActivity.updateGame(multiplayerGame2, number);

                            } catch (Exception e) {
                                Toast.makeText(context, "failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(stringRequest);
    }

    public void endGame(final String number, final PlayActivity playActivity) {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        String gameId = this.gameId.getText().toString();
        String playerId = dataBaseHelper.getUser();
        String url = GlobalConstants.URL + GlobalConstants.END_GAME + playerId + "/" + gameId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        if(response.length() == 0){
//                            Toast.makeText(context, "waiting....", Toast.LENGTH_SHORT).show();
                        }else{
                            try {
                                MultiplayerGame2 multiplayerGame2 = objectMapper.readValue(response, MultiplayerGame2.class);
                                playActivity.updateGame(multiplayerGame2, number);

                            } catch (Exception e) {
                                Toast.makeText(context, "failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(stringRequest);
    }
}
