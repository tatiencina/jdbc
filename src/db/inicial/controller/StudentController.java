package db.inicial.controller;

import db.inicial.DAO.StudentDAO;
import db.inicial.menu.CRUDMenu;
import db.inicial.model.Student;
import db.inicial.service.StudentService;
import db.inicial.util.InputUtil;
import db.inicial.util.PrintUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentController {

    public static int showMenuAndChoose() {
        return CRUDMenu.showCrudMenu("Student");
    }
    public static void studentMenuOptions (Connection connection, int menuSelection) throws Exception {
        InputUtil input = InputUtil.getInstance();
        int mainMenuSelection = 1;
        while (mainMenuSelection == 1) {
            switch (menuSelection) {
                case 1:
                    createNewStudent(connection);
                    break;
                case 2:
                    findAll(connection);
                    break;
                case 3:
                    updateStudent(connection);
                    break;
                case 4:
                    deleteStudent(connection);
                    break;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println();
            System.out.println("1) Continue working in this category 0) Return to main Menu");

            mainMenuSelection = input.getInt("Selection: ");
            if (mainMenuSelection != 1) {
                PrintUtil.printMessage("Returning to main menu...");
            } else {
                menuSelection = showMenuAndChoose();
            }
        }

    }

    private static void deleteStudent(Connection connection) {
        InputUtil input = InputUtil.getInstance();
        int idStudent = input.getInt("STudent ID: ");
        try {
            Student deleteStudent = StudentService.getById(idStudent, connection) ;
            PrintUtil.printMessage("Your are about to delete the student = " + deleteStudent.toString());
            StudentService.delete(connection, deleteStudent);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void updateStudent(Connection connection) throws Exception {
        InputUtil input = InputUtil.getInstance();
        int idStudent = input.getInt("Student ID: ");
        try {
            Student updateStudent = StudentService.getById(idStudent, connection);
            PrintUtil.printMessage("Your are about to edit the student = " + updateStudent.toString());
            String name = input.getString("Name: ");
            String lastName = input.getString("Lastname: ");
            updateStudent.setName(name);
            updateStudent.setLastName(lastName);
            StudentService.update(connection, updateStudent);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

        private static void createNewStudent(Connection connection) {
            PrintUtil.printMessage("Create new student: ");
            InputUtil input = InputUtil.getInstance();
            String studentName = input.getString("Name:");
            String studentLastName = input.getString("Lastname: ");
            try {
                StudentService.insert(connection, studentName, studentLastName);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }


        public static void findAll(Connection connection) throws SQLException, SQLException {
            System.out.println("Student list");
            List<Student> students = StudentDAO.findAll(connection);
            printStudents(students);
        }

        private static void printStudents(List<Student> students) {
            if(students.size() == 0) {
                System.err.println("Students list is empty");
            } else {
                for(Student student: students) {
                    System.out.println(student);
                }
            }
        }
}
