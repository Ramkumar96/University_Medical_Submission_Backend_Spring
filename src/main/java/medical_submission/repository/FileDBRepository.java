package medical_submission.repository;

import medical_submission.models.FileDB;
import medical_submission.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;


@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

//    boolean existsById(String id);
//User findByEmail(String email);

//        FileDB findByCategory(String category);

}