package medical_submission.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import medical_submission.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import medical_submission.models.FileDB;
import medical_submission.payload.response.MessageResponse;
import medical_submission.payload.response.ResponseFile;
import medical_submission.service.FileStorageService;

@Controller
@CrossOrigin("http://localhost:8081")
@RequestMapping("/medical")
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private FileDBRepository fileDBRepository;

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userid") String userid,
            @RequestParam("date") String date,
            @RequestParam("category") String category,
            @RequestParam("courseId") String courseId,
            @RequestParam("departmentId") String departmentId,
            @RequestParam("acceptedByStaff") Boolean acceptedByStaff,
            @RequestParam("deletedByStaff") Boolean deletedByStaff,
            @RequestParam("acceptedByLecturer") Boolean acceptedByLecturer,
            @RequestParam("deletedByLecturer") Boolean deletedByLecturer){
        String message = "";
        try {
            storageService.store(file ,userid ,date ,category, courseId,departmentId, acceptedByStaff,
                    deletedByStaff,acceptedByLecturer,deletedByLecturer );

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/medical/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getId(),
                    dbFile.getUserid(),
                    dbFile.getDate(),
                    dbFile.getCategory(),
                    dbFile.getCourseId(),
                    dbFile.getDepartmentId(),
                    dbFile.getAcceptedByStaff(),
                    dbFile.getDeletedByStaff(),
                    dbFile.getAcceptedByLecturer(),
                    dbFile.getDeletedByLecturer());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

//    Download File
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

//    Delete by id
    @DeleteMapping("/files/{id}")
    ResponseEntity<?> deleteFile(@PathVariable String id){
        fileDBRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //updateAcceptedByStaff
    @PutMapping("/files/acceptedByStaff/{id}")
    ResponseEntity<?> updateAcceptedByStaff( @RequestParam("acceptedByStaff") Boolean acceptedByStaff , @PathVariable String id) {
        Optional<FileDB> existingFile = fileDBRepository.findById(id);
        existingFile.ifPresent((FileDB file) -> {
            file.setAcceptedByStaff(acceptedByStaff);
            fileDBRepository.save(file);
        });
        return ResponseEntity.ok().build();
    }


    //updateDeletedByStaff
    @PutMapping("/files/deletedByStaff/{id}")
    ResponseEntity<?> updateDeletedByStaff ( @RequestParam("deletedByStaff") Boolean deletedByStaff , @PathVariable String id) {
        Optional<FileDB> existingFile = fileDBRepository.findById(id);
        existingFile.ifPresent((FileDB file) -> {
            file.setDeletedByStaff(deletedByStaff);
            fileDBRepository.save(file);
        });
        return ResponseEntity.ok().build();
    }

    //updateAcceptedByLecturer
    @PutMapping("/files/acceptedByLecturer/{id}")
    ResponseEntity<?> updateAcceptedByLecturer ( @RequestParam("acceptedByLecturer") Boolean acceptedByLecturer , @PathVariable String id) {
        Optional<FileDB> existingFile = fileDBRepository.findById(id);
        existingFile.ifPresent((FileDB file) -> {
            file.setAcceptedByLecturer(acceptedByLecturer);
            fileDBRepository.save(file);
        });
        return ResponseEntity.ok().build();
    }

    //updateDeletedByLecturer
    @PutMapping("/files/deletedByLecturer/{id}")
    ResponseEntity<?> updateDeletedByLecturer ( @RequestParam("deletedByLecturer") Boolean deletedByLecturer , @PathVariable String id) {
        Optional<FileDB> existingFile = fileDBRepository.findById(id);
        existingFile.ifPresent((FileDB file) -> {
            file.setDeletedByLecturer(deletedByLecturer);
            fileDBRepository.save(file);
        });
        return ResponseEntity.ok().build();
    }
}
