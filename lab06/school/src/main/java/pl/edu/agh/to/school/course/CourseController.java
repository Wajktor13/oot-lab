package pl.edu.agh.to.school.course;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.to.school.student.Student;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return this.courseService.getAllCourses();
    }

    @GetMapping(path = "{id}/assigned_students")
    public List<Student> getAssignedStudents(@PathVariable String id) {
        try {
            Long idLong = Long.parseLong(id);
            return this.courseService.getAssignedStudents(idLong);

        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "course not found");
        }
    }
}
