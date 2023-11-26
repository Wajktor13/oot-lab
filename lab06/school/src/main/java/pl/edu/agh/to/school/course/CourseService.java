package pl.edu.agh.to.school.course;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.student.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public List<Student> getAssignedStudents(Long courseId) {
        return this.courseRepository.getAssignedStudents(courseId);
    }

    public Optional<Course> getCourseById(Long id) {
        return this.courseRepository.findById(id);
    }
}
