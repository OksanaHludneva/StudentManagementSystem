package schoolEntity;

public abstract class School {

    private String courseName;

    public School(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}