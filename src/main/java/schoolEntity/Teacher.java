package schoolEntity;

public class Teacher extends School {

    private Integer teacherId;
    private String teacherName;
    private String courseName;

    public Teacher(Integer teacherId, String teacherName, String courseName) {
        super(courseName);
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.courseName = courseName;
    }

    public Teacher(String teacherName, String courseName) {
        super(courseName);
        this.teacherName = teacherName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    @Override
    public String toString() {
        return "teacherId = " + teacherId +
                ", teacherName = " + teacherName +
                ", courseName = " + courseName;
    }
}