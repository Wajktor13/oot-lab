package pl.edu.agh.to.school.grade;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.school.course.Course;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public Grade addGrade(Float gradeValue, Course course) {
        Grade grade = new Grade(gradeValue, course);

        this.gradeRepository.save(grade);

        return grade;
    }
}
