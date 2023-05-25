package com.quanlybanhangonline.dto.request;

import java.util.Set;

public class SignUpForm {
    private String name;
    private String address;
    private String phone;
    private String username;
    private String email;
    private String password;
    private String re_enterPassword;
    private Set<String> roles;

    public SignUpForm() {
    }

    public SignUpForm(String name, String address, String phone, String username, String email, String password, String re_enterPassword, Set<String> roles) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.email = email;
        this.password = password;
        this.re_enterPassword = re_enterPassword;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
