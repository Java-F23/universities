package gui.Admin;
import com.company.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminCoursesPanel extends JPanel {
    private AdminHomePanel parentPanel;
    private University university;
    Administrator admin;

    public AdminCoursesPanel(AdminHomePanel parentPanel, University university, Administrator admin) {
        this.parentPanel = parentPanel;
        this.university = university;
        this.admin = admin;
        setLayout(new BorderLayout());
    }

    public JPanel viewCoursesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Department");

        JTable courseTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(courseTable);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = courseTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size

        // Implement logic to display available courses
        ArrayList<Course> courses = university.getCourses();

        // Update the table with course data
        for (Course course : courses) {
            tableModel.addRow(new Object[]{course.getName(), course.getDepartment()});
        }

        return panel;
    }


    public JPanel addCoursePanel() {
        JPanel addCoursePanel = new JPanel();
        addCoursePanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Course Name
        JLabel nameLabel = new JLabel("Course Name:");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addCoursePanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField nameField = new JTextField(30);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addCoursePanel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Department
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addCoursePanel.add(departmentLabel, gbc);
        gbc.gridx = 1;
        JTextField departmentField = new JTextField(30);
        departmentField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addCoursePanel.add(departmentField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Description
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addCoursePanel.add(descriptionLabel, gbc);
        gbc.gridx = 1;
        JTextField descriptionField = new JTextField(30);
        descriptionField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addCoursePanel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Number of Credits (ComboBox)
        JLabel creditsLabel = new JLabel("Number of Credits:");
        creditsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addCoursePanel.add(creditsLabel, gbc);
        gbc.gridx = 1;
        String[] creditOptions = { "1", "2", "3", "4" };
        JComboBox<String> creditsComboBox = new JComboBox<>(creditOptions);
        creditsComboBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addCoursePanel.add(creditsComboBox, gbc);

        // Center and make the button the same width and increase button height
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton createButton = new JButton("Create Course");
        createButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        createButton.setPreferredSize(new Dimension(200, 30));
        addCoursePanel.add(createButton, gbc);

        // Create a JLabel for displaying messages
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel messageLabel = new JLabel();
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addCoursePanel.add(messageLabel, gbc);

        createButton.addActionListener(e -> {
            String courseName = nameField.getText();
            String department = departmentField.getText();
            String description = descriptionField.getText();
            int numOfCredits = Integer.parseInt((String) creditsComboBox.getSelectedItem());

            // Try to create the course and display a dialog with a message
            if (createCourse(university, courseName, department, description, numOfCredits)) {
                JOptionPane.showMessageDialog(
                        addCoursePanel,
                        "Course created successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                // Clear the input fields
                nameField.setText("");
                departmentField.setText("");
                descriptionField.setText("");
                creditsComboBox.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(
                        addCoursePanel,
                        "An error occurred.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        return addCoursePanel;
    }

    public boolean createCourse(University university, String courseName, String department, String description, int numOfCredits) {
        try {
            Course adminCourse = new Course(courseName, department, description, numOfCredits);
            university.addCourse(adminCourse);
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false;
        }
    }

    public JPanel updateCourseDetailsPanel() {
        JButton updateCourseButton; // Declare the button variable

        admin = parentPanel.getAdmin();
        if (admin == null) {
            System.out.println("Admin is null");
        }
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Change layout to GridBagLayout for consistent sizing and layout

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Increase the size of components
        int fieldWidth = 30;

        // Old Course Name
        JLabel oldCourseNameLabel = new JLabel("Old Course Name:");
        oldCourseNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(oldCourseNameLabel, gbc);

        gbc.gridx = 1;
        JTextField oldCourseNameField = new JTextField(fieldWidth);
        oldCourseNameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panel.add(oldCourseNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // New Course Name
        JLabel newCourseNameLabel = new JLabel("New Course Name:");
        newCourseNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(newCourseNameLabel, gbc);

        gbc.gridx = 1;
        JTextField newCourseNameField = new JTextField(fieldWidth);
        newCourseNameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panel.add(newCourseNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Department
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(departmentLabel, gbc);

        gbc.gridx = 1;
        JTextField departmentField = new JTextField(fieldWidth);
        departmentField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panel.add(departmentField, gbc);

        // Create a button panel
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        updateCourseButton = new JButton("Update Course");
        updateCourseButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        updateCourseButton.setPreferredSize(new Dimension(200, 30));
        panel.add(updateCourseButton, gbc);

        // Create a JLabel for displaying messages
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel messageLabel = new JLabel();
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(messageLabel, gbc);

        updateCourseButton.addActionListener(e -> {
            try {
                String oldCourseName = oldCourseNameField.getText();
                String newCourseName = newCourseNameField.getText();
                String department = departmentField.getText();

                // Validate input and handle exceptions
                if (oldCourseName.isEmpty() || newCourseName.isEmpty() || department.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                // Call the updateCourse function and provide user feedback
                String feedback = admin.updateCourse(university, oldCourseName, newCourseName, department, "");

                // Display user feedback using an output dialog
                messageLabel.setText(feedback);

                // Clear the input fields
                oldCourseNameField.setText("");
                newCourseNameField.setText("");
                departmentField.setText("");
            } catch (Exception ex) {
                // Handle exceptions
                messageLabel.setText("An error occurred: " + ex.getMessage());
            }
        });

        return panel;
    }


    public JPanel createAddClassPanel() {
        JPanel addClassPanel = new JPanel();
        addClassPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        // Course Name
        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addClassPanel.add(courseNameLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField courseNameField = new JTextField(30);
        courseNameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addClassPanel.add(courseNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Semester Name
        JLabel semesterLabel = new JLabel("Semester:");
        semesterLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addClassPanel.add(semesterLabel, gbc);
        gbc.gridx = 1;
        JTextField semesterField = new JTextField(30);
        semesterField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addClassPanel.add(semesterField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Professor's ID
        JLabel professorIDLabel = new JLabel("Professor's ID:");
        professorIDLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addClassPanel.add(professorIDLabel, gbc);
        gbc.gridx = 1;
        JTextField professorIDField = new JTextField(30);
        professorIDField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addClassPanel.add(professorIDField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Max Class Capacity
        JLabel maxClassCapacityLabel = new JLabel("Max Class Capacity:");
        maxClassCapacityLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addClassPanel.add(maxClassCapacityLabel, gbc);
        gbc.gridx = 1;
        JTextField maxClassCapacityField = new JTextField(30);
        maxClassCapacityField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addClassPanel.add(maxClassCapacityField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Location
        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addClassPanel.add(locationLabel, gbc);
        gbc.gridx = 1;
        JTextField locationField = new JTextField(30);
        locationField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addClassPanel.add(locationField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        // Time
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addClassPanel.add(timeLabel, gbc);
        gbc.gridx = 1;
        JTextField timeField = new JTextField(30);
        timeField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        addClassPanel.add(timeField, gbc);

        // Center and make the button the same width and increase button height
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addClassButton = new JButton("Add Class");
        addClassButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        addClassButton.setPreferredSize(new Dimension(200, 30));
        addClassPanel.add(addClassButton, gbc);

        // Create a JLabel for displaying messages
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel messageLabel = new JLabel();
        messageLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        addClassPanel.add(messageLabel, gbc);

        addClassButton.addActionListener(e -> {
            String courseName = courseNameField.getText();
            String semesterName = semesterField.getText();
            int professorID;
            int maxClassCapacity;

            try {
                professorID = Integer.parseInt(professorIDField.getText());
                maxClassCapacity = Integer.parseInt(maxClassCapacityField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(addClassPanel, "Professor ID and Max Class Capacity must be valid numbers.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String location = locationField.getText();
            String time = timeField.getText();

            if (courseName.isEmpty() || semesterName.isEmpty() || location.isEmpty() || time.isEmpty()) {
                JOptionPane.showMessageDialog(addClassPanel, "All fields must be filled.", "Missing Information", JOptionPane.ERROR_MESSAGE);
            } else {
                ClassTiming classTiming = new ClassTiming(time, location);
                Schedule schedule = new Schedule(classTiming, location);

                // Call the addClass function and provide user feedback
                String feedback = admin.addClass(university, courseName, semesterName, professorID, maxClassCapacity, schedule);

                // Display user feedback in the result area
                messageLabel.setText(feedback);
            }
        });

        return addClassPanel;
    }


    public JPanel editCapacityPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create input fields and labels
        JTextField courseNameField = new JTextField(20);
        JTextField semesterNameField = new JTextField(20);
        JTextField newCapacityField = new JTextField(20);
        JTextField sectionNumberField = new JTextField(20);

        // Create the "Edit Capacity" button
        JButton editCapacityButton = new JButton("Edit Capacity");

        // Create a main content panel with GridBagLayout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(new JLabel("Enter the name of the course: "), gbc);
        gbc.gridx = 1;
        contentPanel.add(courseNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Enter the semester: "), gbc);
        gbc.gridx = 1;
        contentPanel.add(semesterNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Enter the new capacity: "), gbc);
        gbc.gridx = 1;
        contentPanel.add(newCapacityField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        contentPanel.add(new JLabel("Enter the section number: "), gbc);
        gbc.gridx = 1;
        contentPanel.add(sectionNumberField, gbc);

        // Create a button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editCapacityButton);

        // Add components to the main panel
        panel.add(contentPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Add an action listener to the "Edit Capacity" button
        editCapacityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();
                String semesterName = semesterNameField.getText();
                String newCapacityStr = newCapacityField.getText();
                String sectionNumberStr = sectionNumberField.getText();

                if (courseName.isEmpty() || semesterName.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Course name and semester are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int newCapacity, sectionNumber;
                try {
                    newCapacity = Integer.parseInt(newCapacityStr);
                    sectionNumber = Integer.parseInt(sectionNumberStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "New capacity and section number must be integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String feedback = admin.editCapacity(university, courseName, semesterName, newCapacity, sectionNumber);

                // Display user feedback using an output dialog
                JOptionPane.showMessageDialog(panel, feedback, "Edit Capacity Result", JOptionPane.INFORMATION_MESSAGE);

                // Clear the input fields
                courseNameField.setText("");
                semesterNameField.setText("");
                newCapacityField.setText("");
                sectionNumberField.setText("");
            }
        });

        return panel;
    }

}
