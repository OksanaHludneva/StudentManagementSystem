package repository;

import database.ConnectionManager;
import schoolEntity.ExamResults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamResultsRepository {

    private final ConnectionManager connectionManager = new ConnectionManager();

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    public ArrayList<ExamResults> getAllExamResults(){

        ArrayList<ExamResults> examResults = new ArrayList<>();

        try {
            String query = "SELECT * FROM examResults";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.resultSet = statement.executeQuery();

            while (resultSet.next()){
                examResults.add(this.createExamResultsFromResultSet(resultSet));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return examResults;
    }

    private ExamResults createExamResultsFromResultSet(ResultSet resultSet) throws SQLException {
        return new ExamResults(
                resultSet.getInt("examResultId"),
                resultSet.getString("studentName"),
                resultSet.getString("courseName"),
                resultSet.getInt("examScore"));
    }

    public ExamResults getExamResultsById(Integer examResultId){

        ExamResults examResults = null;

        try{
            String query = "SELECT * FROM examResults WHERE examResultId = ?";
            this.connection = connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setInt(1, examResultId);
            this.resultSet = this.statement.executeQuery();
            while(resultSet.next()) examResults = this.createExamResultsFromResultSet(this.resultSet);

        } catch (SQLException exception){
            this.handleException(exception);
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }
        return examResults;
    }

    private void handleException(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public String createExamResults(ExamResults examResults){
        String response;

        try{
            String query = "INSERT INTO examResults(studentName, courseName, examScore) VALUES (?, ?, ?)";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, examResults.getStudentName());
            this.statement.setString(2, examResults.getCourseName());
            this.statement.setInt(3, examResults.getExamScore());
            int result = statement.executeUpdate();

            if(result == 1){
                response = "Exam result was created successfully!";
            } else {
                throw new SQLException("Problem occurred creating exam result");
            }

        } catch (SQLException e){
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }

    public String updateExamResults(ExamResults examResults) {
        String response;

        try{
            String query = "UPDATE examResults SET studentName = ?, courseName = ?, examScore = ? WHERE examResultId = ?";
            this.connection = this.connectionManager.getConnection();
            this.statement = connection.prepareStatement(query);
            this.statement.setString(1, examResults.getStudentName());
            this.statement.setString(2, examResults.getCourseName());
            this.statement.setInt(3, examResults.getExamScore());
            this.statement.setInt(4, examResults.getExamResultId());
            int result = statement.executeUpdate();

            if(result == 1){
                response = "Exam result was updated successfully!";
            } else {
                throw new SQLException("Problem occurred updating exam result");
            }

        } catch (SQLException e){
            response = e.getMessage();
        } finally {
            this.connectionManager.closeOpenConnections(connection, statement, resultSet);
        }

        return response;
    }
}