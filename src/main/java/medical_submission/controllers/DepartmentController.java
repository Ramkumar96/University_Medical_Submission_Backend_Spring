package medical_submission.controllers;

import medical_submission.models.Course;
import medical_submission.models.Department;
import medical_submission.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        super();
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/alldepartments")
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }
}
