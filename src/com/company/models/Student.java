package com.company.models;

import com.company.UniversityManager;

import java.util.ArrayList;

public class Student {
    private int studentID;
    private String name;
    private String universityName;
    private AcademicTranscript academicTranscript;
    private ArrayList<Course> favoriteCourses;

    public Student(String name, String universityName, int studentID) {
        this.name = name;
        this.universityName = universityName;
        this.studentID = studentID;
        this.academicTranscript = new AcademicTranscript(); // Initialize the academic transcript
        this.favoriteCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public AcademicTranscript getAcademicTranscript() {
        return academicTranscript;
    }

    public ArrayList<Course> getFavoriteCourses() {
        try {
            for (Course course : favoriteCourses) {
                System.out.println("The course name is " + course.getName() + ", and the department is " + course.getDepartment() + "\n");
            }
            if(favoriteCourses.size() == 0) {
                System.out.println("You have no favorite courses.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return favoriteCourses;
    }
    //Students can access a list of available courses, displaying course names and departments.
    public ArrayList<Course> getAvailableCourses(University university, String semester) {
        ArrayList<Course> availableCourses = new ArrayList<>();

        try {
            boolean semesterFound = false;

            // Check if the provided semester is valid
            for (Semester sem : university.getSemesters()) {
                if (sem.getName().equals(semester)) {
                    semesterFound = true;
                    break;
                }
            }

            if (!semesterFound) {
                System.out.println("The provided semester is not valid.");
                return availableCourses; // Return an empty list of available courses
            }

            boolean courseFound = false;
            for (Course course : university.getCourses()) {
                boolean courseAdded = false; // Flag to track if the course has been added
                for (Semester semester1 : course.getOfferedInSemesters()) {
                    if (semester1.getName().equals(semester)) {
                        courseFound = true; // At least one course found
                        if (!courseAdded) { // Check if the course hasn't been added yet
                            availableCourses.add(course);
                            courseAdded = true; // Set the flag to true to mark the course as added
                            System.out.println("The course name is " + course.getName() + ", and the department is " + course.getDepartment() + "\n");
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
    public ArrayList<Course> searchCourseByDepartment(University university, String department) {
        ArrayList<Course> matchingCourses = new ArrayList<>();
        try {
            for (Course course : university.getCourses()) {
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

    public ArrayList<Course> searchCourseByProfessor(University university, String professorName) {
        ArrayList<Course> matchingCourses = new ArrayList<>();
        for (Course course : university.getCourses()) {
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
    public void getCourseDetails(University university, String courseName) {
        try {
            for (Course course : university.getCourses()) {
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
    public void getCourseSchedule(University university, String semester) {
        try {
            for (Course course : university.getCourses()) {
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

    //"Students can mark courses as favorites.
    //Favorited courses should be easily accessible"
    public void addCourseToFavorites(University university, String courseName) {
        try {
            boolean courseExists = false;
            boolean isCourseAlreadyFavorited = false;

            for (Course course : favoriteCourses) {
                if (course.getName().equals(courseName)) {
                    isCourseAlreadyFavorited = true;
                }
            }

            if (!isCourseAlreadyFavorited) {
                for (Course course : university.getCourses()) {
                    if (course.getName().equals(courseName)) {
                        courseExists = true;
                        favoriteCourses.add(course);
                        System.out.println("Course added to favorites");
                    }
                }
            } else {
                if (courseExists)
                    System.out.println("Course is already favorited");
                else
                    System.out.println("Course does not exist");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void removeCourseFromFavorites(University university, String courseName) {
        try {
            boolean courseExists = false;
            boolean isCourseAlreadyFavorited = false;

            for (Course course : favoriteCourses) {
                if (course.getName().equals(courseName)) {
                    isCourseAlreadyFavorited = true;
                }
            }

            if (isCourseAlreadyFavorited) {
                for (Course course : university.getCourses()) {
                    if (course.getName().equals(courseName)) {
                        courseExists = true;
                        favoriteCourses.remove(course);
                        System.out.println("Course removed from favorites");
                    }
                }
            } else {
                if (courseExists)
                    System.out.println("Course is not favorited");
                else
                    System.out.println("Course does not exist");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void enrollInCourse(University university, String courseName, String semesterName) {
        try {
            for (Course course : university.getCourses()) {
                if (course.getName().equals(courseName)) {
                    for (Class class1 : course.getClasses()) {
                        if (class1.getSemester().getName().equals(semesterName)) {
                            if (!class1.isFull()) {
                                class1.enrollStudent(this);
                            } else {
                                System.out.println("The class is full");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void getTranscript(University university) {
        try {
            for (StudentGrade studentGrade : academicTranscript.getStudentGrades()) {
                if (studentGrade.isCompleted()) {
                    Class studentClass = studentGrade.getStudentClass();
                    Course course = studentClass.getCourse();
                    Semester semester = studentClass.getSemester();

                    System.out.println("The course name is " + course.getName() + ", the semester is " + semester.getName() + ", and the grade is " + studentGrade.getGrade() + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public double calculateGPAForStudentInSemester(String semesterName) {
        int totalCredits = 0;
        double totalGradePoints = 0.0;

        for (StudentGrade studentGrade : academicTranscript.getStudentGrades()) {
            if (studentGrade.getStudentClass().getSemester().getName().equals(semesterName) &&
                    studentGrade.isCompleted()) {

                int courseCredits = studentGrade.getStudentClass().getCourse().getNumOfCredits();
                totalCredits += courseCredits;

                double numericalGrade = studentGrade.getNumericalGrade();
                double courseGradePoints = numericalGrade * courseCredits;
                totalGradePoints += courseGradePoints;
            }
        }

        if (totalCredits > 0) {
            return totalGradePoints / totalCredits;
        } else {
            return 0.0;
        }
    }

    public void getGPA(University university) {
        try {
            for (StudentGrade grades : academicTranscript.getStudentGrades()) {
                if (grades.isCompleted()) {
                    System.out.println("The semester is " + grades.getStudentClass().getSemester().getName() +
                            ", and the GPA is " + calculateGPAForStudentInSemester(grades.getStudentClass().getSemester().getName()) + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void getHistoricalCourseSchedule(University university) {
        try {
            for (StudentGrade grades : academicTranscript.getStudentGrades()) {
                if (grades.isCompleted()) {
                    System.out.println("The course name is " + grades.getStudentClass().getCourse().getName() +
                            ", the semester is " + grades.getStudentClass().getSemester().getName() +
                            ", and the grade is " + grades.getGrade() + "\n");
                    System.out.println("The course schedule is " + grades.getStudentClass().getSchedule().getClassTimings().getDayOfWeek() +" " + grades.getStudentClass().getSchedule().getClassTimings().getTime() +
                            " and the location is " + grades.getStudentClass().getSchedule().getLocation() + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

}
