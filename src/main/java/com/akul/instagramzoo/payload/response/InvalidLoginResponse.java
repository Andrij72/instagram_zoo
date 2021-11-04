package com.akul.instagramzoo.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {

    private String username;
    private String password;

    public InvalidLoginResponse() {
        this.username = "Invalide Username";
        this.password = "Invalide Password";
    }
}
