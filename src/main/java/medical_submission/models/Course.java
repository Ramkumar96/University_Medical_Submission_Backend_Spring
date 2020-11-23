package medical_submission.models;

import javax.persistence.*;

@Entity
@Table(name="course")
public class Course {

    @Id
    private String courseId;

    private String courseName;

    private String courseYear;

    private String courseSemester;

    private String departmentId;

    public Course() {
    }

    public Course(String courseId, String courseName, String courseYear, String courseSemester, String departmentId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseYear = courseYear;
        this.courseSemester = courseSemester;
        this.departmentId = departmentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }

    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
        this.courseSemester = courseSemester;
    }

    public String getDepartmentId() { return departmentId; }

    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }
}
