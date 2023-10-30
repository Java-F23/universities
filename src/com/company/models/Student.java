package com.company.models;

import com.company.UniversityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Student {
    private int studentID;
    private String name;
    private String password;
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

    //"Students can mark courses as favorites.
    //Favorited courses should be easily accessible"
    public String addCourseToFavorites(University university, String courseName) {
        try {
            boolean courseExists = false;
            boolean isCourseAlreadyFavorited = false;

            for (Course course : favoriteCourses) {
                if (course.getName().equals(courseName)) {
                    isCourseAlreadyFavorited = true;
                    break;
                }
            }

            if (!isCourseAlreadyFavorited) {
                for (Course course : university.getCourses()) {
                    if (course.getName().equals(courseName)) {
                        courseExists = true;
                        favoriteCourses.add(course);
                        return "Course added to favorites";
                    }
                }
            } else {
                if (courseExists)
                    return "Course is already favorited";
                else
                    return "Course does not exist";
            }
        } catch (NoSuchElementException e) {
            return "Course does not exist in the university.";
        } catch (IllegalArgumentException e) {
            return "Invalid argument provided: " + e.getMessage();
        } catch (UnsupportedOperationException e) {
            return "Operation not supported on the favoriteCourses collection.";
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
        return "An error occurred.";
    }


    public String removeCourseFromFavorites(University university, String courseName) {
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
                        return "Course removed from favorites";
                    }
                }
            } else {
                if (courseExists)
                    return "Course is not favorited";
                else
                    return "Course does not exist";
            }
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
        return "An error occurred.";
    }

    public String enrollInCourse(University university, String courseName, String semesterName) {
        try {
            for (Course course : university.getCourses()) {
                if (course.getName().equals(courseName)) {
                    for (Class class1 : course.getClasses()) {
                        if (class1.getSemester().getName().equals(semesterName)) {
                            if (!class1.isFull()) {
                                class1.enrollStudent(this);
                                return "Enrolled in " + courseName + " for " + semesterName;
                            } else {
                                return "The class is full";
                            }
                        }
                    }
                }
            }
            return "Course or semester not found";
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
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
            // Maintain a list of semesters and their corresponding grade points
            List<String> semesters = new ArrayList<>();
            List<Double> gpaList = new ArrayList();
            List<Integer> courseCounts = new ArrayList(); // Maintain course counts for each semester

            for (StudentGrade grades : academicTranscript.getStudentGrades()) {
                if (grades.isCompleted()) {
                    Class studentClass = grades.getStudentClass();
                    String semesterName = studentClass.getSemester().getName();
                    double gradePoint = grades.getNumericalGrade();

                    // Check if the semester is already in the list
                    int index = semesters.indexOf(semesterName);
                    if (index == -1) {
                        // If the semester is not in the list, add it and the initial GPA
                        semesters.add(semesterName);
                        gpaList.add(gradePoint);
                        courseCounts.add(1); // Initialize course count for this semester
                    } else {
                        // If the semester is already in the list, update the GPA and course count
                        double currentGPA = gpaList.get(index);
                        gpaList.set(index, currentGPA + gradePoint);
                        courseCounts.set(index, courseCounts.get(index) + 1); // Increment course count
                    }
                }
            }

            // Calculate the final GPA for each semester and print the results
            for (int i = 0; i < semesters.size(); i++) {
                double gpa = gpaList.get(i);
                int numberOfCourses = courseCounts.get(i);
                double semesterGPA = gpa / numberOfCourses;
                System.out.println("The semester is " + semesters.get(i) + ", and the GPA is " + semesterGPA);
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
