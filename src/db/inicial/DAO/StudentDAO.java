package db.inicial.DAO;

import db.inicial.model.Student;
import db.inicial.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static void insert(Connection connection, Student newStudent) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO STUDENT (name, lastname) VALUES(?,?)");
        prepStmt.setString(1, newStudent.getName());
        prepStmt.setString(2, newStudent.getLastName());
        prepStmt.executeUpdate();
    }

    public static List<Student> findAll(Connection connection) throws SQLException {
        List<Student> studentList = new ArrayList<Student>();
        String sql = "SELECT * FROM STUDENT";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String studentName = rs.getString(2);
            String studentLastName = rs.getString(3);
            int idStudent = rs.getInt(1);
            Student student = new Student(studentName,studentLastName);
            student.setId(idStudent);
            studentList.add(student);
        }

        return studentList;
    }

    public static int delete(int studentId, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM STUDENT WHERE ID = ?");
        prepStmt.setInt(1, studentId);
        return prepStmt.executeUpdate();
    }


    // actualizar objeto como parametro
    public static void update(Student student, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("UPDATE STUDENT SET name = ? , lastname = ? WHERE ID = ?");
        prepStmt.setInt(3, student.getId());
        prepStmt.setString(1, student.getName());
        prepStmt.setString(2, student.getLastName());
        prepStmt.executeUpdate();

    }

    public static Student findById(int studentId, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM STUDENT WHERE ID = ?");
        prepStmt.setInt(1, studentId);
        ResultSet rs = prepStmt.executeQuery();
        Student student = null;
        while (rs.next()) {
            String studentName = rs.getString(2);
            String studentLastName = rs.getString(3);
            int idStudent = rs.getInt(1);
            student = new Student(studentName,studentLastName);
            student.setId(idStudent);

        }

        return student;
    }

    public static Student findByNameAndLastName(String studentName, String studentLastName, Connection connection) throws SQLException {
        String sql = "SELECT * FROM TEACHER WHERE NAME =? AND LASTNAME = ?";
        PreparedStatement prepStmt = connection.prepareStatement(sql);
        prepStmt.setString(1,studentName);
        prepStmt.setString(2, studentLastName);
        ResultSet rs = prepStmt.executeQuery();
        Student student = null;
        while (rs.next()) {
            student = new Student (rs.getString(2), rs.getString(3));
            int courseId = rs.getInt(1);
            student.setId(courseId);
        }
        return student;

    }
}
