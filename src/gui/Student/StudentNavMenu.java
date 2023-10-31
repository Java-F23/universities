package gui.Student;

import com.company.models.Student;
import com.company.models.University;
import gui.landing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentNavMenu extends JMenuBar{
    public JMenuBar getStudentMenuBar() {
        return this; // Return the current instance, which is a JMenuBar
    }
    private Student student;
    private landing app; // Add a field to store the 'landing' instance
    private StudentHomePanel studentHomePanel;
    private CoursesPanel coursesPanel;
    private GradesPanel gradesPanel;
    private University university;

    public StudentNavMenu(landing app, StudentHomePanel studentHomePanel, CoursesPanel coursesPanel, GradesPanel gradesPanel, University university) {
        this.studentHomePanel=studentHomePanel;
        this.app = app;
        this.coursesPanel = coursesPanel; // Initialize coursesPanel
        this.gradesPanel = gradesPanel; // Initialize gradesPanel
        this.university = university; // Initialize university

        JMenuBar menuBar = new JMenuBar();

        // Courses menu
        JMenu coursesMenu = new JMenu("Courses");
        coursesMenu.setFont(new Font("sansserif", Font.PLAIN, 18));
        coursesMenu.setBackground(new Color(7, 164, 121));
        JMenuItem viewCoursesItem = new JMenuItem("View Courses");
        JMenuItem viewCourseByDepartmentItem = new JMenuItem("View Course by Department");
        JMenuItem viewCourseByProfessorItem = new JMenuItem("View Course by Professor");
        JMenuItem getCourseDetailsItem = new JMenuItem("Get Course Details");
        JMenuItem getCourseScheduleItem = new JMenuItem("Get Course Schedule");
        JMenuItem favoriteCoursesItem = new JMenuItem("Favorite Courses");
        JMenuItem removeCourseFromFavoritesItem = new JMenuItem("Remove Course from Favorites");
        JMenuItem viewFavoriteCoursesItem = new JMenuItem("View Favorite Courses");
        JMenuItem enrollInCourseItem = new JMenuItem("Enroll in a Course");
        coursesMenu.add(viewCoursesItem);
        coursesMenu.add(viewCourseByDepartmentItem);
        coursesMenu.add(viewCourseByProfessorItem);
        coursesMenu.add(getCourseDetailsItem);
        coursesMenu.add(getCourseScheduleItem);
        coursesMenu.add(favoriteCoursesItem);
        coursesMenu.add(removeCourseFromFavoritesItem);
        coursesMenu.add(viewFavoriteCoursesItem);
        coursesMenu.add(enrollInCourseItem);

        // Reports menu
        JMenu reportsMenu = new JMenu("Reports");
        reportsMenu.setFont(new Font("sansserif", Font.PLAIN, 18));
        reportsMenu.setBackground(new Color(7, 164, 121));
        JMenuItem viewAcademicTranscriptItem = new JMenuItem("View Academic Transcript");
        JMenuItem viewHistoricalCourseScheduleItem = new JMenuItem("View Historical Course Schedule");
        JMenuItem viewPastSemesterPerformanceItem = new JMenuItem("View Past Semester Performance");
        reportsMenu.add(viewAcademicTranscriptItem);
        reportsMenu.add(viewHistoricalCourseScheduleItem);
        reportsMenu.add(viewPastSemesterPerformanceItem);

        // Logout menu
        JButton logoutMenu = new JButton("Logout");
        //make it look like menu item
        logoutMenu.setBorderPainted(false);
        logoutMenu.setFocusPainted(false);
        logoutMenu.setContentAreaFilled(false);
        logoutMenu.setFont(new Font("sansserif", Font.PLAIN, 18));

        menuBar.setLayout(new GridBagLayout()); // Use GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow horizontal expansion
        gbc.insets = new Insets(20, 20, 20, 20); // Add some padding
        menuBar.setPreferredSize(new Dimension(menuBar.getPreferredSize().width, 40)); // Set the preferred height to 80 pixels
        //set color to green
        menuBar.setBackground(new Color(7, 164, 121));
        //menuBar.setLayout(new FlowLayout(FlowLayout.LEFT)); // Use FlowLayout with left alignment

        Component horizontalStrut = Box.createHorizontalStrut(70);
        // Add menus to the menu bar
        menuBar.add(coursesMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(reportsMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(logoutMenu);

        logoutMenu.setForeground(new Color(7, 164, 121));

        //app.setJMenuBar(menuBar);
        studentHomePanel.setJMenuBar(menuBar);

        // Add action listeners to menu items
        viewCoursesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(coursesPanel.viewCoursesPanel());
            }
        });

        viewCourseByDepartmentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(coursesPanel.viewCoursesByDepartmentPanel());
            }
        });

        viewCourseByProfessorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(coursesPanel.viewCoursesByProfessorPanel());
            }
        });

        getCourseDetailsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(coursesPanel.viewCourseDetailsPanel());
            }
        });

        getCourseScheduleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(coursesPanel.getCourseSchedulePanel());
            }
        });

        favoriteCoursesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(coursesPanel.addToFavoritesPanel());

            }
        });

        removeCourseFromFavoritesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(coursesPanel.removeCourseFromFavorites());
            }
        });

        viewFavoriteCoursesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(coursesPanel.viewFavoriteCoursesPanel());
            }
        });

        enrollInCourseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(coursesPanel.enrollInCoursePanel());
            }
        });

        viewAcademicTranscriptItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(gradesPanel.getTranscriptPanel());
            }
        });

        viewHistoricalCourseScheduleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(gradesPanel.getHistoricalCourseSchedulePanel());
            }
        });

        viewPastSemesterPerformanceItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentHomePanel.switchToPanel(gradesPanel.getGPAPanel());
            }
        });

        logoutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.switchToLandingFrame();
                studentHomePanel.switchToHomePage();
            }
        });
    }
}

