package pl.edu.agh.to.school.student;

import pl.edu.agh.to.school.grade.Grade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String indexNumber;

    @OneToMany
    private final List<Grade> grades = new ArrayList<>();

    public Student(String firstName, String lastName, LocalDate birthDate, String indexNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.indexNumber = indexNumber;
    }

    public Student() {

    }

    public long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public String getIndexNumber() {
        return this.indexNumber;
    }

//    public giveGrade()
}
