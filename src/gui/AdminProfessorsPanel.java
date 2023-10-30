package gui;
import com.company.models.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminProfessorsPanel extends JPanel {
    private AdminHomePanel parentPanel;
    private University university;
    Administrator admin;

    public AdminProfessorsPanel(AdminHomePanel parentPanel, University university, Administrator admin) {
        this.parentPanel = parentPanel;
        this.university = university;
        this.admin = admin;
        setLayout(new BorderLayout());
    }

    public JPanel createAddProfessorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Reduce the margin

        // Professor Name
        JLabel professorNameLabel = new JLabel("Professor Name:");
        professorNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(professorNameLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField professorNameField = new JTextField(20); // Reduce the width
        professorNameField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panel.add(professorNameField, gbc);

        // Department
        JLabel departmentLabel = new JLabel("Department:");
        departmentLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(departmentLabel, gbc);

        gbc.gridx = 1;
        JTextField departmentField = new JTextField(20); // Reduce the width
        departmentField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        panel.add(departmentField, gbc);

        // Center and make the button the same width and increase button height
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addProfessorButton = new JButton("Add Professor");
        addProfessorButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        addProfessorButton.setPreferredSize(new Dimension(200, 40)); // Increase the button height
        panel.add(addProfessorButton, gbc);

        addProfessorButton.addActionListener(e -> {
            try {
                String professorName = professorNameField.getText();
                String department = departmentField.getText();

                // Validate input and handle exceptions
                if (professorName.isEmpty() || department.isEmpty()) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                // Call the addProfessor function and provide user feedback
                admin.addProfessor(university, professorName, department);

                // Display a success message using an output dialog
                JOptionPane.showMessageDialog(panel, "Professor added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Clear the input fields
                professorNameField.setText("");
                departmentField.setText("");
            } catch (Exception ex) {
                // Handle exceptions
                JOptionPane.showMessageDialog(panel, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }


}
