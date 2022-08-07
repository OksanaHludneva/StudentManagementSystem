import repository.CourseRepository;
import repository.StudentRepository;
import repository.TeacherRepository;
import repository.ExamResultsRepository;
import schoolEntity.Course;
import schoolEntity.ExamResults;
import schoolEntity.Student;
import schoolEntity.Teacher;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        StudentRepository studentRepository = new StudentRepository();
        System.out.println(studentRepository.getAllStudents());
        System.out.println(studentRepository.createStudent(new Student("Paul Sanders", "Forensic Medicine")));
        Student student = studentRepository.getStudentById(2);

        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student with given ID not found");
        }

        student = studentRepository.getStudentById(3);

        if (student != null) {
            System.out.println(studentRepository.updateStudent(
                    new Student(student.getStudentId(), "Eli Evans", "Forensic Medicine")));
        } else {
            System.out.println("Student with given ID not found");
        }

        student = studentRepository.getStudentById(5);
        System.out.println(studentRepository.deleteStudent(student.getStudentId()));

        TeacherRepository teacherRepository = new TeacherRepository();
        System.out.println(teacherRepository.getAllTeachers());
        System.out.println(teacherRepository.createTeacher(new Teacher("PROF Sara Turner", "Forensic Medicine")));
        Teacher teacher = teacherRepository.getTeacherById(2);

        if (teacher != null) {
            System.out.println(teacher);
        } else {
            System.out.println("Teacher with given ID not found");
        }

        teacher = teacherRepository.getTeacherById(3);

        if (teacher != null) {
            System.out.println(teacherRepository.updateTeacher(
                    new Teacher(teacher.getTeacherId(),"PROF Kevin Spicy", "Entrepreneurship")));
        } else {
            System.out.println("Teacher with given ID not found");
        }

        teacher = teacherRepository.getTeacherById(5);
        System.out.println(teacherRepository.deleteTeacher(teacher.getTeacherId()));

        CourseRepository courseRepository = new CourseRepository();
        System.out.println(courseRepository.getAllCourses());
        System.out.println(courseRepository.createCourse(new Course("Forensic Medicine")));
        Course course = courseRepository.getCourseById(3);

        if (course != null){
            System.out.println(course);
        } else {
            System.out.println("Course with given ID not found");
        }

        course = courseRepository.getCourseById(5);

        if (course != null) {
            System.out.println(courseRepository.updateCourse(
                    new Course(course.getCourseId(), "General Medicine")));
        } else {
            System.out.println("Course with given ID not found");
        }

        course = courseRepository.getCourseById(4);
        System.out.println(courseRepository.deleteCourse(course.getCourseId()));

        ExamResultsRepository examResultsRepository = new ExamResultsRepository();
        System.out.println(examResultsRepository.getAllExamResults());
        System.out.println(examResultsRepository.createExamResults(new ExamResults(
                "Robert Harris", "Practical Medicine", 9)));
        ExamResults examResults = examResultsRepository.getExamResultsById(3);

        if (examResults != null){
            System.out.println(examResults);
        } else {
            System.out.println("Exam result with given ID not found");
        }

        examResults = examResultsRepository.getExamResultsById(4);

        if (examResults != null) {
            System.out.println(examResultsRepository.updateExamResults(
                    new ExamResults(examResults.getExamResultId(), "Erik Lee", "Entrepreneurship", 10)));
        } else {
            System.out.println("Exam result with given ID not found");
        }
    }
}