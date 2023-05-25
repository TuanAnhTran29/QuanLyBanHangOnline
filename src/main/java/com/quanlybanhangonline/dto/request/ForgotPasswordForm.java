package com.quanlybanhangonline.dto.request;

public class ForgotPasswordForm {
    private String username;
    private String password;
    private String re_enterPassword;

    public ForgotPasswordForm() {
    }

    public ForgotPasswordForm(String username, String password, String re_enterPassword) {
        this.username = username;
        this.password = password;
        this.re_enterPassword = re_enterPassword;
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

    public String getRe_enterPassword() {
        return re_enterPassword;
    }

    public void setRe_enterPassword(String re_enterPassword) {
        this.re_enterPassword = re_enterPassword;
    }
}
