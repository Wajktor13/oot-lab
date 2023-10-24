package pl.edu.agh.iisg.to.dao;

import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;

import javax.persistence.PersistenceException;

public class GradeDao extends GenericDao<Grade> {

    public boolean gradeStudent(final Student student, final Course course, final float grade) {
        Grade gradeObj = new Grade(student, course, grade);
        student.addGrade(gradeObj);
        course.addGrade(gradeObj);

        try {
            this.save(gradeObj);
            return true;
        } catch (PersistenceException e) {
            student.removeGrade(gradeObj);
            course.removeGrade(gradeObj);

            e.printStackTrace();
        }

        return false;
    }

}
