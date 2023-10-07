package com.company.models;

import com.company.UniversityManager;

public class Administrator {
    public String getUniversityName() {
        return universityName;
    }

    public void getUniversityName(String universityName) {
        this.universityName = universityName;
    }

    private String universityName;
    //Administrators can enter course details, including course name, department, and description.
    public void createCourse(String courseName, String department, String description){
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        Course adminCourse = new Course(courseName,  department,  description);
        //add course to list of courses in the adminstrator's university

        university.addCourse(adminCourse);
    }

    //Administrators can make changes to course details, including name, department, and description.
    public void updateCourse(String oldCourseName, String newCourseName, String department, String description){
        boolean foundCourse = false;
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        //loop over the courses in the university and change the course details
        for(Course course : university.getCourses()){
            if(course.getName().equals(oldCourseName)){
                course.setName(newCourseName);
                course.setDepartment(department);
                course.setDescription(description);
                foundCourse = true;
            }
        }

        if(!foundCourse){
            System.out.println("Course not found");
        }
    }

    //"Administrators can add and remove professors from the university's faculty list.
    //The faculty list should include professor names and departments."
    public void addProfessor(String professorName, String department){
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        Professor professor = new Professor(professorName, department);

        university.addProfessor(professor);
    }

    public void removeProfessor(String professorName) {
        boolean foundProfessor = false;
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        //loop over the professors in the university and remove the professor
        for (Professor professor : university.getProfessors()) {
            if (professor.getName().equals(professorName)) {
                university.getProfessors().remove(professor);
                foundProfessor = true;
            }
        }

        if (!foundProfessor) {
            System.out.println("Professor not found");
        }
    }

    //Administrators can track and manage student enrollments and class capacities.
    public void enrollStudent(String studentName, String courseName, String semester){
        boolean courseExists = false;
        boolean isStudentAlreadyEnrolled = false;
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        //loop over enrolledStudents
        for (Student student : university.getStudents()) {
            if (student.getName().equals(studentName)) {
                isStudentAlreadyEnrolled = true;
            }
        }

        if(!isStudentAlreadyEnrolled) {
            System.out.println("Student is not enrolled at this university");
        }
        else {
            //loop over students
        }
    }

}
