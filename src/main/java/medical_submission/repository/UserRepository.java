package medical_submission.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import medical_submission.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	User findByEmail(String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

//	@Query("SELECT c FROM Customer c WHERE c.email = ?1")
//	public User findByEmail(String email);

	public User findByResetPasswordToken(String token);
}
