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
    private Boolean accepted;

//  public ResponseFile(String name, String url, String type, long size, String username, String details) {
//    this.name = name;
//    this.url = url;
//    this.type = type;
//    this.size = size;
//    this.username = username;
//    this.details = details;
//  }

    public ResponseFile(String name, String url, String type, long size, String id ,
                        String userid, String date, String category, String courseId,
                        String departmentId, Boolean accepted) {
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
        this.accepted = accepted;
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

    public Boolean getAccepted() { return accepted; }

    public void setAccepted(Boolean accepted) { this.accepted = accepted; }
}
