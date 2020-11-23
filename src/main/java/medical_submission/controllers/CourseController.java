package medical_submission.controllers;

import medical_submission.models.Course;
import medical_submission.repository.CourseRepository;
import medical_submission.repository.DepartmentRepository;
import medical_submission.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseRepository courseRepository;


    public CourseController(CourseRepository courseRepository) {
        super();
        this.courseRepository = courseRepository;
    }

    @GetMapping("/allcourses")
    public Optional<Course> findById(String id) {

        return courseRepository.findById("PST11201");
    }

}




