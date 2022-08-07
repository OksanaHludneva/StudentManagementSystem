package repository;

import database.ConnectionManager;
import schoolEntity.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseRepository {

    private final ConnectionManager connectionManager = new ConnectionManager();

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ArrayList<Course> getAllCourses(){

        ArrayList<Course> courses = new ArrayList<>();

        try {
            String query = "SELECT * FROM courses";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.resultSet = statement.executeQuery();

            while (resultSet.next()){
                courses.add(this.createCourseFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return courses;
    }

    private Course createCourseFromResultSet(ResultSet resultSet) throws SQLException {
        return new Course(
                resultSet.getInt("courseId"),
                resultSet.getString("courseName"));
    }

    public Course getCourseById(Integer courseId){

        Course course = null;

        try{
            String query = "SELECT * FROM courses WHERE courseId = ?";
            this.connection = connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setInt(1, courseId);
            this.resultSet = this.statement.executeQuery();
            while(resultSet.next()) course = this.createCourseFromResultSet(this.resultSet);

        } catch (SQLException exception){
            this.handleException(exception);
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }
        return course;
    }

    private void handleException(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public String createCourse(Course course){
        String response;

        try{
            String query = "INSERT INTO courses(courseName) VALUES (?)";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, course.getCourseName());
            int result = statement.executeUpdate();

            if(result == 1){
                response = "Course was created successfully!";
            } else {
                throw new SQLException("Problem occurred creating course");
            }

        } catch (SQLException e){
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }

    public String updateCourse(Course course) {
        String response;

        try{
            String query = "UPDATE courses SET courseName = ? WHERE courseId = ?";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, course.getCourseName());
            this.statement.setInt(2, course.getCourseId());
            int result = statement.executeUpdate();

            if(result == 1){
                response = "Course was updated successfully!";
            } else {
                throw new SQLException("Problem occurred updating course");
            }

        } catch (SQLException e){
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }

    public String deleteCourse(Integer courseId) {
        String response;

        try {
            String query = "DELETE FROM courses WHERE courseId = ?";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setInt(1, courseId);
            int result = statement.executeUpdate();

            if (result == 1) {
                response = "Course was deleted successfully!";
            } else {
                throw new SQLException("Problem occurred deleting course");
            }

        } catch (SQLException e) {
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }
}