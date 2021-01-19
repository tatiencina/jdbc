package db.inicial.controller;

import db.inicial.DAO.TeacherDAO;
import db.inicial.menu.CRUDMenu;
import db.inicial.model.Teacher;
import db.inicial.service.TeacherService;
import db.inicial.util.InputUtil;
import db.inicial.util.PrintUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TeacherController {

    public static int showMenuAndChoose() {
        return CRUDMenu.showCrudMenu("Teacher");
    }
    public static void teacherMenuOptions (Connection connection, int menuSelection) throws Exception {
        InputUtil input = InputUtil.getInstance();
        int mainMenuSelection = 1;
        while (mainMenuSelection == 1) {
            switch (menuSelection) {
                case 1:
                    createNewTeacher(connection);
                    break;
                case 2:
                    findAll(connection);
                    break;
                case 3:
                    updateTeacher(connection);
                    break;
                case 4:
                    deleteTeacher(connection);
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

    private static void deleteTeacher(Connection connection) {
        InputUtil input = InputUtil.getInstance();
        int idTeacher = input.getInt("Teacher ID: ");
        try {
            Teacher deleteTeacher = TeacherService.getById(idTeacher, connection) ;
            PrintUtil.printMessage("Your are about to delete the teacher = " + deleteTeacher.toString());
            TeacherService.delete(connection, deleteTeacher);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void updateTeacher(Connection connection) throws Exception {
        InputUtil input = InputUtil.getInstance();
        int idTeacher = input.getInt("Teacher ID: ");
        try {
            Teacher updateTeacher = TeacherService.getById(idTeacher, connection) ;
            PrintUtil.printMessage("Your are about to edit the teacher = " + updateTeacher.toString());
            String name = input.getString("Name: ");
            String lastName = input.getString("Lastname: ");
            updateTeacher.setName(name);
            updateTeacher.setLastName(lastName);
            TeacherService.update(connection, updateTeacher);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        /*if (editTeacher == null){
            System.err.println("That ID doesn't match any teacher from de database");
        } else {
            PrintUtil.printMessage("Your are about to edit the teacher = " + editTeacher.toString());
            String name = input.getString("Name: ");
            String lastName = input.getString("Lastname: ");
            // confirmar operacion
            // validar string
            editTeacher.setName(name);
            editTeacher.setLastName(lastName);
            TeacherService.update(connection, editTeacher);
        }*/

    }

    private static void createNewTeacher(Connection connection) {
        PrintUtil.printMessage("Create new teacher: ");
        InputUtil input = InputUtil.getInstance();
        String teacherName = input.getString("Name:");
        String teacherLastName = input.getString("Lastname: ");
        try {
            TeacherService.insert(connection, teacherName, teacherLastName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public static void findAll(Connection connection) throws SQLException, SQLException {
        System.out.println("Teacher list");
        List<Teacher> teachers = TeacherDAO.findAll(connection);
        printTeachers(teachers);
    }

    private static void printTeachers(List<Teacher> teachers) {
        if(teachers.size() == 0) {
            System.err.println("Teachers list is empty");
        } else {
            for(Teacher teacher: teachers) {
                System.out.println(teacher);
            }
        }
    }


}
