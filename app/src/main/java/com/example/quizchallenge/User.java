package com.example.quizchallenge;

public class User {

    private String name;
    private String email;
    private String pass;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    private String profile;
    private String refercode;
    private long coins=10 ;
    public User() {
    }

    public User(String name, String email, String pass, String refercode) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.refercode = refercode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRefercode() {
        return refercode;
    }

    public void setRefercode(String refercode) {
        this.refercode = refercode;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }
}
