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

    @Query(value = "SELECT STUDENT.ID, BIRTH_DATE, FIRST_NAME, INDEX_NUMBER, LAST_NAME, AVG(GRADE_VALUE) FROM STUDENT_GRADES \n" +
            "JOIN STUDENT ON STUDENT.ID = STUDENT_GRADES.STUDENT_ID\n" +
            "JOIN GRADE ON GRADE.ID = STUDENT_GRADES.GRADES_ID\n" +
            "WHERE COURSE_ID = 4\n" +
            "GROUP BY STUDENT.ID", nativeQuery = true)
    List<List<Object>> getMeanForAllStudents(@Param("courseId") Long courseId);
}
