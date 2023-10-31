package com.company.models;

import com.company.UniversityManager;

import java.util.ArrayList;
import java.util.Iterator;

public class Administrator {
    private int adminID;
    private String password;
    private String universityName;

    public Administrator(String universityName, int adminID, String password) {
        this.universityName = universityName;
    }

    //Administrators can enter course details, including course name, department, and description.
    public void createCourse(University university, String courseName, String department, String description, int numOfCredits) {
        try {
            Course adminCourse = new Course(courseName, department, description, numOfCredits);
            university.addCourse(adminCourse);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    //Admins can add a section to a specific course
    public String addClass(University university, String courseName, String semesterName, int professorID, int maxClassCapacity, Schedule schedule) {
        try {
            Course course = university.findCourseByName(courseName);
            Semester semester = university.findSemesterByName(semesterName);
            Professor professor = university.findProfessorByID(professorID);

            if (course == null) {
                return "Course does not exist";
            }
            if (semester == null) {
                return "Semester does not exist";
            }
            if (professor == null) {
                return "Professor does not exist";
            }

            int sectionNumber = 0;
            for (Class class1 : course.getClasses()) {
                if (class1.getSemester().getName().equals(semesterName)) {
                    sectionNumber = class1.getSectionNumber();
                    return "Able to add class to semester: " + semesterName;
                } else {
                    return "Created first section for the course: " + courseName;
                }
            }

            sectionNumber++;

            Class newClass = new Class(course, sectionNumber, semester, professor, maxClassCapacity, schedule);
            course.addClass(newClass);
            university.addClass(newClass, course);

            return "Class added successfully";
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }


    public String updateCourse(University university, String oldCourseName, String newCourseName, String department, String description) {
        try {
            boolean foundCourse = false;
            for (Course course : university.getCourses()) {
                if (course.getName().equals(oldCourseName)) {
                    course.setName(newCourseName);
                    course.setDepartment(department);
                    course.setDescription(description);
                    foundCourse = true;
                    return "Course updated";
                }
            }

            if (!foundCourse) {
                return "Course not found";
            }
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }

        return ""; // Return an empty string if no specific message is returned
    }

    public void addProfessor(University university, String professorName, String department) {
        try {
            Professor professor = new Professor(professorName, department, university.getName(), university.getProfessors().size() + 8001);
            university.addProfessor(professor);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public boolean removeProfessor(University university, String professorName) {
        try {
            boolean foundProfessor = false;
            Iterator<Professor> professorIterator = university.getProfessors().iterator();
            while (professorIterator.hasNext()) {
                Professor professor = professorIterator.next();
                if (professor.getName().equals(professorName)) {
                    professorIterator.remove();
                    foundProfessor = true;
                    System.out.println("Professor removed");
                    return true;
                }
            }

            if (!foundProfessor) {
                System.out.println("Professor not found");
                return false;
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
        return false;
    }

    public String enrollStudent(University university, String studentName, String courseName, String semester) {
        try {
            boolean courseExists = false;
            boolean isStudentAlreadyEnrolled = false;

            int studentID = university.getNewStudentID();

            // Enroll the student in a class
            Student student = new Student(studentName, university.getName(), studentID);
            university.addStudent(student);

            for (Course course : university.getCourses()) {
                if (course.getName().equals(courseName)) {
                    courseExists = true;
                    for (Class class1 : course.getClasses()) {
                        if (class1.getSemester().getName().equals(semester)) {
                            if (!class1.isFull()) {
                                class1.enrollStudent(student);
                                return "Enrollment successful";
                            } else {
                                throw new IllegalStateException("Class is full");
                            }
                        }
                    }
                }
            }

            if (!courseExists) {
                throw new IllegalArgumentException("Course not found");
            }
        } catch (Exception e) {
            // Log or handle the exception as needed
            return "An error occurred: " + e.getMessage();
        }
        return ""; // Return an empty string if no specific message is returned
    }


    public String editCapacity(University university, String courseName, String semester, int capacity, int sectionNumber) {
        try {
            boolean courseExists = false;

            for (Course course : university.getCourses()) {
                if (course.getName().equals(courseName)) {
                    courseExists = true;
                    for (Class class1 : course.getClasses()) {
                        if (class1.getSectionNumber() == sectionNumber && class1.getSemester().getName().equals(semester)) {
                            class1.setMaxClassCapacity(capacity);
                            return "Capacity updated";
                        }
                    }
                }
            }

            if (!courseExists) {
                return "Course does not exist";
            }

            return "Section not found";
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage();
        }
    }


}
