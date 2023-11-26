package pl.edu.agh.to.school.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getStudentByIndexNumber(@Param("index") String index);

    @Query(value = "SELECT AVG(GRADE_VALUE) FROM STUDENT_GRADES" +
            " JOIN GRADE ON GRADE.ID = STUDENT_GRADES.GRADES_ID" +
            " WHERE STUDENT_GRADES.STUDENT_ID = :studentId", nativeQuery = true)
    Float getMeanGrade(@Param("studentId") Long studentId);
}
