package gui;

import com.company.models.University;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminNavMenu extends JMenuBar {
    private landing app;
    private University university;
    private AdminHomePanel adminHomePanel;
    private AdminCoursesPanel adminCoursesPanel;
    private AdminProfessorsPanel adminProfessorsPanel;
    private CoursesPanel coursesPanel;

    public AdminNavMenu(landing app, AdminHomePanel adminHomePanel, University university, AdminCoursesPanel adminCoursesPanel, AdminProfessorsPanel adminProfessorsPanel) {
        this.app = app;
        this.university = university;
        this.adminHomePanel = adminHomePanel;
        this.adminCoursesPanel = adminCoursesPanel;
        this.adminProfessorsPanel = adminProfessorsPanel;
        this.coursesPanel = coursesPanel;

        JMenuBar menuBar = new JMenuBar();

        // Courses menu
        JMenu coursesMenu = new JMenu("Courses");
        coursesMenu.setFont(new Font("sansserif", Font.PLAIN, 18));
        coursesMenu.setBackground(new Color(7, 164, 121));

        JMenuItem addClass = new JMenuItem("Add Class");
        JMenuItem editClass = new JMenuItem("Edit Class Capacity");
        JMenuItem viewCoursesItem = new JMenuItem("View Courses");
        JMenuItem addCourseItem = new JMenuItem("Add Course");
        JMenuItem changeCourseDetailsItem = new JMenuItem("Change Course Details");

        coursesMenu.add(viewCoursesItem);
        coursesMenu.add(addCourseItem);
        coursesMenu.add(changeCourseDetailsItem);
        coursesMenu.add(addClass);
        coursesMenu.add(editClass);

        // Professors menu
        JMenu professorsMenu = new JMenu("Professors");
        professorsMenu.setFont(new Font("sansserif", Font.PLAIN, 18));
        professorsMenu.setBackground(new Color(7, 164, 121));

        JMenuItem viewProfessorsItem = new JMenuItem("View Professors");
        JMenuItem addProfessorItem = new JMenuItem("Add Professor");
        JMenuItem removeProfessorItem = new JMenuItem("Remove Professor");

        professorsMenu.add(addProfessorItem);
        professorsMenu.add(removeProfessorItem);
        professorsMenu.add(viewProfessorsItem);

        // Students menu
        JMenu studentsMenu = new JMenu("Students");
        studentsMenu.setFont(new Font("sansserif", Font.PLAIN, 18));
        studentsMenu.setBackground(new Color(7, 164, 121));

        JMenuItem viewStudentsItem = new JMenuItem("View Students");
        JMenuItem enrollStudentItem = new JMenuItem("Enroll Student");

        studentsMenu.add(viewStudentsItem);
        studentsMenu.add(enrollStudentItem);

        // Reports menu
        JMenu reportsMenu = new JMenu("Reports");
        reportsMenu.setFont(new Font("sansserif", Font.PLAIN, 18));
        reportsMenu.setBackground(new Color(7, 164, 121));

        //add a tooltip for the reports menu syaing it is for generating reports
        reportsMenu.setToolTipText("Generate Report");

        // Logout menu
        JButton logoutMenu = new JButton("Logout");
        // Make it look like a menu item
        logoutMenu.setBorderPainted(false);
        logoutMenu.setFocusPainted(false);
        logoutMenu.setContentAreaFilled(false);
        logoutMenu.setFont(new Font("sansserif", Font.PLAIN, 18));

        // Add menus to the menu bar
        menuBar.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 20);
        menuBar.setPreferredSize(new Dimension(menuBar.getPreferredSize().width, 40));
        menuBar.setBackground(new Color(7, 164, 121));

        menuBar.add(coursesMenu);
        menuBar.add(professorsMenu);
        menuBar.add(studentsMenu);
        menuBar.add(reportsMenu);
        menuBar.add(logoutMenu);

        // Set the menu bar for the main frame
        app.setJMenuBar(menuBar);

        addClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminHomePanel.switchToPanel(adminCoursesPanel.createAddClassPanel());
            }
        });

        // Add action listeners to menu items
        viewCoursesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminHomePanel.switchToPanel(adminCoursesPanel.viewCoursesPanel());
            }
        });

        addCourseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminHomePanel.switchToPanel(adminCoursesPanel.addCoursePanel());
            }
        });

        changeCourseDetailsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminHomePanel.switchToPanel(adminCoursesPanel.updateCourseDetailsPanel());
            }
        });

        editClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminHomePanel.switchToPanel(adminCoursesPanel.editCapacityPanel());
            }
        });

        viewProfessorsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        addProfessorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminHomePanel.switchToPanel(adminProfessorsPanel.createAddProfessorPanel());
            }
        });

        removeProfessorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action to remove a professor
            }
        });

        viewStudentsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        enrollStudentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement the action to enroll a student
            }
        });


        logoutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.switchToLandingFrame();
                adminHomePanel.switchToHomePage();
            }
        });
    }
}
