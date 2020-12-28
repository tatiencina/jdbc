package db.inicial.DAO;

import db.inicial.model.Student;

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

    public static void update(String name, String lastName, int studentId, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("UPDATE STUDENT SET name = ? , lastname = ? WHERE ID = ?");
        prepStmt.setInt(3, studentId);
        prepStmt.setString(1, name);
        prepStmt.setString(2, lastName);
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
}
