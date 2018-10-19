package com.thejoker.cowbull;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thejoker.cowbull.Constants.GlobalConstants;
import com.thejoker.cowbull.user.GetAllUsersResponse;
import com.thejoker.cowbull.user.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class LeaderBoard{
    private RequestQueue queue;
    private ObjectMapper objectMapper;
    Context context;
    public LeaderBoard(Context context){
        this.context = context;
        queue = Volley.newRequestQueue(context);
        objectMapper = new ObjectMapper();
    }

    public void getLeaderBoard(final ListView listView) {
        String url = GlobalConstants.URL + GlobalConstants.GET_ALL_USERS;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                        try {
                            GetAllUsersResponse getAllUserResponse = objectMapper.readValue(response, GetAllUsersResponse.class);
                            Collections.sort(getAllUserResponse.getUserList(), new Comparator<User>() {
                                public int compare(User c1, User c2) {
                                    if (c1.getTrophies() > c2.getTrophies()) return -1;
                                    if (c1.getTrophies() < c2.getTrophies()) return 1;
                                    return 0;
                                }});
                            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(context, R.layout.post_list);
                            for(int i = 0; i < getAllUserResponse.getUserList().size(); i++){
                                User user = getAllUserResponse.getUserList().get(i);
                                stringArrayAdapter.add(user.getUserId() + "   " + user.getName() + "   " + user.getTrophies());
                                listView.setAdapter(stringArrayAdapter);
                            }

                        } catch (Exception e) {
                            Toast.makeText(context, "failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
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
