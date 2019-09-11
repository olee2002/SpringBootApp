package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class User {
    private final Integer id;
    private final String username;
    private final String password;

    public User(@JsonProperty("id") Integer id,
                @JsonProperty("username") String username,
                @JsonProperty("password") String password){
        this.id= id;
        this.username = username;
        this.password = password;
    }

    public Integer getId(){
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}