package db.inicial.DAO;

import db.inicial.model.Course;
import db.inicial.model.Student;
import db.inicial.model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    public static void insert(Connection connection, Teacher newTeacher) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO TEACHER (name, lastname) VALUES(?,?)");
        prepStmt.setString(1, newTeacher.getName());
        prepStmt.setString(2, newTeacher.getLastName());
        prepStmt.executeUpdate();
    }

    public static List<Teacher> findAll(Connection connection) throws SQLException {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        String sql = "SELECT * FROM TEACHER";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String teacherName = rs.getString(2);
            String teacherLastName = rs.getString(3);
            int idTeacher = rs.getInt(1);
            Teacher teacher = new Teacher(teacherName,teacherLastName);
            teacher.setId(idTeacher);
            teacherList.add(teacher);
        }

        return teacherList;
    }

    public static int delete(int teacherId, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM TEACHER WHERE IDTEACHER = ?");
        prepStmt.setInt(1, teacherId);
        return prepStmt.executeUpdate();
    }

    public static void update(Teacher teacher, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("UPDATE TEACHER SET name = ? , lastname = ? WHERE IDTEACHER = ?");
        prepStmt.setInt(3, teacher.getId());
        prepStmt.setString(1, teacher.getName());
        prepStmt.setString(2, teacher.getLastName());
        prepStmt.executeUpdate();

    }

    public static Teacher findById(int teacherId, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("SELECT * FROM TEACHER WHERE IDTEACHER = ?");
        prepStmt.setInt(1, teacherId);
        ResultSet rs = prepStmt.executeQuery();
        Teacher teacher = null;
        while (rs.next()) {
            String teacherName = rs.getString(2);
            String teacherLastName = rs.getString(3);
            int idTeacher = rs.getInt(1);
            teacher = new Teacher(teacherName,teacherLastName);
            teacher.setId(idTeacher);

        }

        return teacher;
    }

    public static Teacher findByName(Connection connection, String name) throws SQLException {
        String sql = "SELECT * FROM TEACHER WHERE NAME = ?";
        PreparedStatement prepStmt = connection.prepareStatement(sql);
        prepStmt.setString(1,name);
        ResultSet rs = prepStmt.executeQuery();
        Teacher teacher = null;
        while (rs.next()) {
            teacher = new Teacher (rs.getString(2), rs.getString(3));
            int courseId = rs.getInt(1);
            teacher.setId(courseId);
        }
        return teacher;
    }
    public static Teacher findByLastName(Connection connection, String lastName) throws SQLException {
        String sql = "SELECT * FROM TEACHER WHERE LASTNAME = ?";
        PreparedStatement prepStmt = connection.prepareStatement(sql);
        prepStmt.setString(1,lastName);
        ResultSet rs = prepStmt.executeQuery();
        Teacher teacher = null;
        while (rs.next()) {
            teacher = new Teacher (rs.getString(2), rs.getString(3));
            int courseId = rs.getInt(1);
            teacher.setId(courseId);
        }
        return teacher;
    }

    public static Teacher findByNameAndLastName(String teacherName, String teacherLastName, Connection connection) throws SQLException {
        String sql = "SELECT * FROM TEACHER WHERE NAME =? AND LASTNAME = ?";
        PreparedStatement prepStmt = connection.prepareStatement(sql);
        prepStmt.setString(1,teacherName);
        prepStmt.setString(2, teacherLastName);
        ResultSet rs = prepStmt.executeQuery();
        Teacher teacher = null;
        while (rs.next()) {
            teacher = new Teacher (rs.getString(2), rs.getString(3));
            int courseId = rs.getInt(1);
            teacher.setId(courseId);
        }
        return teacher;
    }


}
