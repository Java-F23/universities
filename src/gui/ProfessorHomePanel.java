package gui;

import com.company.models.Professor;
import com.company.models.Student;
import com.company.models.University;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProfessorHomePanel extends JPanel {
    private University university;
    private Professor professor;
    private landing app; // Add a field to store the 'landing' instance
    private ProfNavMenu profNavMenu;
    private JPanel currentPanel;
    private ProfessorPanels professorPanels;

    public ProfessorHomePanel(landing app, ProfNavMenu profNavMenu, Professor professor, ProfessorPanels professorPanels) {
        this.app = app;
        this.professor = professor;
        this.profNavMenu = profNavMenu;
        this.professorPanels = professorPanels;

        // Use GridBagLayout for centering
        setLayout(new GridBagLayout());

        // Create a GridBagConstraints object
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // Center horizontally
        gbc.weightx = 1; // Fill horizontal space
        gbc.insets = new Insets(10, 0, 10, 0); // Add vertical spacing

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to UniTrack!");
        welcomeLabel.setFont(new Font("sansserif", Font.BOLD, 40));
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(welcomeLabel, gbc);

        // Button: Take Attendance
        JButton takeAttendanceButton = new JButton("Take Attendance");
        takeAttendanceButton.setFont(new Font("sansserif", Font.BOLD, 18));
        takeAttendanceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        takeAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action when the "Take Attendance" button is clicked
            }
        });
        gbc.gridy = 1; // Center vertically
        add(takeAttendanceButton, gbc);

        List<String> gradeChoices = new ArrayList<>();
        gradeChoices.add("A");
        gradeChoices.add("A-");
        gradeChoices.add("B+");
        gradeChoices.add("B");
        gradeChoices.add("B-");
        gradeChoices.add("C+");
        gradeChoices.add("C");
        gradeChoices.add("C-");
        gradeChoices.add("D");

        // Button: Enter Grades
        JButton enterGradesButton = new JButton("Enter Grades");
        enterGradesButton.setFont(new Font("sansserif", Font.BOLD, 18));
        enterGradesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enterGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.getProfessorHomePanel().switchToPanel(professorPanels.createGradeEntryPanel(gradeChoices));
            }
        });
        gbc.gridy = 2; // Place below the "Take Attendance" button
        add(enterGradesButton, gbc);

        // Button: Logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("sansserif", Font.BOLD, 18));
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action to log out
                app.switchToLandingFrame();
            }
        });
        gbc.gridy = 3; // Place below the "Enter Grades" button
        add(logoutButton, gbc);
    }

    public void switchToPanel(JPanel newPanel) {
        if (currentPanel != null) {
            remove(currentPanel);
        }
        currentPanel = newPanel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setMenuBar(StudentNavMenu navMenu) {
        app.setJMenuBar(navMenu); // Set the menu bar for the main frame
        app.revalidate();
        app.repaint();
    }

    public void setJMenuBar(JMenuBar menuBar) {
        app.setJMenuBar(menuBar);
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }
}
