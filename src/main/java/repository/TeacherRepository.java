package repository;

import database.ConnectionManager;
import schoolEntity.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TeacherRepository {

    private final ConnectionManager connectionManager = new ConnectionManager();

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ArrayList<Teacher> getAllTeachers(){

        ArrayList<Teacher> teachers = new ArrayList<>();

        try {
            String query = "SELECT * FROM teachers";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.resultSet = statement.executeQuery();

            while (resultSet.next()){
                teachers.add(this.createTeacherFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return teachers;
    }

    private Teacher createTeacherFromResultSet(ResultSet resultSet) throws SQLException {
        return new Teacher(
                resultSet.getInt("teacherId"), resultSet.getString("teacherName"), resultSet.getString("courseName"));
    }

    public Teacher getTeacherById(Integer teacherId){

        Teacher teacher = null;

        try{
            String query = "SELECT * FROM teachers WHERE teacherId = ?";
            this.connection = connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setInt(1, teacherId);
            this.resultSet = this.statement.executeQuery();
            while(resultSet.next()) teacher = this.createTeacherFromResultSet(this.resultSet);

        } catch (SQLException exception){
            this.handleException(exception);
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }
        return teacher;
    }

    private void handleException(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public String createTeacher(Teacher teacher){
        String response;

        try {
            String query = "INSERT IGNORE INTO teachers(teacherName, courseName) VALUES (?, ?)";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, teacher.getTeacherName());
            this.statement.setString(2, teacher.getCourseName());
            int result = statement.executeUpdate();

            if (result == 1) {
                response = "Teacher was created successfully!";
            } else {
                throw new SQLException("Problem occurred creating teacher");
            }

        } catch (SQLException e){
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }
            
        return response;
    }

    public String updateTeacher(Teacher teacher) {
        String response;

        try{
            String query = "UPDATE teachers SET teacherName = ?, courseName = ? WHERE teacherId = ?";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, teacher.getTeacherName());
            this.statement.setString(2, teacher.getCourseName());
            this.statement.setInt(3, teacher.getTeacherId());

            int result = statement.executeUpdate();

            if(result == 1){
                response = "Teacher was updated successfully!";
            } else {
                throw new SQLException("Problem occurred updating teacher");
            }

        } catch (SQLException e){
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }

    public String deleteTeacher(Integer teacherId) {
        String response;

        try {
            String query = "DELETE FROM teachers WHERE teacherId = ?";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setInt(1, teacherId);
            int result = statement.executeUpdate();

            if (result == 1) {
                response = "Teacher was deleted successfully!";
            } else {
                throw new SQLException("Problem occurred deleting teacher");
            }

        } catch (SQLException e) {
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }
}