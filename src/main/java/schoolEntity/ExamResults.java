package schoolEntity;

public class ExamResults extends School {

    private Integer examResultId;
    private String studentName;
    private String courseName;
    private Integer examScore;

    public ExamResults(Integer examResultId, String studentName, String courseName, Integer examScore) {
        super(courseName);
        this.examResultId = examResultId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.examScore = examScore;
    }

    public ExamResults(String studentName, String courseName, Integer examScore) {
        super(courseName);
        this.studentName = studentName;
        this.examScore = examScore;
    }

    public Integer getExamResultId() {
        return examResultId;
    }

    public String getStudentName() {
        return studentName;
    }

    public Integer getExamScore() {
        return examScore;
    }

    @Override
    public String toString() {
        return "examResultId = " + examResultId +
                ", studentName = " + studentName +
                ", courseName = " + courseName +
                ", examScore = " + examScore;
    }
}