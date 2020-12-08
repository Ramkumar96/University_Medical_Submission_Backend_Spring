package medical_submission.payload.response;

public class ResponseFile {
    private String name;
    private String url;
    private String type;
    private long size;
    private String id;
    private String userid;
    private String date;
    private String category;
    private String courseId;
    private String departmentId;
    private Boolean acceptedByStaff;
    private Boolean deletedByStaff;
    private Boolean acceptedByLecturer;
    private Boolean deletedByLecturer;

    public ResponseFile(String name, String url, String type, long size, String id,
                        String userid, String date, String category, String courseId,
                        String departmentId, Boolean acceptedByStaff, Boolean deletedByStaff,
                        Boolean acceptedByLecturer, Boolean deletedByLecturer) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
        this.id = id;
        this.userid = userid;
        this.date = date;
        this.category = category;
        this.courseId = courseId;
        this.departmentId = departmentId;
        this.acceptedByStaff = acceptedByStaff;
        this.deletedByStaff = deletedByStaff;
        this.acceptedByLecturer = acceptedByLecturer;
        this.deletedByLecturer = deletedByLecturer;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUserid() { return userid; }

    public void setUserid(String userid) { this.userid = userid; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getCourseId() { return courseId; }

    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getDepartmentId() { return departmentId; }

    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public Boolean getAcceptedByStaff() { return acceptedByStaff; }

    public void setAcceptedByStaff(Boolean acceptedByStaff) { this.acceptedByStaff = acceptedByStaff; }

    public Boolean getDeletedByStaff() { return deletedByStaff; }

    public void setDeletedByStaff(Boolean deletedByStaff) { this.deletedByStaff = deletedByStaff; }

    public Boolean getAcceptedByLecturer() { return acceptedByLecturer; }

    public void setAcceptedByLecturer(Boolean acceptedByLecturer) { this.acceptedByLecturer = acceptedByLecturer; }

    public Boolean getDeletedByLecturer() { return deletedByLecturer; }

    public void setDeletedByLecturer(Boolean deletedByLecturer) { this.deletedByLecturer = deletedByLecturer; }
}
