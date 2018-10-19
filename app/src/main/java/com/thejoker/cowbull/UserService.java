package com.thejoker.cowbull;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
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
import com.thejoker.cowbull.user.MultiplayerGame2;
import com.thejoker.cowbull.user.User;
import com.thejoker.cowbull.user.UserAuthResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService{
    private RequestQueue queue;
    private ObjectMapper objectMapper;
    Context context;
    public UserService(Context context){
        this.context = context;
        queue = Volley.newRequestQueue(context);
        objectMapper = new ObjectMapper();
    }

    public boolean userSignUp(final User user, final Button prButton) {
        String url = GlobalConstants.URL + GlobalConstants.ADD_USER;
        try {
            String jsonInString = objectMapper.writeValueAsString(user);
            final JSONObject jsonObject = new JSONObject(jsonInString);
            JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            prButton.setVisibility(View.VISIBLE);
                            String result = response.toString();
                            try {
                                UserSignupResponse userSignupResponse = objectMapper.readValue(result, UserSignupResponse.class);
                                Toast.makeText(context, userSignupResponse.getStatus().toString(), Toast.LENGTH_SHORT).show();
                                if(userSignupResponse.getStatus() == UserSignupResponse.Status.SUCCESS){
                                    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                                    dataBaseHelper.insertUser(user.getUserId());
                                    final Intent intent = new Intent(context, StartActivity.class);
                                    context.startActivity(intent);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            queue.add(putRequest);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public void authUser(final String userId, final String password) {
        String url = GlobalConstants.URL + GlobalConstants.AUTH_USER + userId + "/" + password;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        if(response.length() == 0){
//                            Toast.makeText(context, "waiting....", Toast.LENGTH_SHORT).show();
                        }else{
                            try {
                                UserAuthResponse userAuthResponse = objectMapper.readValue(response, UserAuthResponse.class);
                                Toast.makeText(context, userAuthResponse.getStatus().toString(), Toast.LENGTH_SHORT).show();
                                if(userAuthResponse.getStatus() == UserAuthResponse.Status.SUCCESS){
                                    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
                                    dataBaseHelper.insertUser(userId);
                                    final Intent intent = new Intent(context, StartActivity.class);
                                    context.startActivity(intent);
                                }

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
