package com.thejoker.cowbull;


public class UserSignupResponse {
    public enum Status {
        INVALID_PASSWORD,
        INVALID_USER_ID,
        INVALID_NAME,
        SUCCESS,
        USER_EXISTS
    }
    Status status;

    public UserSignupResponse() {
    }

    public UserSignupResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
