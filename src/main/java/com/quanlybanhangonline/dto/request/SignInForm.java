package com.quanlybanhangonline.dto.request;

public class SignInForm {
    private Long id;
    private String username;
    private String password;

    public SignInForm() {
    }

    public SignInForm(Long id, String username, String password) {
        this.id= id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
