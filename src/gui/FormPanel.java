package gui;

import com.company.models.*;

import javax.swing.SwingUtilities;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class FormPanel extends JPanel {
    private JTextField idField;
    private JPasswordField passwordField;
    private UserType userType;
    private StudentNavMenu studentNavMenu;
    private University university;
    private Student student; // Add a field to store the Student object set in FormPanel
    private Professor professor;
    private Administrator admin;
    private Consumer<Student> studentSetListener;
    private Consumer<Professor> professorSetListener;
    private Consumer<Administrator> adminSetListener;
    private landing parent;

    public FormPanel(landing parentFrame, University university) {
        this.university = university;
        this.parent = parentFrame;
        setLayout(new GridLayout(0, 1, 10, 10));

        JLabel label = new JLabel("Login");
        label.setFont(new Font("sansserif", Font.BOLD, 30));
        label.setForeground(new Color(7, 164, 121));
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label);

        JLabel idLabel = new JLabel("University ID:");
        idLabel.setForeground(new Color(7, 164, 121));
        idLabel.setFont(new Font("sansserif", Font.BOLD, 15));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(7, 164, 121));
        passwordLabel.setFont(new Font("sansserif", Font.BOLD, 15));

        idField = new JTextField();
        passwordField = new JPasswordField();

        add(idLabel);
        add(idField);
        add(passwordLabel);
        add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        CustomButton submitButton = new CustomButton("Submit");
        // Make the button the same green color
        submitButton.setBackground(new Color(7, 164, 121));
        buttonPanel.add(submitButton);

        add(buttonPanel);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Submit button clicked"); // Add this line
                String id = idField.getText();

                try {
                    boolean isAdmin =false;
                    int idInt = Integer.parseInt(id);
                    String password = new String(passwordField.getPassword());

                    if(id.startsWith("1"))
                        isAdmin = true;

                    if (id.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if(!isAdmin&&(university.findStudentByID(idInt)==null || university.findProfessorByID(idInt)==null)){
                        JOptionPane.showMessageDialog(null, "Invalid ID", "Error", JOptionPane.ERROR_MESSAGE);
                        //set fields to empty
                        idField.setText("");
                        passwordField.setText("");
                    }
                        else {
                        if (id.startsWith("9")) {
                            userType = UserType.STUDENT;
                            parentFrame.updateUserTypeAndMenu(userType); // Update the menu and user type
                            student = university.findStudentByID(idInt); // Replace with actual student data
                            //formPanelStudent = student; // Set the student object in FormPanel
                            parentFrame.setStudent(student);
                            parentFrame.switchToPanel(parentFrame.getStudentHomePanel());
                            // Notify the listener that the student is set
                            if (studentSetListener != null) {
                                System.out.println("studentSetListener is not null");
                                studentSetListener.accept(student);
                            }else{
                                System.out.println("studentSetListener is null");
                            }
                        } else if (id.startsWith("8")) {
                            userType = UserType.PROFESSOR;
                            parentFrame.updateUserTypeAndMenu(userType); // Update the menu and user type
                            professor = university.findProfessorByID(idInt); // Replace with actual student data
                            parentFrame.setProfessor(professor);
                            parentFrame.switchToPanel(parentFrame.getProfessorHomePanel());

                            if(professorSetListener != null){
                                professorSetListener.accept(professor);
                            }
                        } else if (id.startsWith("1")) {
                            userType = UserType.ADMIN;
                            admin = new Administrator(university.getName(), 1001, "admin1234");
                            parentFrame.setAdmin(admin);
                            parentFrame.updateUserTypeAndMenu(userType); // Update the menu and user type
                            parentFrame.switchToPanel(parentFrame.getAdminHomePanel());

                            if(adminSetListener != null){
                                adminSetListener.accept(admin);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        idField.setText("");
                        passwordField.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    idField.setText("");
                    passwordField.setText("");
                }
            }
        });
    }

    public UserType getUserType() {
        return userType;
    }

    // Add a method to register a listener for student set events.
    public void addStudentSetListener(Consumer<Student> listener) {
        this.studentSetListener = listener;
        // Check if student is available and not null
        if (student != null) {
            this.studentSetListener.accept(student); // Notify the listener with the current student
        }
    }

    public void addProfessorSetListener(Consumer<Professor> listener) {
        this.professorSetListener = listener;
        // Check if student is available and not null
        if (professor != null) {
            this.professorSetListener.accept(professor); // Notify the listener with the current student
        }
    }

    public void addAdminSetListener(Consumer<Administrator> listener) {
        this.adminSetListener = listener;
        // Check if student is available and not null
        if (admin != null) {
            this.adminSetListener.accept(admin); // Notify the listener with the current student
        }
    }
}

class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setBackground(new Color(7, 164, 121));
        setForeground(Color.WHITE);
        setFont(new Font("sansserif", Font.BOLD, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(0, 123, 91));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(7, 164, 121));
            }
        });
    }
}
