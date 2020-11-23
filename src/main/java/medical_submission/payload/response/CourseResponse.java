package medical_submission.payload.response;

import medical_submission.models.Department;

import javax.persistence.*;

public class CourseResponse {


    private String courseId;
    private String courseName;
    private String courseYear;
    private String courseSemester;
    private Department department;


    public CourseResponse(String courseId, String courseName, String courseYear,
                          String courseSemester, Department department) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseYear = courseYear;
        this.courseSemester = courseSemester;
        this.department = department;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
