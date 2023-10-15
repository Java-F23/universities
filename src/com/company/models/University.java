package com.company.models;

import java.util.ArrayList;

public class University {
    private String name;
    private String location;
    private ArrayList<Class> classes;
    private ArrayList<Course> courses;
    private ArrayList<StudentCourseEnrollment> studentCourseEnrollments;
    private ArrayList<Professor> professors;
    private ArrayList<Student> students;
    private ArrayList<Administrator> administrators;
    private ArrayList<Semester> semesters;

    public University(String name, String location) {
        this.name = name;
        this.location = location;
        this.classes = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.studentCourseEnrollments = new ArrayList<>();
        this.professors = new ArrayList<>();
        this.students = new ArrayList<>();
        this.administrators = new ArrayList<>();
        this.semesters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Semester> getSemesters() {
        return semesters;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public ArrayList<StudentCourseEnrollment> getStudentCourseEnrollments() {
        return studentCourseEnrollments;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    // Find professor by name
    public Professor findProfessorByName(String professorName) {
        for (Professor professor : professors) {
            if (professor.getName().equals(professorName)) {
                return professor;
            }
        }
        return null; // Professor not found
    }

    public Professor findProfessorByID(int professorID) {
        for (Professor professor : professors) {
            if (professor.getProfessorID() == professorID) {
                return professor;
            }
        }
        return null; // Professor not found
    }

    // Add student to university
    public void addStudent(Student student) {
        if (student != null) {
            this.students.add(student);
        } else {
            System.out.println("Cannot add a null student to the university.");
        }
    }

    public void addCourse(Course course) {
        if (course != null) {
            this.courses.add(course);
        } else {
            System.out.println("Cannot add a null course to the university.");
        }
    }

    public Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getName().equals(courseName)) {
                return course;
            }
        }
        System.out.println("Course not found");
        return null; // Course not found
    }

    public Semester findSemesterByName(String semesterName) {
        for (Semester semester : semesters) {
            if (semester.getName().equals(semesterName)) {
                return semester;
            }
        }
        System.out.println("Semester not found");
        return null; // Semester not found
    }

    public void addClass(Class class1, Course course) {
        if (class1 == null) {
            System.out.println("Class is null");
            return;
        }
        if (course == null) {
            System.out.println("Course is null");
            return;
        }
        this.classes.add(class1);
        // Add class to the course
        course.addClass(class1);
    }

    public void addProfessor(Professor professor) {
        if (professor != null) {
            this.professors.add(professor);
        } else {
            System.out.println("Cannot add a null professor to the university.");
        }
    }

    public Student findStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null; // Student not found
    }

    public int getNewStudentID(){
        return students.size() + 1;
    }
}
