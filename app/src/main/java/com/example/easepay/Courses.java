package com.example.easepay;

public class Courses {
    private String name, email, password, repassword;

    public Courses() {
        // empty constructor
        // required for Firebase.
    }

    // Constructor for all variables.
    public Courses(String name, String email, String password,String repassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
    }

    // getter methods for all variables.
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // getter methods for all variables.
    public String getEmail() {
        return email;
    }

    // setter method for all variables.
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
