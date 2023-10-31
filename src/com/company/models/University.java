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

    //Students can access a list of available courses, displaying course names and departments.
    public ArrayList<Course> getAvailableCourses(String semester) {
        ArrayList<Course> availableCourses = new ArrayList<>();

        try {
            boolean semesterFound = false;

            // Check if the provided semester is valid
            for (Semester sem : getSemesters()) {
                if (sem.getName().equals(semester)) {
                    semesterFound = true;
                    break;
                }
            }

            if (!semesterFound) {
                return availableCourses; // Return an empty list of available courses
            }

            boolean courseFound = false;
            for (Course course : getCourses()) {
                boolean courseAdded = false; // Flag to track if the course has been added
                for (Semester semester1 : course.getOfferedInSemesters()) {
                    if (semester1.getName().equals(semester)) {
                        courseFound = true; // At least one course found
                        if (!courseAdded) { // Check if the course hasn't been added yet
                            availableCourses.add(course);
                            courseAdded = true; // Set the flag to true to mark the course as added
                        }
                    }
                }
            }

            if (!courseFound) {
                System.out.println("No courses are offered in the provided semester.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return availableCourses;
    }

    //"Students can search for courses using filters like department and professor.
    //The system displays matching courses."
    public ArrayList<Course> searchCourseByDepartment(String department) {
        ArrayList<Course> matchingCourses = new ArrayList<>();
        try {
            for (Course course : getCourses()) {
                if (course.getDepartment().equals(department)) {
                    matchingCourses.add(course);
                    System.out.println("The course name is " + course.getName() + ", and the department is " + course.getDepartment() + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return matchingCourses;
    }

    public ArrayList<Course> searchCourseByProfessor(String professorName) {
        ArrayList<Course> matchingCourses = new ArrayList<>();
        for (Course course : getCourses()) {
            for (Class aClass : course.getClasses()) {
                try {
                    if (aClass.getProfessor().getName().equals(professorName)) {
                        System.out.println("The course name is " + course.getName() + ", and the department is " + course.getDepartment() + "\n");
                        matchingCourses.add(course);
                        break;
                    }
                } catch (NullPointerException e) {
                    System.err.println("Error: Professor name is null for class in course " + course.getName());
                }
            }
        }

        return matchingCourses;
    }

    //Students can access a detailed page for each course, displaying course descriptions and schedules.
    public void getCourseDetails(String courseName) {
        try {
            for (Course course : getCourses()) {
                if (course.getName().equals(courseName)) {
                    System.out.println("The course name is " + course.getName() + ", and the department is " + course.getDepartment() + "\n");
                    System.out.println("The course description is " + course.getDescription() + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Students can access the course schedule for a specific semester, including class timings and locations.
    public void getCourseSchedule(String semester) {
        try {
            for (Course course : getCourses()) {
                for (Class class1 : course.getClasses()) {
                    if (class1.getSemester().getName().equals(semester)) {
                        System.out.println("The course name is " + course.getName() + ": \n");
                        System.out.println("The semester is " + class1.getSemester().getName() + ", the class timings are " +  class1.getSchedule().getClassTimings().getDayOfWeek() + " " + class1.getSchedule().getClassTimings().getTime() + ", and the location is " + class1.getSchedule().getLocation() + "\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
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

    //get course by name
    public Course getCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getName().equals(courseName)) {
                return course;
            }
        }
        return null; // Course not found
    }

    public int getNewStudentID(){
        return students.size() + 1;
    }
}
