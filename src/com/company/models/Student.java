package com.company.models;

import com.company.UniversityManager;

import java.util.List;

public class Student {
    private int studentID;
    private String name;
    private String universityName;
    //private List<StudentCourseEnrollment> enrolledCourses;
    private List<Course> favoriteCourses;
    private AcademicTranscript academicTranscript;
    private List<HistoricalCoursePerformance> historicalPerformance;

    // Constructors, getters, and setters

//    public void enrollInCourse(Course course, Semester semester) {
//        StudentCourseEnrollment enrollment = new StudentCourseEnrollment();
//        enrollment.setCourse(course);
//        enrollment.setSemester(semester);
//        enrolledCourses.add(enrollment);
//    }

    public String getName() {
        return name;
    }

    public List<Course> getFavoriteCourses() {
        return favoriteCourses;
    }

    public Student(String name, String universityName) {
        this.name = name;
        this.universityName = universityName;
    }


    //Students can access a list of available courses, displaying course names and departments.
    public List<Course> getAvailableCourses(String semester){
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        List<Course> availableCourses = null;

        //loop over the courses in the university and print their course names and departments
        for(Course course : university.getCourses()){
            for(Semester semester1 : course.getOfferedInSemesters()) {
                if (semester1.getName().equals(semester)) {
                    availableCourses.add(course);
                    System.out.println("The course name is " + course.getName() + ", and the department is " + course.getDepartment() + "\n");
                    }
                }
            }

        return availableCourses;
    }

    //"Students can search for courses using filters like department and professor.
    //The system displays matching courses."
    public List<Course> searchCourseByDepartment(String department){
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        List<Course> matchingCourses = null;

        //loop over the courses in the university and print their course names and departments
        for(Course course : university.getCourses()){
            if(course.getDepartment().equals(department)){
                matchingCourses.add(course);
                System.out.println("The course name is "+ course.getName() + ", and the department is " + course.getDepartment()+"\n");
            }
        }

        return matchingCourses;
    }

    public List<Course> searchCourseByProfessor(String professorName){
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        List<Course> matchingCourses = null;

        //loop over the courses in the university and print their course names and departments
        for(Course course : university.getCourses()){
            if(course.getProfessor().equals(professorName)){
                matchingCourses.add(course);
                System.out.println("The course name is "+ course.getName() + ", and the department is " + course.getDepartment()+"\n");
            }
        }

        return matchingCourses;
    }

    //Students can access a detailed page for each course, displaying course descriptions and schedules.
    public void getCourseDetails(String courseName){
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        //loop over the courses in the university and print their course names and departments
        for(Course course : university.getCourses()){
            if(course.getName().equals(courseName)){
                System.out.println("The course name is "+ course.getName() + ", and the department is " + course.getDepartment()+"\n");
                System.out.println("The course description is "+ course.getDescription() + ", and the schedule is " + course.getSchedule().getClassTimings()+"\n");
            }
        }
    }

    //Students can access the course schedule for a specific semester, including class timings and locations.
    public void getCourseSchedule(String semester){
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        //loop over the courses in the university and print their course names and departments
        for(Course course : university.getCourses()){
            for(Semester semester1 : course.getOfferedInSemesters()){
                if(semester1.getName().equals(semester)){
                    System.out.println("The course name is "+ course.getName() + ", and the department is " + course.getDepartment()+"\n");
                    System.out.println("The course schedule is "+ course.getSchedule().getClassTimings() + ", and the location is " + course.getSchedule().getLocation()+"\n");
                }
            }
        }
    }

    //"Students can mark courses as favorites.
    //Favorited courses should be easily accessible"
    public void addCourseToFavorites(String courseName) {
        boolean courseExists = false;
        boolean isCourseAlreadyFavorited = false;
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        //loop over favoritedCourses
        for (Course course : favoriteCourses) {
            if (course.getName().equals(courseName)) {
                isCourseAlreadyFavorited = true;
            }
        }

        if(!isCourseAlreadyFavorited) {
            //loop over the courses in the university and print their course names and departments
            for (Course course : university.getCourses()) {
                if (course.getName().equals(courseName)) {
                    courseExists = true;
                    favoriteCourses.add(course);
                }
            }
        }
        else {
            if(courseExists)
            System.out.println("Course is already favorited");
            else
                System.out.println("Course does not exist");
        }
    }

    //remove course from favorites
    public void removeCourseFromFavorites(String courseName) {
        boolean courseExists = false;
        boolean isCourseAlreadyFavorited = false;
        UniversityManager universityManager = UniversityManager.getInstance();

        University university = universityManager.findUniverisityByName(universityName);

        //loop over favoritedCourses
        for (Course course : favoriteCourses) {
            if (course.getName().equals(courseName)) {
                isCourseAlreadyFavorited = true;
            }
        }

        if(isCourseAlreadyFavorited) {
            //loop over the courses in the university and print their course names and departments
            for (Course course : university.getCourses()) {
                if (course.getName().equals(courseName)) {
                    courseExists = true;
                    favoriteCourses.remove(course);
                }
            }
        }
        else {
            if(courseExists)
                System.out.println("Course is not favorited");
            else
                System.out.println("Course does not exist");
        }
    }

}
