package db.inicial;

import db.inicial.DAO.AdminDB;
import db.inicial.controller.CourseController;
import db.inicial.controller.TeacherController;
import db.inicial.menu.MainMenu;
import db.inicial.util.InputUtil;
import db.inicial.util.PrintUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AppCourses {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        InputUtil input = InputUtil.getInstance();
        try {
            PrintUtil.drawSign("Connecting to database","");
            Connection connection = AdminDB.obtenerConexion();
            PrintUtil.printMessage("Connection successful");

        int mainMenuOption = MainMenu.showMenu();
        while (mainMenuOption != 0) {
            try {
                switch (mainMenuOption) {
                    case 1:
                        student(connection);
                        break;
                    case 2:
                        teacher(connection);
                        break;
                    case 3:
                        course(connection);
                        break;
                    case 4:
                        enrollment(connection);
                        break;
                    default:
                        PrintUtil.printMessage("Invalid option");
                        break;
                } mainMenuOption = MainMenu.showMenu();


            } catch(SQLException e){
                PrintUtil.printMessage("Changes could not be uploaded to the database");
            } catch (Exception e){
                System.err.println("Unexpected error");
            }

        }

        }catch(SQLException e){
            PrintUtil.printMessage("Error accesing database");
        }
        PrintUtil.drawSign("Exit", "*");

    }

    private static void enrollment(Connection connection) {
    }

    private static void teacher(Connection connection) throws Exception {
        int teacherAction = TeacherController.showMenuAndChoose();
        if (teacherAction!= 0) TeacherController.teacherMenuOptions(connection, teacherAction);
    }

    private static void student(Connection connection) {
    }

    private static void course(Connection connection) throws SQLException {
      int courseAction = CourseController.showMenuAndChoose();
      if (courseAction!= 0) CourseController.courseMenuOptions(connection, courseAction);
    }
}
