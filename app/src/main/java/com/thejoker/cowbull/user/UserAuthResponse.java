package com.thejoker.cowbull.user;

public class UserAuthResponse {
    public enum Status {
        INVALID_PASSWORD,
        INVALID_USER_ID,
        SUCCESS
    }
    UserAuthResponse.Status status;

    public UserAuthResponse() {
    }

    public UserAuthResponse(UserAuthResponse.Status status) {
        this.status = status;
    }

    public UserAuthResponse.Status getStatus() {
        return status;
    }

    public void setStatus(UserAuthResponse.Status status) {
        this.status = status;
    }
}
