package repository;

import database.ConnectionManager;
import schoolEntity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepository {

    private final ConnectionManager connectionManager = new ConnectionManager();

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ArrayList<Student> getAllStudents(){

        ArrayList<Student> students = new ArrayList<>();

        try {
            String query = "SELECT * FROM students";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.resultSet = statement.executeQuery();

            while (resultSet.next()){
                students.add(this.createStudentFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return students;
    }

    private Student createStudentFromResultSet(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getInt("studentId"), resultSet.getString("studentName"), resultSet.getString("courseName"));
    }

    public Student getStudentById(Integer studentId){

        Student student = null;

        try{
            String query = "SELECT * FROM students WHERE studentId = ?";
            this.connection = connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setInt(1, studentId);
            this.resultSet = this.statement.executeQuery();
            while(resultSet.next()) student = this.createStudentFromResultSet(this.resultSet);

        } catch (SQLException exception){
            this.handleException(exception);
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }
        return student;
    }

    private void handleException(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public String createStudent(Student student) {
        String response;

        try{
            String query = "INSERT IGNORE INTO students(studentName, courseName) VALUES (?, ?)";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, student.getStudentName());
            this.statement.setString(2, student.getCourseName());
            int result = statement.executeUpdate();

            if(result == 1){
                response = "Student created successfully!";
            } else {
                throw new SQLException("Problem occurred creating student");
            }

        } catch (SQLException e){
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }

    public String updateStudent(Student student) {
        String response;

        try{
            String query = "UPDATE students SET studentName = ?, courseName = ? WHERE studentId = ?";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, student.getStudentName());
            this.statement.setString(2, student.getCourseName());
            this.statement.setInt(3, student.getStudentId());
            int result = statement.executeUpdate();

            if(result == 1){
                response = "Student was updated successfully!";
            } else {
                throw new SQLException("Problem occurred updating student");
            }

        } catch (SQLException e){
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }

    public String deleteStudent(Integer studentId) {
        String response;

        try {
            String query = "DELETE FROM students WHERE studentId = ?";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setInt(1, studentId);
            int result = statement.executeUpdate();

            if (result == 1) {
                response = "Student was deleted successfully!";
            } else {
                throw new SQLException("Problem occurred deleting student");
            }

        } catch (SQLException e) {
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }
}