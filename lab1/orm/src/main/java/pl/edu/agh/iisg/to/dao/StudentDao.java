package pl.edu.agh.iisg.to.dao;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Student;

public class StudentDao extends GenericDao<Student> {

    public Optional<Student> create(final String firstName, final String lastName, final int indexNumber) {
        //TODO - implement
        return Optional.empty();
    }

    public Optional<Student> findByIndexNumber(final int indexNumber) {
        //TODO - implement
        return Optional.empty();
    }

    public Map<Course, Float> createReport(final Student student) {
        //TODO additional task
        return Collections.emptyMap();
    }

}
