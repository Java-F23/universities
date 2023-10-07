package com.company.models;

import java.util.List;

public class Course {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public List<Semester> getOfferedInSemesters() {
        return offeredInSemesters;
    }

    public void setOfferedInSemesters(List<Semester> offeredInSemesters) {
        this.offeredInSemesters = offeredInSemesters;
    }

    public void addOfferedInSemester(Semester semester) {
        offeredInSemesters.add(semester);
    }

    private String professor;
    //private List<Student> studentsEnrolled;
    private Schedule schedule;
    private String name;
    private String department;
    private String description;
    private List<Semester> offeredInSemesters;

    public Course(String courseName, String department, String description){
        this.name = courseName;
        this.department = department;
        this.description = description;
    }
}
