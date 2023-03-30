package com.example.easepay;
public class Courses {

    // variables for storing our data.
    private String name, email, password;

    public Courses() {
        // empty constructor
        // required for Firebase.
    }

    // Constructor for all variables.
    public Courses(String courseName, String courseDescription, String courseDuration) {
        this.name = courseName;
        this.email = courseDescription;
        this.password = courseDuration;
    }

    // getter methods for all variables.
    public String getCourseName() {
        return name;
    }

    public void setCourseName(String courseName) {
        this.name = courseName;
    }

    public String getCourseDescription() {
        return email;
    }

    // setter method for all variables.
    public void setCourseDescription(String courseDescription) {
        this.email = courseDescription;
    }

    public String getCourseDuration() {
        return password;
    }

    public void setCourseDuration(String courseDuration) {
        this.password = courseDuration;
    }
}
