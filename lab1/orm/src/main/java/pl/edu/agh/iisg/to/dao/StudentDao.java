package pl.edu.agh.iisg.to.dao;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionException;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Student;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class StudentDao extends GenericDao<Student> {

    public Optional<Student> create(final String firstName, final String lastName, final int indexNumber) {
        Student newStudent = new Student(firstName, lastName, indexNumber);

        try {
            this.save(newStudent);
            return Optional.of(newStudent);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<Student> findByIndexNumber(final int indexNumber) {
        try (Session currentSession = this.currentSession()) {
            try {
                Student student = (Student) currentSession
                        .createQuery("Select s FROM Student s WHERE s.indexNumber = :indexNumber",
                                Student.class)
                        .setParameter("indexNumber", indexNumber)
                        .getSingleResult();

                return Optional.ofNullable(student);
            } catch (PersistenceException e){
                e.printStackTrace();
            }
        } catch (SessionException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Map<Course, Float> createReport(final Student student) {
        Map<Course, Float> avgGrades = new HashMap<>();

        try (Session currentSession = this.currentSession()) {
            try {
                Query query = currentSession
                        .createQuery("SELECT course.id, AVG(g.grade) FROM Grade g " +
                                "WHERE student.id = :studentID GROUP BY course.id")
                        .setParameter("studentID", student.id());
                List<Object[]> resultList = query.getResultList();

                for (Object[] result : resultList) {
                    Integer courseId = (Integer) result[0];
                    Double averageGrade = (Double) result[1];
                    Optional<Course> course = (new CourseDao()).findById(courseId);

                    course.ifPresent(value -> avgGrades.put(value, averageGrade.floatValue()));
                }
            } catch (PersistenceException e){
                e.printStackTrace();
            }
        } catch (SessionException e) {
            e.printStackTrace();
        }

        return avgGrades;
    }

}
