package pl.edu.agh.to.school.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.edu.agh.to.school.course.Course;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    @GetMapping(path = "student")
    public Student getStudentByIndex(@RequestParam String index){
        return this.studentService.getStudentByIndexNumber(index);
    }

    @GetMapping(path = "student/{studentId}/meanGrade")
    public Float getMeanGrade(@PathVariable String studentId) {
        try {
            Long studentIdLong = Long.valueOf(studentId);
            return this.studentService.getMeanGrade(studentIdLong);

        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "wrong id");
        }
    }

    @PutMapping(path = "student/{studentId}/{courseId}/{grade}")
    public void giveGrade(@PathVariable String studentId, @PathVariable String courseId, @PathVariable String grade) {
        try {
            Long studentIdLong = Long.valueOf(studentId);
            Long courseIdLong = Long.valueOf(courseId);
            Float gradeFloat = Float.valueOf(grade);
            this.studentService.giveGrade(studentIdLong, courseIdLong, gradeFloat);

        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "wrong id or grade");
        }
    }
}
