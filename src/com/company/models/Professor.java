package com.company.models;

import java.util.List;

public class Professor {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    private String name;
    private String department;
    private List<Course> coursesTaught;

    public Professor(String name, String department) {
        this.name = name;
        this.department = department;
    }
}
