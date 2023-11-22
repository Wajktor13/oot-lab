package pl.edu.agh.to.school.course;

import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentService;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    @Id
    private Long id;

    private String name;

    @OneToMany
    private final List<Student> students = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public Course() {

    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return this.name;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void assignStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}
