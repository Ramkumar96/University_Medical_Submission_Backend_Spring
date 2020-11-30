package medical_submission.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "files")
public class FileDB {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    private String userid;

    private String date;

    private String category;

    private String courseId;

    private String departmentId;

    private Boolean accepted;

    public FileDB() {
    }


    public FileDB(String name, String type, byte[] data, String userid, String date,
                  String category, String courseId, String departmentId, Boolean accepted) {

//        this.id=id;
        this.name = name;
        this.type = type;
        this.data = data;
        this.userid = userid;
        this.date = date;
        this.category = category;
        this.courseId = courseId;
        this.departmentId = departmentId;
        this.accepted = accepted;
    }

    public String getId() {
        return id;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public byte[] getData() { return data; }

    public void setData(byte[] data) { this.data = data; }

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