package db.inicial.service;

import db.inicial.DAO.TeacherDAO;
import db.inicial.model.Teacher;
import db.inicial.util.PrintUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TeacherService {
    /*public static List<Teacher> findAll(Connection connection) throws SQLException {
        return TeacherDAO.findAll(connection);

    }*/

    public static void insert(Connection connection, String teacherName, String teacherLastName) throws SQLException {
        /*
        buscar por nombre y por apellido, mostrar resultados similares, confirmar operación
        Si la búsqueda devuelve nulo hacer insert
        Si la búsqueda devuelve algú nresultado, confirmar el insert con el usuario.
         */
        Teacher insertTeacher = getByNameandLastName(teacherName, teacherLastName, connection);
        if (insertTeacher == null){
            Teacher newTeacher = new Teacher(teacherName, teacherLastName);
            TeacherDAO.insert(connection, newTeacher);
            System.out.println("The teacher wad added succesfully");
        } else {
            PrintUtil.printMessage("That teacher already exists. ");

        }

    }

    public static Teacher getByNameandLastName(String teacherName, String teacherLastName, Connection connection) throws SQLException {
        return TeacherDAO.findByNameAndLastName(teacherName, teacherLastName, connection);
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

    public static void update(Connection connection, Teacher teacher) throws Exception {
        TeacherDAO.update(teacher, connection);
        System.out.println("Teacher updated");
    }

    public static Teacher getById(int idTeacher, Connection connection) throws Exception {
        if (TeacherDAO.findById(idTeacher, connection) == null){
            throw new Exception ("Teacher with id " + idTeacher + " not found");
        } else {
            return TeacherDAO.findById(idTeacher, connection);
        }
    }

    public static void delete(Connection connection, Teacher deleteTeacher) throws SQLException {
        TeacherDAO.delete(deleteTeacher.getId(), connection);
        System.out.println("Teacher deleted");

    }
}
