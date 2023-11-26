package pl.edu.agh.to.school.grade;

import pl.edu.agh.to.school.course.Course;

import javax.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue
    private Long id;

    private float gradeValue;

    @OneToOne
    private Course course;

    public Grade() {

    }

    public Long getId() {
        return id;
    }

    public float getGradeValue() {
        return this.gradeValue;
    }

    public Course getCourse() {
        return this.course;
    }

    public Grade(float gradeValue, Course course) {
        this.gradeValue = gradeValue;
        this.course = course;
    }
}
