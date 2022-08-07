package schoolEntity;

public class Student extends School {

    private Integer studentId;
    private String studentName;
    private String courseName;

    public Student(Integer studentId, String studentName, String courseName) {
        super(courseName);
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public Student(String studentName, String courseName) {
        super(courseName);
        this.studentName = studentName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "studentId = " + studentId +
                ", studentName = " + studentName +
                ", courseName = " + courseName;
    }
}