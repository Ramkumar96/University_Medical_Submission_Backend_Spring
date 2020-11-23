package medical_submission.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String userid;
	private String firstname;
	private String lastname;
	private String mobile;
	private String address;
	private String username;
	private String email;
	private String courseId;
	private String departmentId;
	private List<String> roles;

//	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
//		this.token = accessToken;
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.roles = roles;
//	}


	public JwtResponse(String accessToken,Long id, String userid,
					   String firstname, String lastname, String mobile, String address,
					   String username, String email, String courseId,
					   String departmentId, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobile = mobile;
		this.address = address;
		this.username = username;
		this.email = email;
		this.courseId = courseId;
		this.departmentId = departmentId;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCourseId() { return courseId; }

	public void setCourseId(String courseId) { this.courseId = courseId; }

	public String getDepartmentId() { return departmentId; }

	public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

	public List<String> getRoles() {
		return roles;
	}
}
