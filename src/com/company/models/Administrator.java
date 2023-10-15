package com.company.models;

import com.company.UniversityManager;

import java.util.ArrayList;
import java.util.Iterator;

public class Administrator {
    private int adminID;
    private String universityName;

    public Administrator(String universityName) {
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
    public void addClass(University university, String courseName, String semesterName, int professorID, int maxClassCapacity, Schedule schedule) {
        try {
            Course course = university.findCourseByName(courseName);
            Semester semester = university.findSemesterByName(semesterName);
            Professor professor = university.findProfessorByID(professorID);

            if (course == null) {
                System.out.println("Course does not exist");
                return;
            }
            if (semester == null) {
                System.out.println("Semester does not exist");
                return;
            }
            if (professor == null) {
                System.out.println("Professor does not exist");
                return;
            }

            int sectionNumber = 0;
            for (Class class1 : course.getClasses()) {
                if (class1.getSemester().getName().equals(semesterName)) {
                    sectionNumber = class1.getSectionNumber();
                    System.out.println("Able to add class to semester: " + semesterName);
                }else {
                    System.out.println("Created first section for the course: " + courseName);
                    sectionNumber = 1;
                }
            }

            sectionNumber++;

            Class newClass = new Class(course, sectionNumber, semester, professor, maxClassCapacity, schedule);
            course.addClass(newClass);
            university.addClass(newClass, course);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void updateCourse(University university, String oldCourseName, String newCourseName, String department, String description) {
        try {
            boolean foundCourse = false;
            for (Course course : university.getCourses()) {
                if (course.getName().equals(oldCourseName)) {
                    course.setName(newCourseName);
                    course.setDepartment(department);
                    course.setDescription(description);
                    foundCourse = true;
                    System.out.println("Course updated");
                }
            }

            if (!foundCourse) {
                System.out.println("Course not found");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void addProfessor(University university, String professorName, String department) {
        try {
            Professor professor = new Professor(professorName, department, university.getName(), university.getProfessors().size() + 8001);
            university.addProfessor(professor);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void removeProfessor(University university, String professorName) {
        try {
            boolean foundProfessor = false;
            Iterator<Professor> professorIterator = university.getProfessors().iterator();
            while (professorIterator.hasNext()) {
                Professor professor = professorIterator.next();
                if (professor.getName().equals(professorName)) {
                    professorIterator.remove();
                    foundProfessor = true;
                    System.out.println("Professor removed");
                }
            }

            if (!foundProfessor) {
                System.out.println("Professor not found");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void enrollStudent(University university, String studentName, String courseName, String semester) {
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
                                return;  // Enrollment successful
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
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    public void editCapacity(University university, String courseName, String semester, int capacity, int sectionNumber) {
        try {
            boolean courseExists = false;

            for (Course course : university.getCourses()) {
                if (course.getName().equals(courseName)) {
                    courseExists = true;
                    for (Class class1 : course.getClasses()) {
                        if (class1.getSectionNumber() == sectionNumber && class1.getSemester().getName().equals(semester)) {
                            class1.setMaxClassCapacity(capacity);
                            System.out.println("Capacity updated");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }


    public class ReportGenerator {
        public static void generateStudentPerformanceReport(ArrayList<Student> students) {
            try {
                // Generate a report header
                System.out.println("=== Student Performance Report ===");
                if (students == null) {
                    System.err.println("Error: Student list is null.");
                    return;
                }
                System.out.println("Total Students: " + students.size());
                System.out.println("Student Name\t\tCourse\t\t\tGrade\tAttendance\tAbsence");

                // Iterate through each student and their grades
                for (Student student : students) {
                    if (student == null || student.getAcademicTranscript() == null) {
                        System.err.println("Error: Missing data for a student or student grade.");
                        continue;  // Skip to the next student if data is missing
                    }
                    for (StudentGrade studentGrade : student.getAcademicTranscript().getStudentGrades()) {
                        String studentName = student.getName();
                        String courseName = studentGrade.getStudentClass().getCourse().getName();
                        String grade = studentGrade.getGrade();
                        int attendance = studentGrade.getAttendance();
                        int absence = studentGrade.getAbsence();

                        // Print student performance information with fixed column widths
                        System.out.printf("%-15s %-25s %-10s %-10d %-10d%n", studentName, courseName, grade, attendance, absence);
                    }
                }
            } catch (NullPointerException e) {
                // Handle any unexpected NullPointerExceptions
                System.err.println("Error: An unexpected NullPointerException occurred.");
            }
        }
    }

}
