package db.inicial.service;

import db.inicial.DAO.StudentDAO;
import db.inicial.DAO.TeacherDAO;
import db.inicial.model.Student;
import db.inicial.model.Teacher;
import db.inicial.util.PrintUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentService {
    /*public static List<Teacher> findAll(Connection connection) throws SQLException {
        return TeacherDAO.findAll(connection);

    }*/

    public static void insert(Connection connection, String studentName, String studentLastName) throws SQLException {
        /*
        buscar por nombre y por apellido, mostrar resultados similares, confirmar operación
        Si la búsqueda devuelve nulo hacer insert
        Si la búsqueda devuelve algú nresultado, confirmar el insert con el usuario.
         */
        Student insertStudent = getByNameandLastName(studentName, studentLastName, connection);
        if (insertStudent == null){
            Student newStudent = new Student(studentName, studentLastName);
            StudentDAO.insert(connection, newStudent);
            System.out.println("The student wad added succesfully");
        } else {
            PrintUtil.printMessage("That student already exists. ");

        }

    }

    public static Student getByNameandLastName(String studentName, String studentLastName, Connection connection) throws SQLException {
        return StudentDAO.findByNameAndLastName(studentName, studentLastName, connection);
    }

    public static void findAll(Connection connection) throws SQLException, SQLException {
        System.out.println("Student list");
        List<Student> students = StudentDAO.findAll(connection);
        printStudent(students);
    }

    private static void printStudent(List<Student> students) {
    }

    private static void printTeachers(List<Student> students) {
        if(students.size() == 0) {
            System.err.println("Students list is empty");
        } else {
            for(Student student: students) {
                System.out.println(student);
            }
        }
    }

    public static void update(Connection connection, Student student) throws Exception {
        StudentDAO.update(student, connection);
        System.out.println("Student updated");
    }

    public static Student getById(int idStudent, Connection connection) throws Exception {
        if (StudentDAO.findById(idStudent, connection) == null){
            throw new Exception ("Student with id " + idStudent + " not found");
        } else {
            return StudentDAO.findById(idStudent, connection);
        }
    }

    public static void delete(Connection connection, Student deleteStudent) throws SQLException {

    }
}
