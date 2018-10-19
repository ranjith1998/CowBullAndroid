package com.thejoker.cowbull.user;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersResponse {
    List<User> userList;

    public GetAllUsersResponse() {
        userList = new ArrayList<>();
    }

    public GetAllUsersResponse(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
