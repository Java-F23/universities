package gui;
import com.company.models.*;
import com.company.models.Class;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GradesPanel extends JPanel {
    private StudentHomePanel parentPanel;
    private University university;
    private Student student;

    public GradesPanel(StudentHomePanel parentPanel, University university) {
        this.parentPanel = parentPanel;
        this.university = university;
        this.student = parentPanel.getStudent();
        setLayout(new BorderLayout());
    }


    public JPanel getTranscriptPanel() {
        student = parentPanel.getStudent();
        AcademicTranscript academicTranscript = student.getAcademicTranscript();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for viewing the academic transcript
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Semester");
        tableModel.addColumn("Grade");

        JTable transcriptTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(transcriptTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = transcriptTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size
        tableHeader.setReorderingAllowed(false); // Prevent column reordering

        // Fetch and display academic transcript data
        try {
            for (StudentGrade studentGrade : academicTranscript.getStudentGrades()) {
                if (studentGrade.isCompleted()) {
                    Class studentClass = studentGrade.getStudentClass();
                    Course course = studentClass.getCourse();
                    Semester semester = studentClass.getSemester();

                    // Add a new row to the table model with course, semester, and grade
                    tableModel.addRow(new Object[]{course.getName(), semester.getName(), studentGrade.getGrade()});
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return panel;
    }

    public JPanel getHistoricalCourseSchedulePanel() {
        student = parentPanel.getStudent();
        AcademicTranscript academicTranscript = student.getAcademicTranscript();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for viewing the historical course schedule
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Semester");
        tableModel.addColumn("Grade");
        tableModel.addColumn("Day of the Week");
        tableModel.addColumn("Time");
        tableModel.addColumn("Location");

        JTable scheduleTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(scheduleTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = scheduleTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size
        tableHeader.setReorderingAllowed(false); // Prevent column reordering

        // Fetch and display historical course schedule data
        try {
            for (StudentGrade grades : academicTranscript.getStudentGrades()) {
                if (grades.isCompleted()) {
                    Class studentClass = grades.getStudentClass();
                    Course course = studentClass.getCourse();
                    Semester semester = studentClass.getSemester();

                    // Add a new row to the table model with course, semester, grade, day, time, and location
                    tableModel.addRow(new Object[]{
                            course.getName(),
                            semester.getName(),
                            grades.getGrade(),
                            studentClass.getSchedule().getClassTimings().getDayOfWeek(),
                            studentClass.getSchedule().getClassTimings().getTime(),
                            studentClass.getSchedule().getLocation()
                    });
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return panel;
    }

    public JPanel getGPAPanel() {
        Student student = parentPanel.getStudent();
        AcademicTranscript academicTranscript = student.getAcademicTranscript();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for viewing GPA
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Semester");
        tableModel.addColumn("GPA");

        JTable gpaTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(gpaTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = gpaTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size
        tableHeader.setReorderingAllowed(false); // Prevent column reordering

        try {
            // Maintain a list of semesters and their corresponding grade points
            List<String> semesters = new ArrayList<>();
            List<Double> gpaList = new ArrayList<>();
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

            // Calculate the final GPA for each semester and add the data to the table
            for (int i = 0; i < semesters.size(); i++) {
                String semesterName = semesters.get(i);
                double gpa = gpaList.get(i);
                int numberOfCourses = courseCounts.get(i);
                double semesterGPA = gpa / numberOfCourses;

                // Add a new row to the table model with semester and GPA
                tableModel.addRow(new Object[]{semesterName, semesterGPA});
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return panel;
    }


}
