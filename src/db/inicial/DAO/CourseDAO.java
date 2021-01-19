package db.inicial.DAO;

import db.inicial.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public static void insert(Connection connection, Course newCourse) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO COURSE (name) VALUES(?)");
        prepStmt.setString(1, newCourse.getName());
        prepStmt.executeUpdate();
    }
    public static List<Course> findAll(Connection connection) throws SQLException {
        List<Course> courseList = new ArrayList<Course>();
        String sql = "SELECT * FROM COURSE";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String courseName = rs.getString(2);
            int courseId = rs.getInt(1);
            Course course = new Course(courseName);
            course.setId(courseId);
            courseList.add(course);
        }

        return courseList;
    }

    public static int delete (int idCurso, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM COURSE WHERE idcourse = ?");
        prepStmt.setInt(1, idCurso);
        return prepStmt.executeUpdate();
    }

    public static void update (String name, int idCurso, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("UPDATE COURSE SET name = ? WHERE idcourse = ?" );
        prepStmt.setString(1, name);
        prepStmt.setInt(2,idCurso);
        prepStmt.executeUpdate();

    }


    public static Course findById(int courseId, Connection connection) throws SQLException {
        PreparedStatement prepStmt = connection.prepareStatement("SELECT NAME FROM COURSE WHERE idcourse = ?");
        prepStmt.setInt(1, courseId);
        ResultSet rs = prepStmt.executeQuery();
        Course course = null;
        while (rs.next()) {
            String courseName = rs.getString(1);
            course = new Course(courseName);
            course.setId(courseId);
            //  course = new Course(rs.getInt(1), rs.getString(2));
        }
        return course;
    }
    public static List<Course> findBySimilarName(String name, Connection connection) throws SQLException {
        List<Course> courseList = new ArrayList<Course>();
        // String sql = "SELECT FROM CURSO WHERE NAME = ?";
        String sql = "SELECT * FROM COURSE WHERE NAME LIKE '%" + name + "%' ORDER BY NAME";
        // SELECT * FROM FROM courses WHERE cName = '" + courseName + "%'"
        PreparedStatement prepStmt = connection.prepareStatement(sql);
        //  prepStmt.setString(1,name);
        ResultSet rs = prepStmt.executeQuery();
        Course course = null;
        while (rs.next()){
            course = new Course (rs.getString(2));
            int courseId = rs.getInt(1);
            course.setId(courseId);
            courseList.add(course);
        }

        return courseList;
    }


    public static Course findByName(String name, Connection connection) throws SQLException {
        String sql = "SELECT * FROM COURSE WHERE NAME = ?";
        PreparedStatement prepStmt = connection.prepareStatement(sql);
        prepStmt.setString(1,name);
        ResultSet rs = prepStmt.executeQuery();
        Course course = null;
        while (rs.next()) {
            course = new Course (rs.getString(2));
            int courseId = rs.getInt(1);
            course.setId(courseId);
        }
        return course;
    }
}
