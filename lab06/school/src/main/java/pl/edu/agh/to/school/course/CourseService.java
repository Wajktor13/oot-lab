package pl.edu.agh.to.school.course;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentRepository;
import pl.edu.agh.to.school.student.StudentService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
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

    public Map<Student, Float> getMeanForAllStudents(Long courseId) {
        List<List<Object>> resultList = this.courseRepository.getMeanForAllStudents(courseId);
        Student student;
        Float meanGrade;
        Map<Student, Float> meanGradesMap = new HashMap<>();

        for (List<Object> innerResult : resultList) {
            meanGrade = ((BigDecimal) innerResult.get(innerResult.size() - 1)).floatValue();
            student = this.studentRepository.getReferenceById(((BigInteger) innerResult.get(0)).longValue());

            meanGradesMap.put(student, meanGrade);
        }

        return meanGradesMap;
    }
}
