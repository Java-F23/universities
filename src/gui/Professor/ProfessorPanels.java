package gui.Professor;
import com.company.models.*;
import com.company.models.Class;
import gui.Student.CoursesPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProfessorPanels extends JPanel {
    private ProfessorHomePanel parentPanel;
    private CoursesPanel coursesPanel;
    private University university;
    private Professor professor;
    private JComboBox<String> gradeComboBox;
    private JButton submitButton;

    public ProfessorPanels(ProfessorHomePanel parentPanel, University university, Professor professor) {
        this.parentPanel = parentPanel;
        this.university = university;
        this.professor = professor;
        this.coursesPanel = coursesPanel;
        setLayout(new BorderLayout());
    }

    public JPanel createGradeEntryPanel(List<String> gradeChoices) {
        professor = parentPanel.getProfessor();
        JPanel gradeEntryPanel = new JPanel();
        gradeEntryPanel.setLayout(new BorderLayout());

        // Create a panel for entering data
        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.setLayout(new FlowLayout());

        // Input student ID as int and give an error if not int
        JTextField studentIDField = new JTextField(10);
        dataEntryPanel.add(new JLabel("Student ID: "));
        dataEntryPanel.add(studentIDField);

        // Input course name
        JTextField courseNameField = new JTextField(10);
        dataEntryPanel.add(new JLabel("Course Name: "));
        dataEntryPanel.add(courseNameField);

        // Create a combo box for grades
        gradeComboBox = new JComboBox<>(gradeChoices.toArray(new String[0]));
        gradeComboBox.setPreferredSize(new Dimension(100, 30));
        dataEntryPanel.add(new JLabel("Grade: "));
        dataEntryPanel.add(gradeComboBox);

        gradeEntryPanel.add(dataEntryPanel, BorderLayout.CENTER);

        // Create a submit button
        submitButton = new JButton("Submit Grade");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentIDText = studentIDField.getText();
                String courseName8 = courseNameField.getText();
                String grade = (String) gradeComboBox.getSelectedItem();

                try {
                    int studentID2 = Integer.parseInt(studentIDText);

                    Student student7 = university.findStudentByID(studentID2);
                    Course course5 = university.findCourseByName(courseName8);
                    Class class8 = course5.findClassByStudent(student7);

                    if (class8 == null) {
                        JOptionPane.showMessageDialog(null, "Class not found.");
                    } else if(student7 == null){
                        JOptionPane.showMessageDialog(null, "Student not found.");
                    } else if(course5 ==null){
                        JOptionPane.showMessageDialog(null, "Course not found.");
                    }
                    else {
                        professor.inputGrades(student7, class8, grade);
                        JOptionPane.showMessageDialog(null, "Grade submitted.");
                        // Clear the text fields
                        studentIDField.setText("");
                        courseNameField.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid student ID. Please enter a number.");
                }
            }
        });
        gradeEntryPanel.add(submitButton, BorderLayout.SOUTH);

        return gradeEntryPanel;
    }

    public JPanel takeAttendancePanel() {
        professor = parentPanel.getProfessor();
        JPanel attendancePanel = new JPanel();
        attendancePanel.setLayout(new BorderLayout());

        JPanel dataEntryPanel = new JPanel();
        dataEntryPanel.setLayout(new FlowLayout());

        JTextField studentIDField = new JTextField(10);
        dataEntryPanel.add(new JLabel("Student ID: "));
        dataEntryPanel.add(studentIDField);

        JTextField courseNameField = new JTextField(10);
        dataEntryPanel.add(new JLabel("Course Name: "));
        dataEntryPanel.add(courseNameField);

        JCheckBox attendanceCheckBox = new JCheckBox("Attended?");
        dataEntryPanel.add(attendanceCheckBox);
        //add a tool kit to explain what the checkbox is for
        attendanceCheckBox.setToolTipText("Check if student attended class");

        attendancePanel.add(dataEntryPanel, BorderLayout.CENTER);

        JButton takeAttendanceButton = new JButton("Take Attendance");
        takeAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentIDText = studentIDField.getText();
                String courseName = courseNameField.getText();
                boolean isPresent = attendanceCheckBox.isSelected();

                try {
                    int studentID = Integer.parseInt(studentIDText);
                    Student student = university.findStudentByID(studentID);
                    Course course = university.findCourseByName(courseName);
                    Class class1 = course.findClassByStudent(student); //find class by student in course

                    if (student != null && course != null&& class1 != null) {
                        professor.takeAttendance(student, class1, isPresent);
                        JOptionPane.showMessageDialog(null, "Attendance taken.");
                        studentIDField.setText("");
                        courseNameField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Student, course, or course not found.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid student ID. Please enter a number.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                }
            }
        });
        attendancePanel.add(takeAttendanceButton, BorderLayout.SOUTH);

        return attendancePanel;
    }


}
