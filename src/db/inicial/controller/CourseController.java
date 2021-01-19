package db.inicial.controller;

import db.inicial.DAO.CourseDAO;
import db.inicial.menu.CRUDMenu;
import db.inicial.model.Course;
import db.inicial.service.CourseService;
import db.inicial.util.InputUtil;
import db.inicial.util.PrintUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseController {

    public static int showMenuAndChoose() {
        return CRUDMenu.showCrudMenu("Course");
    }

    public static void courseMenuOptions (Connection connection, int menuSelection) throws SQLException {
        InputUtil input = InputUtil.getInstance();
        int mainMenuSelection = 1;
        while (mainMenuSelection == 1) {
               switch (menuSelection) {
                   case 1:
                       createNewCourse(connection);
                       break;
                   case 2:
                       findAll(connection);
                       break;
                   case 3:
                       Course editCourse = updateCourse(input, connection);
                       CourseService.update(connection, editCourse);
                       break;
                   case 4:
                       deleteCourse(input, connection);
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

    private static void deleteCourse(InputUtil input, Connection connection) throws SQLException {
        int idCourse = input.getInt("Course ID: ");
        try {
            CourseService.delete(connection, idCourse);
            PrintUtil.printMessage("Course deleted");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        /*Course deleteCourse = CourseService.getById(idCourse, connection);
        if (deleteCourse == null) {
            System.err.println("Operation cancelled");
        } else {
            PrintUtil.printMessage("Your are about to delete the course = " + deleteCourse.getName());
            CourseService.delete(connection, deleteCourse.getId());
        }*/
    }

    private static void findAll(Connection connection) throws SQLException {
        List<Course> courseList = CourseService.findAll(connection);
        for (Course course : courseList) {
            System.out.println(course.getId() + ": " + course.getName() + " ");
        }

    }

    private static void createNewCourse(Connection connection) {
        PrintUtil.printMessage("Create new course: ");
        InputUtil input = InputUtil.getInstance();
        String courseName = input.getString("Name:");
        try {
            CourseService.insert(connection, courseName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // validar input
        // longitud de texto
    }

    private static Course updateCourse(InputUtil input, Connection connection) throws SQLException {
        int idCourse = input.getInt("Course ID: ");
        Course editCourse = CourseService.getById(idCourse, connection);
        if (editCourse == null) {
            System.err.println("Operation cancelled");
        } else {
            PrintUtil.printMessage("Your are about to edit the course = " + editCourse.getName());
            String name = input.getString("Name: ");
            // confirmar operacion
            // validar string
            editCourse.setName(name);
        }
        return editCourse;
    }



}
