package pl.edu.agh.to.school.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.school.student.Student;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT s FROM Student s " +
            "WHERE s.id IN (SELECT student.id FROM Course c, IN(c.students) student WHERE c.id = :courseId)")
    List<Student> getAssignedStudents(@Param("courseId") Long courseId);
}
