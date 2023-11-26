package pl.edu.agh.to.school.student;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.course.CourseService;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.grade.GradeService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    private final GradeService gradeService;

    public StudentService(StudentRepository studentRepository, CourseService courseService, GradeService gradeService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentByIndexNumber(String index) {
        return studentRepository.getStudentByIndexNumber(index);
    }

    @Transactional
    public void giveGrade(Long studentId, Long courseId, Float gradeValue) {
        Optional<Student> studentOptional = this.studentRepository.findById(studentId);
        Optional<Course> courseOptional = this.courseService.getCourseById(courseId);

        if (studentOptional.isPresent() && courseOptional.isPresent()) {
            Student student = studentOptional.get();
            Grade grade = this.gradeService.addGrade(gradeValue, courseOptional.get());
            student.giveGrade(grade);
            this.studentRepository.save(student);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "student or course not found");
        }
    }

    public Float getMeanGrade(Long studentId) {
        return this.studentRepository.getMeanGrade(studentId);
    }
}
