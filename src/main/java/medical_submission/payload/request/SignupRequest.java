package medical_submission.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {

    private String userid;

    private String firstname;

    private String lastname;

    private String mobile;

    private String address;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
    private Set<String> role;
    

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
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
