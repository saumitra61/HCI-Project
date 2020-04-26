package com.example.musicplayer;

public class UserProfile {
    public String userName;
    public String userNumber;
    public String token;


    public UserProfile(){

    }

    public UserProfile(String userName, String userNumber, String token){
        this.userName= userName;
        this.userNumber= userNumber;
        this.token= token;
    }

    public  String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
