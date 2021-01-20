package db.inicial;

import db.inicial.DAO.AdminDB;
import db.inicial.DAO.CourseDAO;
import db.inicial.DAO.StudentDAO;
import db.inicial.model.Course;
import db.inicial.model.Student;
import db.inicial.util.InputUtil;
import db.inicial.util.PrintUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AppJDBC {
    public static void main(String[] args) {
        try {
            InputUtil input = InputUtil.getInstance();
            Scanner scan = new Scanner(System.in);
            Connection connection = AdminDB.obtenerConexion();
            welcomeMessage();
            int menuOption = showMainMenu(input);
            showCategoryMenu(scan, menuOption, connection);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void showCategoryMenu(Scanner scan, int menuOption, Connection connection) throws SQLException {
        String categoryMenu = null;
        int categoryMenuOption = 0;
        while (menuOption!= 4) {
            switch (menuOption) {
                case 1:
                    categoryMenu = "Student";
                    categoryMenuOption = showCrudMenu(categoryMenu);
                    studentCrudMenu(scan, categoryMenuOption, connection);
                    break;
                case 2:
                    categoryMenu = "Course";
                    categoryMenuOption = showCrudMenu(categoryMenu);
                    courseCrudMenu(categoryMenuOption, connection);
                    break;
                case 3:
                    categoryMenu = "Enrollment";
                    showCrudMenu(categoryMenu);
                    break;
                default:
                    System.out.println("Invalid option, choose again");

            }
            menuOption = showMainMenu();

        }
        PrintUtil.drawSign("Goodbye!","-");

    }

    private static void courseCrudMenu(int categoryMenuOption, Connection connection) throws SQLException {
        int courseMenuOpt = 1;
        while (courseMenuOpt == 1) {
            switch (categoryMenuOption) {
                case 1:
                    createNewCourse(connection);
                    break;
                case 2:
                    readCourseList(connection);
                    break;
                case 3:
                    updateCourse(connection);
                    break;
                case 4:
                    deleteCourse(connection);
                    break;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println(" ");
            System.out.println("1) Continue working in this category 2) Return to main Menu");
            Scanner scan = new Scanner(System.in);
            courseMenuOpt = scan.nextInt();
            if (courseMenuOpt != 1){
                PrintUtil.drawSign("Main Menu", "-");
            } else {
                categoryMenuOption = showCrudMenu("Course");
            }
        }

    }

    private static void deleteCourse(Connection connection) throws SQLException {
        System.out.print("Type the Id of the course you wish to delete from the database:");
        Scanner scan = new Scanner(System.in);
        int courseId = scan.nextInt();
        Course course = CourseDAO.findById(courseId, connection);
        if (course != null) {
            int deleted = CourseDAO.delete(courseId, connection);
            if (deleted == 1) {
                System.out.println("Your are about to delete the course = " + course.getId() + ": " + course.getName());
                System.out.print("Confirm operation? (Y/N) -->");
                String choice = scan.next().toUpperCase();
                if (choice.equals("Y")) {
                    CourseDAO.delete(courseId, connection);
                    System.out.print("Course deleted");
                } else {
                    System.out.println("Operation cancelled");
                }
            } else {
                System.err.println("The course couldn't be deleted");
            }

        } else {
            System.err.println("Course not found");
        }

    }

    private static void updateCourse(Connection connection) throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Search for courses by name?");
        System.out.print("Confirm operation? (Y/N) -->");
        String choice = scan.next().toUpperCase();
        if(choice.equals("Y")){
            System.out.println("Type key word to find your course");
            String courseName = scan.next();
            List<Course> courseList = CourseDAO.findBySimilarName(courseName, connection);
            for (Course course : courseList) {
                System.out.println(course.getId() + ": " + course.getName() + " ");
            }
        }
        System.out.print("Type the Id of the course you wish to edit: ");
        int courseId = scan.nextInt();
        Course course = CourseDAO.findById(courseId, connection);
        System.out.println("Your are about to edit the course = " + course.getName());
        System.out.print("Name:");
        String courseName = scan.next();
        System.out.println();
        System.out.print("Confirm operation? (Y/N) -->");
        choice = scan.next().toUpperCase();
        System.out.println();
        if(choice.equals("Y")){
            CourseDAO.update(courseName, courseId, connection);
            System.out.println("Course updated");
        } else {
            System.out.println("Operation cancelled");
        }
        /*
        int isValid = validateCourse(idCourse, coursesList);
		if (isValid == 1) {
			System.out.println();
			System.out.print("¿Está seguro de eliminar este curso? y/n -> ");
			String opt = scan.next();
			if (opt.toUpperCase().equals("Y")) {
				int deleted = CoursesDAO.delete(idCourse, con);
				if (deleted == 1) {
					System.out.println("Registro eliminado");
				} else {
					System.err.println("Registro inexistente");
				}
			} else if (opt.toUpperCase().equals("N")) {
				System.out.println("Registro no eliminado");
			}
		} else {
			System.err.println("Registro inexistente");
		}

         */
    }

    private static void readCourseList(Connection connection) throws SQLException {
        System.out.println("Course list:");
        CourseDAO.findAll(connection);
        List<Course> courseList = CourseDAO.findAll(connection);
        for (Course course : courseList) {
            System.out.println(course.getId() + ": " + course.getName() + " ");
        }
    }

    private static void createNewCourse(Connection connection) throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Course name: ");
        String courseName = scan.next();
        Course newCourse = new Course (courseName);
        CourseDAO.insert(connection, newCourse);
        System.out.println("The course wad added succesfully");
    }

    private static void studentCrudMenu(Scanner scan, int categoryMenuOption, Connection connection) throws SQLException {
        int studentMenuOpt = 1;
        while (studentMenuOpt == 1) {
            switch (categoryMenuOption) {
                case 1:
                    createNewStudent(connection);
                    break;
                case 2:
                    showStudentList(connection);
                    break;
                case 3:
                    updateStudent(scan, connection);
                    break;
                case 4:
                    deleteStudent(connection);
                    break;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println(" ");
            System.out.println("1) Continue working in this category 2) Return to main Menu");
            studentMenuOpt = scan.nextInt();
            if (studentMenuOpt != 1){
                PrintUtil.drawSign("Main Menu", "-");
            } else {
                categoryMenuOption = showCrudMenu("Student");
            }
        }


    }

    private static void deleteStudent(Connection connection) throws SQLException {
        System.out.print("Type the Id of the student you wish to delete from the database:");
        Scanner scan = new Scanner(System.in);
        int studentId = scan.nextInt();
        Student student = StudentDAO.findById(studentId, connection);
        System.out.println("Your are about to delete the student = " + student.getName() + " " +  student.getLastName());
        System.out.print("Confirm operation? (Y/N) -->");
        String choice = scan.next().toUpperCase();
        if(choice.equals("Y")){
            StudentDAO.delete(studentId, connection);
            System.out.print("Student deleted");

        } else {
            System.out.println("Operation cancelled");
        }


    }

    private static void updateStudent(Scanner scan, Connection connection) throws SQLException {
        System.out.print("Type the Id of the student you wish to edit: ");
        int studentId = scan.nextInt();
        Student student = StudentDAO.findById(studentId, connection);
        System.out.println("Your are about to edit the student = " + student.getName() + " " +  student.getLastName());
        System.out.print("Name:");
        String studentName = scan.next();
        System.out.print("Lastname:");
        String studentLastName = scan.next();
        System.out.print("Confirm operation? (Y/N) -->");
        String choice = scan.next().toUpperCase();
        if(choice.equals("Y")){
            student = new Student (studentName, studentLastName);
            StudentDAO.update(student, connection);
            System.out.println("Student updated");
        } else {
            System.out.println("Operation cancelled");
        }

    }

    private static void showStudentList(Connection connection) throws SQLException {
        System.out.println("Student list:");
        StudentDAO.findAll(connection);
        List<Student> studentList = StudentDAO.findAll(connection);
        for (Student student : studentList) {
            System.out.println(student.getId() + ": " + student.getName() + " " + student.getLastName());
        }
    }

    private static void createNewStudent(Connection connection) throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Name: ");
        String studentName = scan.next();
        System.out.print("Lastname: ");
        String studentLastName = scan.next();
        Student newStudent = new Student (studentName, studentLastName);
        StudentDAO.insert(connection, newStudent);
        System.out.println("The student wad added succesfully");
    }

    private static int showCrudMenu(String category) {
        System.out.println("1) Create a new " + category);
        System.out.println("2) Read the list of " + category + "s");
        System.out.println("3) Update an existing " + category);
        System.out.println("4) Delete a " + category);
        System.out.print("What d you want to do?");
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    private static int showMainMenu(InputUtil input) {
        System.out.println("Choose a category to work with");
        System.out.println("1)Students 2) Courses 3) Enrollments 4)Exit");
        return input.getInt("Selection:");
    }
    private static int showMainMenu() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose a category to work with");
        System.out.println("1)Students 2) Courses 3) Enrollments 4)Exit");
        return scan.nextInt();
    }

    private static void welcomeMessage() {
        PrintUtil.drawSign("Welcome", "+");
        PrintUtil.drawSign(" Students, courses and enrollments database","");
        System.out.println();

    }

}
