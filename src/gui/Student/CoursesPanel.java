package gui.Student;
import com.company.models.Course;
import com.company.models.Class;
import com.company.models.Student;
import com.company.models.University;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CoursesPanel extends JPanel {
    private StudentHomePanel parentPanel;
    private University university;
    private Student student;

    public CoursesPanel(StudentHomePanel parentPanel, University university) {
        this.parentPanel = parentPanel;
        this.university = university;
        this.student = parentPanel.getStudent();
        setLayout(new BorderLayout());
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public JPanel viewCoursesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for viewing courses by semester
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        inputPanel.add(new JLabel("Enter the semester: "));
        JTextField semesterField = new JTextField(20);
        inputPanel.add(semesterField);
        JButton viewCoursesButton = new JButton("View Courses");
        inputPanel.add(viewCoursesButton);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Department");

        JTable courseTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(courseTable);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = courseTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String semesterName = semesterField.getText();

                // Implement logic to display available courses based on user input
                ArrayList<Course> courses = university.getAvailableCourses(semesterName);

                // Clear the existing table data
                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }

                // Update the table with course data
                for (Course course : courses) {
                    tableModel.addRow(new Object[]{course.getName(), course.getDepartment()});
                }

                if(courses.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No courses available for this semester");
                }
            }
        });

        return panel;
    }

    public JPanel viewCoursesByDepartmentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for viewing courses by department
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        inputPanel.add(new JLabel("Enter the department: "));
        JTextField departmentField = new JTextField(20);
        inputPanel.add(departmentField);
        JButton viewCoursesButton = new JButton("View Courses");
        inputPanel.add(viewCoursesButton);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name"); // Include only the "Course Name" column

        JTable courseTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(courseTable);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = courseTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size
        tableHeader.setReorderingAllowed(false); // Prevent column reordering

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentName = departmentField.getText();

                // Implement logic to display available courses based on user input
                ArrayList<Course> courses = university.searchCourseByDepartment(departmentName);

                // Clear the existing table data
                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }

                // Update the table with course data
                for (Course course : courses) {
                    tableModel.addRow(new Object[]{course.getName()}); // Include only the "Course Name" data
                }

                if(courses.size() == 0){
                    JOptionPane.showMessageDialog(null, "No courses found for this department");
                }
            }
        });

        return panel;
    }

    public JPanel viewCoursesByProfessorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for viewing courses by instructor
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        inputPanel.add(new JLabel("Enter the instructor: "));
        JTextField instructorField = new JTextField(20);
        inputPanel.add(instructorField);
        JButton viewCoursesButton = new JButton("View Courses");
        inputPanel.add(viewCoursesButton);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name"); // Include only the "Course Name" column

        JTable courseTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(courseTable);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = courseTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size
        tableHeader.setReorderingAllowed(false); // Prevent column reordering

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String instructorName = instructorField.getText();

                // Implement logic to display available courses based on user input
                ArrayList<Course> courses = university.searchCourseByProfessor(instructorName);

                // Clear the existing table data
                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }

                // Update the table with course data
                for (Course course : courses) {
                    tableModel.addRow(new Object[]{course.getName()}); // Include only the "Course Name" data
                }

                if(courses.size() == 0){
                    JOptionPane.showMessageDialog(null, "No courses found for this instructor");
                }
            }
        });

        return panel;
    }

    public JPanel viewCourseDetailsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for viewing course details
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        inputPanel.add(new JLabel("Enter the course name: "));
        JTextField courseNameField = new JTextField(20);
        inputPanel.add(courseNameField);
        JButton viewDetailsButton = new JButton("View Course Details");
        inputPanel.add(viewDetailsButton);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Department");
        tableModel.addColumn("Description");

        JTable courseTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(courseTable);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = courseTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size
        tableHeader.setReorderingAllowed(false); // Prevent column reordering

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();

                // Implement logic to get course details based on user input
                Course course = university.getCourseByName(courseName);

                if (course != null) {
                    // Clear the existing table data
                    tableModel.setRowCount(0);

                    // Update the table with course details
                    tableModel.addRow(new Object[]{course.getName(), course.getDepartment(), course.getDescription()});
                } else {
                    // Display a message if the course is not found
                    JOptionPane.showMessageDialog(panel, "Course not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    public JPanel getCourseSchedulePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for viewing course schedule
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        inputPanel.add(new JLabel("Enter the semester: "));
        JTextField semesterField = new JTextField(20);
        inputPanel.add(semesterField);
        JButton viewScheduleButton = new JButton("View Course Schedule");
        inputPanel.add(viewScheduleButton);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name");
        tableModel.addColumn("Semester");
        tableModel.addColumn("Day of the Week");
        tableModel.addColumn("Time");
        tableModel.addColumn("Location");

        JTable scheduleTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(scheduleTable);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = scheduleTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size
        tableHeader.setReorderingAllowed(false); // Prevent column reordering

        viewScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String semesterName = semesterField.getText();

                // Clear the existing table data
                tableModel.setRowCount(0);

                // Implement logic to display course schedule based on user input
                for (Course course : university.getCourses()) {
                    for (Class class1 : course.getClasses()) {
                        if (class1.getSemester().getName().equals(semesterName)) {
                            tableModel.addRow(new Object[]{
                                    course.getName(),
                                    class1.getSemester().getName(),
                                    class1.getSchedule().getClassTimings().getDayOfWeek(),
                                    class1.getSchedule().getClassTimings().getTime(),
                                    class1.getSchedule().getLocation()
                            });
                        }
                    }
                }
            }
        });

        return panel;
    }


    public JPanel addToFavoritesPanel() {
        student = parentPanel.getStudent();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for adding a course to favorites
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        inputPanel.add(new JLabel("Enter the course name: "));
        JTextField courseNameField = new JTextField(20);
        inputPanel.add(courseNameField);
        JButton addToFavoritesButton = new JButton("Add to Favorites");
        inputPanel.add(addToFavoritesButton);

        JTextArea resultArea = new JTextArea(4, 40);
        resultArea.setEditable(false);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(resultArea, BorderLayout.CENTER);

        addToFavoritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();

                // Implement logic to add the course to favorites and provide user feedback
                String feedback = student.addCourseToFavorites(university, courseName);

                // Display user feedback in the result area
                resultArea.setText(feedback);
            }
        });

        return panel;
    }

    public JPanel removeCourseFromFavorites(){
        student = parentPanel.getStudent();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for removing a course from favorites
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        inputPanel.add(new JLabel("Enter the course name: "));
        JTextField courseNameField = new JTextField(20);
        inputPanel.add(courseNameField);
        JButton removeFromFavoritesButton = new JButton("Remove from Favorites");
        inputPanel.add(removeFromFavoritesButton);

        JTextArea resultArea = new JTextArea(4, 40);
        resultArea.setEditable(false);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(resultArea, BorderLayout.CENTER);

        removeFromFavoritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();

                // Implement logic to remove the course from favorites and provide user feedback
                String feedback = student.removeCourseFromFavorites(university, courseName);

                // Display user feedback in the result area
                resultArea.setText(feedback);
            }
        });

        return panel;
    }

    public JPanel viewFavoriteCoursesPanel() {
        student = parentPanel.getStudent();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for viewing favorites
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton viewFavoritesButton = new JButton("View Favorites");
        inputPanel.add(viewFavoritesButton);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Course Name");

        JTable favoritesTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(favoritesTable);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(tableScrollPane, BorderLayout.CENTER);

        // Increase the font size of the header cells
        JTableHeader tableHeader = favoritesTable.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(new Font(headerFont.getName(), Font.BOLD, 16)); // Change 16 to the desired font size
        tableHeader.setReorderingAllowed(false); // Prevent column reordering

        viewFavoritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the existing table data
                tableModel.setRowCount(0);

                // Implement logic to display favorites
                for (Course course : student.getFavoriteCourses()) {
                    tableModel.addRow(new Object[]{course.getName()});
                }

                //if there are no courses in the favorites list, say in the text area that there are no courses
                if (student.getFavoriteCourses().size() == 0) {
                    JOptionPane.showMessageDialog(panel, "There are no courses in your favorites list.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        return panel;
    }

    public JPanel enrollInCoursePanel() {
        student = parentPanel.getStudent();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Add components for enrolling in a course
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel courseNameLabel = new JLabel("Enter the course name: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 5, 5); // Add some padding
        inputPanel.add(courseNameLabel, constraints);

        JTextField courseNameField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        inputPanel.add(courseNameField, constraints);

        JLabel semesterLabel = new JLabel("Enter the semester: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPanel.add(semesterLabel, constraints);

        JTextField semesterField = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        inputPanel.add(semesterField, constraints);

        JButton enrollButton = new JButton("Enroll in Course");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2; // Make the button span two columns
        inputPanel.add(enrollButton, constraints);

        JTextArea resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(resultArea, BorderLayout.CENTER);

        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName = courseNameField.getText();
                String semesterName = semesterField.getText();

                // Implement logic to enroll in the course
                String feedback = student.enrollInCourse(university, courseName, semesterName);

                // Display feedback in the result area
                resultArea.setText(feedback);
            }
        });

        return panel;
    }


}
