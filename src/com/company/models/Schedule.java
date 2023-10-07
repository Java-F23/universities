package com.company.models;

import java.util.List;

public class Schedule {
    public List<ClassTiming> getClassTimings() {
        return classTimings;
    }

    public String getLocation() {
        return location;
    }

    private List<ClassTiming> classTimings;
    private String location;
}
