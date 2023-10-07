package com.company.models;

import java.util.List;

public class Semester {
    public String getName() {
        return name;
    }

    private String name;
    private List<Course> offeredCourses;

}
