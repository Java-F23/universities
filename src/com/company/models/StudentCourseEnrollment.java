package com.company.models;

import java.util.List;

public class StudentCourseEnrollment {
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void setStudentsEnrolled(List<Student> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }

    public List<Student> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    private Semester semester;

    private List<Student> studentsEnrolled;

}
