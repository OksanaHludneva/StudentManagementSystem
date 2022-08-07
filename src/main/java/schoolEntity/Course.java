package schoolEntity;

public class Course extends School {

    private Integer courseId;
    private String courseName;

    public Course(Integer courseId, String courseName) {
        super(courseName);
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Course(String courseName) {
        super(courseName);
        this.courseName = courseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public String toString() {
        return "courseId = " + courseId +
                ", courseName = " + courseName;
    }
}