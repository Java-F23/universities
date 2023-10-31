package gui;
import com.company.DataInitializer;
import com.company.models.*;
import gui.Admin.*;
import gui.Professor.ProfNavMenu;
import gui.Professor.ProfessorHomePanel;
import gui.Professor.ProfessorPanels;
import gui.Student.CoursesPanel;
import gui.Student.GradesPanel;
import gui.Student.StudentHomePanel;
import gui.Student.StudentNavMenu;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class landing extends JFrame{
    private CoursesPanel coursesPanel;
    private GradesPanel gradesPanel;
    private ProfessorPanels professorPanels;
    private AdminCoursesPanel adminCoursesPanel;
    private AdminProfessorsPanel adminProfessorsPanel;
    private AdminStudentsPanel adminStudentsPanel;
    private StudentHomePanel studentHomePanel;
    private ProfessorHomePanel professorHomePanel;
    private AdminHomePanel adminHomePanel;
    private FormPanel formPanel;
    private JPanel cardPanel;
    private StudentNavMenu studentNavMenu;
    private ProfNavMenu profNavMenu;
    private AdminNavMenu adminNavMenu;
    private UserType userType;
    private University university;
    private Student student; // Add a field to store the Student object set in FormPanel
    private Professor professor;
    private Administrator admin;

    public landing() {
        setTitle("University Platform");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String uniName = "The American University in Cairo";
        String uniLocation = "New Cairo, Egypt";
        university = new University(uniName, uniLocation);
        admin = new Administrator(university.getName(), 1001, "admin1234");

        formPanel = new FormPanel(this, university);

        userType = formPanel.getUserType();
        //student = formPanel.getFormPanelStudent();
        professorHomePanel = new ProfessorHomePanel(landing.this, profNavMenu, professor);
        adminHomePanel = new AdminHomePanel(landing.this, adminNavMenu, admin, coursesPanel);
        studentHomePanel = new StudentHomePanel(landing.this, studentNavMenu, student);

        studentNavMenu = new StudentNavMenu(landing.this, studentHomePanel, coursesPanel, gradesPanel, university);
        profNavMenu = new ProfNavMenu(landing.this, university, professorHomePanel, professorPanels);
        adminNavMenu = new AdminNavMenu(landing.this, adminHomePanel, university, adminCoursesPanel, adminProfessorsPanel, adminStudentsPanel);

        coursesPanel = new CoursesPanel(studentHomePanel, university);
        gradesPanel = new GradesPanel(studentHomePanel, university);
        professorPanels = new ProfessorPanels (professorHomePanel, university, professor);
        adminCoursesPanel = new AdminCoursesPanel(adminHomePanel, university, admin);
        adminProfessorsPanel = new AdminProfessorsPanel(adminHomePanel, university, admin);
        adminStudentsPanel = new AdminStudentsPanel(adminHomePanel, university, admin);

        coursesPanel.setStudent(student);

        // Set up the CardLayout for panel switching
        cardPanel = new JPanel(new CardLayout());
        cardPanel.add(formPanel, "LoginForm");

        setJMenuBar(null);
        setContentPane(cardPanel);
        setVisible(true);
    }

    public void switchToPanel(JPanel panel) {
        SwingUtilities.invokeLater(() -> {
            panel.add(studentNavMenu);
            setContentPane(panel);
            revalidate();
            repaint();
        });
    }

    public void initializeData() {
        DataInitializer.initializeData(university); // Pass the university object
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void switchToLandingFrame(){
        SwingUtilities.invokeLater(() -> {
            setJMenuBar(null);
            setContentPane(cardPanel);
            revalidate();
            repaint();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            landing app = new landing();

            app.initializeData(); // Initialize the data

            // Add your listener initialization here
            app.formPanel.addStudentSetListener(new Consumer<Student>() {
                @Override
                public void accept(Student student) {
                    app.setStudent(student);
                    app.getStudentHomePanel().setStudent(student);
                }
            });

            app.formPanel.addProfessorSetListener(new Consumer<Professor>() {
                @Override
                public void accept(Professor professor) {
                    app.professor = professor;
                    app.getProfessorHomePanel().setProfessor(professor);
                }
            });

            app.formPanel.addAdminSetListener(new Consumer<Administrator>() {
                @Override
                public void accept(Administrator admin) {
                    app.admin = admin;
                    app.getAdminHomePanel().setAdmin(admin);
                }
            });
            app.setVisible(true);
        });
    }

    public void updateUserTypeAndMenu(UserType userType) {
        this.userType = userType;
        if (userType == UserType.STUDENT) {
            this.studentNavMenu = new StudentNavMenu(this, studentHomePanel, coursesPanel, gradesPanel, university);
        } else if (userType == UserType.PROFESSOR) {
            this.profNavMenu = new ProfNavMenu(landing.this, university, professorHomePanel, professorPanels);

            // Handle professor menu
        } else if (userType == UserType.ADMIN) {
            this.adminNavMenu = new AdminNavMenu(this, adminHomePanel, university, adminCoursesPanel, adminProfessorsPanel, adminStudentsPanel);
            // Handle admin menu
        } else {
            // No menu bar
            this.studentNavMenu = new StudentNavMenu(this, studentHomePanel, coursesPanel, gradesPanel, university);
            setJMenuBar(studentNavMenu);
        }
        revalidate();
        repaint();
    }

    public Student getStudent() {
        return student;
    }

    public StudentHomePanel getStudentHomePanel() {
        return studentHomePanel;
    }

    public ProfessorHomePanel getProfessorHomePanel() {
        return professorHomePanel;
    }

    public AdminHomePanel getAdminHomePanel() {
        return adminHomePanel;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

}
