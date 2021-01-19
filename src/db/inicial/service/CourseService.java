package db.inicial.service;

import db.inicial.DAO.CourseDAO;
import db.inicial.model.Course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/*
Validaciones con la base de datos
Invocación al DAO
 */

public class CourseService {
    public static List<Course> findAll(Connection connection) throws SQLException {
         return CourseDAO.findAll(connection);

    }

    public static void insert(Connection connection, String courseName) throws Exception {
        Course insertCourse = getByName(courseName, connection);
        if (insertCourse == null) {
            Course newCourse = new Course(courseName);
            CourseDAO.insert(connection, newCourse);
            System.out.println("The course wad added succesfully");
        } else {
            throw new Exception("Course with name " + courseName + " already exists" );
        }

    }

    public static void update(Connection connection, Course editCourse) throws SQLException {
        CourseDAO.update(editCourse.getName(), editCourse.getId(), connection);
        System.out.println("Course updated");
    }

    public static void delete(Connection connection, int idCourse) throws Exception {
        Course deleteCourse = CourseService.getById(idCourse, connection);
        if (deleteCourse == null) {
            throw new Exception("Course with id " + idCourse + " not found");
        }
        CourseDAO.delete(idCourse, connection);
    }

    public static boolean idIsValid(int idCourse, Connection connection) throws SQLException {
       if( (CourseDAO.findById(idCourse, connection) == null)){
           System.err.println("Invalid Id");
           return false;
       }
       return true;
    }

    public static Course getById(int idCourse, Connection connection) throws SQLException {
        return CourseDAO.findById(idCourse, connection);
    }
    private static Course getByName(String name, Connection connection) throws SQLException {
        return CourseDAO.findByName(name, connection);
    }


}
