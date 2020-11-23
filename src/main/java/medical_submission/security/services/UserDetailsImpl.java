package medical_submission.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import medical_submission.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String userid;

	private String firstname;

	private String lastname;

	private String mobile;

	private String address;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private String courseId;

	private String departmentId;

	private Collection<? extends GrantedAuthority> authorities;

//	public UserDetailsImpl(Long id, String username, String email, String password,
//			Collection<? extends GrantedAuthority> authorities) {
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.authorities = authorities;
//	}


	public UserDetailsImpl(Long id, String userid, String firstname, String lastname,
						   String mobile, String address, String username, String email,
						   String password, String courseId, String departmentId,
						   Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
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
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(),
				user.getUserid(),
				user.getFirstname(),
				user.getLastname(),
				user.getMobile(),
				user.getAddress(),
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(),
				user.getCourseId(),
				user.getDepartmentId(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getUserid() {
		return userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getCourseId() {
		return courseId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
