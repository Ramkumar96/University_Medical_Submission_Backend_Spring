package medical_submission.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import medical_submission.payload.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import medical_submission.models.ERole;
import medical_submission.models.Role;
import medical_submission.models.User;
import medical_submission.payload.request.LoginRequest;
import medical_submission.payload.request.SignupRequest;
import medical_submission.payload.response.JwtResponse;
import medical_submission.payload.response.MessageResponse;
import medical_submission.repository.RoleRepository;
import medical_submission.repository.UserRepository;
import medical_submission.security.jwt.JwtUtils;
import medical_submission.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(),
												 userDetails.getUserid(),
												 userDetails.getFirstname(),
												 userDetails.getLastname(),
												 userDetails.getMobile(),
												 userDetails.getAddress(),
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 userDetails.getCourseId(),
												 userDetails.getDepartmentId(),
												 roles));
	}

	@PostMapping("/adduser")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(
				signUpRequest.getUserid(),
				signUpRequest.getFirstname(),
				signUpRequest.getLastname(),
				signUpRequest.getMobile(),
				signUpRequest.getAddress(),
				signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getCourseId(),
				signUpRequest.getDepartmentId());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

			strRoles.forEach(role -> {
				switch (role) {

				case "student":
					Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(studentRole);

					break;

				case "staff":
					Role staffRole = roleRepository.findByName(ERole.ROLE_STAFF)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(staffRole);

					break;

				case "lecturer":
					Role lecturerRole = roleRepository.findByName(ERole.ROLE_LECTURER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(lecturerRole);

					break;

				case "hod":
					Role hodRole = roleRepository.findByName(ERole.ROLE_HOD)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(hodRole);

					break;

				case "dean":
					Role deanRole = roleRepository.findByName(ERole.ROLE_DEAN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(deanRole);

						break;

				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				}
			});


		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User Added successfully!"));
	}

	@PutMapping("/register")
	ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

		if (userRepository.existsByUsername(registerRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		User existingUser = userRepository.findByEmail(registerRequest.getEmail());

		existingUser.setUsername(registerRequest.getUsername());
		existingUser.setPassword(encoder.encode(registerRequest.getPassword()));

		User result = userRepository.save(existingUser);

		return ResponseEntity.ok(new MessageResponse("User Registered successfully!"));
	}
}
