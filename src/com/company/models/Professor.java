package com.company.models;

import com.company.UniversityManager;

import java.util.ArrayList;

public class Professor {
    private String name;
    private int professorID;
    private String password;
    private String universityName;
    private String department;

    public int getProfessorID() {
        return professorID;
    }

    public String getName() {
        return name;
    }

    public Professor(String name, String department, String universityName, int professorID) {
        this.name = name;
        this.department = department;
        this.universityName = universityName;
        this.professorID = professorID;
    }

    //Professors can input grades for their students in the courses they teach.
    public void inputGrades(Student student, Class class1, String grade) {
        try {
            boolean studentFound = false;
            for (StudentGrade studentGrade : class1.getStudentCourseEnrollment().getStudentGrades()) {
                if (studentGrade.getStudent().getName().equals(student.getName())) {
                    studentGrade.setGrade(studentGrade.getStudent(), grade, class1, true);
                    student.getAcademicTranscript().addStudentGrade(studentGrade);
                    studentFound = true;
                }
            }
            if (!studentFound) {
                System.out.println("Student not found");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void takeAttendance(Student student, Class class1, Boolean attendance) {
        try {
            boolean studentFound = false;
            StudentCourseEnrollment enrollment = class1.getStudentCourseEnrollment();
            if (enrollment != null) {
                for (StudentGrade studentGrade : enrollment.getStudentGrades()) {
                    if (studentGrade.getStudent().getName().equals(student.getName())) {
                        if (attendance == true) {
                            studentGrade.addAttendance();
                        } else {
                            studentGrade.addAbsence();
                        }
                        studentFound = true;
                    }
                }
            }

            if (!studentFound) {
                System.out.println("Student not found");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
