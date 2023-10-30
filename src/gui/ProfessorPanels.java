package gui;
import com.company.models.*;
import com.company.models.Class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProfessorPanels extends JPanel {
    private ProfessorHomePanel parentPanel;
    private University university;
    private Professor professor;
    private JComboBox<Student> studentComboBox;
    private JComboBox<String> gradeComboBox;
    private JButton submitButton;

    public ProfessorPanels(ProfessorHomePanel parentPanel, University university) {
        this.parentPanel = parentPanel;
        this.university = university;
        this.professor = parentPanel.getProfessor();
        setLayout(new BorderLayout());
    }

    public JPanel createGradeEntryPanel(List<String> gradeChoices) {
        professor = university.findProfessorByID(professor.getProfessorID());
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
                    } else {
                        professor.inputGrades(student7, class8, grade);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid student ID. Please enter a number.");
                }
            }
        });
        gradeEntryPanel.add(submitButton, BorderLayout.SOUTH);

        return gradeEntryPanel;
    }

}

