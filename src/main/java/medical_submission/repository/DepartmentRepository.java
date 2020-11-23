package medical_submission.repository;

import medical_submission.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department , String> {

    Optional<Department> findById(String departmentId);
}
