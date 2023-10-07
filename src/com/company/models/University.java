package com.company.models;
import java.util.List;

public class University {
    public String getName() {
        return name;
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

    private String name;
    private String location;
    private List<Department> departments;

    public List<StudentCourseEnrollment> getStudentCourseEnrollments() {
        return studentCourseEnrollments;
    }

    public void setStudentCourseEnrollments(List<StudentCourseEnrollment> studentCourseEnrollments) {
        this.studentCourseEnrollments = studentCourseEnrollments;
    }

    private List<StudentCourseEnrollment> studentCourseEnrollments;

    public List<Course> getCourses() {
        return courses;
    }

    private List<Course> courses;

    public List<Professor> getProfessors() {
        return professors;
    }

    private List<Professor> professors;

    public List<Student> getStudents() {
        return students;
    }

    private List<Student> students;

    //find professor by name
    public Professor findProfessorByName(String professorName){
        for (Professor professor : professors) {
            if (professor.getName().equals(professorName)) {
                return professor;
            }
        }
        return null;
    }

    //Add student to university
    public void addStudent(Student student){
        this.students.add(student);
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }

    public void addProfessor(Professor professor){
        this.professors.add(professor);
    }
}
