package com.example.androidfeedback.ui.login;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("userName")
    private String username ;

    @SerializedName("userID")
    private String userId ;

    public String getUserId() {
        return userId;
    }

    @SerializedName("roleManager")
    private String roleManager ;



    @SerializedName("password")
    private String password ;

    @SerializedName("role")
    private String role;

    @SerializedName("success")
    private boolean success ;

    @SerializedName("message")
    private String message ;

    public LoginModel(String username , String password , String role ){
        this.password = password ;
        this.role = role ;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getRoleManager() {
        return roleManager;
    }

}
