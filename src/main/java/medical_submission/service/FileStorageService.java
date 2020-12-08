package medical_submission.service;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import medical_submission.models.FileDB;
import medical_submission.repository.FileDBRepository;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file , String userid, String date, String category,
                        String courseId, String departmentId,  Boolean acceptedByStaff,
                        Boolean deletedByStaff, Boolean acceptedByLecturer,Boolean deletedByLecturer ) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes() , userid ,
                date , category ,courseId ,departmentId , acceptedByStaff,
                deletedByStaff,acceptedByLecturer,deletedByLecturer);

        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

}
