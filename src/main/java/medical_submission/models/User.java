package medical_submission.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String userid;

	private String firstname;

	private String lastname;

	private String mobile;

	private String address;

	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@Size(max = 120)
	private String password;

	private String courseId;

	private String departmentId;

	private String resetPasswordToken;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	public User() {
	}

	public User(String userid, String firstname, String lastname,
				String mobile, String address,String username,
				String email, String password, String courseId,
				String departmentId, String resetPasswordToken) {
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.address = address;
		this.username = username;
		this.email = email;
		this.password = password;
		this.courseId = courseId;
		this.departmentId = departmentId;
		this.resetPasswordToken = resetPasswordToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() { return userid; }

	public void setUserid(String userid) { this.userid = userid; }

	public String getFirstname() { return firstname; }

	public void setFirstname(String firstname) { this.firstname = firstname; }

	public String getLastname() { return lastname; }

	public void setLastname(String lastname) { this.lastname = lastname; }

	public String getMobile() { return mobile; }

	public void setMobile(String mobile) { this.mobile = mobile; }

	public String getAddress() { return address; }

	public void setAddress(String address) { this.address = address; }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCourseId() { return courseId; }

	public void setCourseId(String courseId) { this.courseId = courseId; }

	public String getDepartmentId() { return departmentId; }

	public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

	public String getResetPasswordToken() { return resetPasswordToken; }

	public void setResetPasswordToken(String resetPasswordToken) { this.resetPasswordToken = resetPasswordToken; }

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
