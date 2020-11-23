package medical_submission.repository;


import medical_submission.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course , String> {

  Optional<Course> findById(String CourseId);

}
