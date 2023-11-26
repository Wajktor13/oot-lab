package pl.edu.agh.to.school;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.course.CourseRepository;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.grade.GradeRepository;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentRepository;

import java.time.LocalDate;

@Configuration
public class SchoolApplicationConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, CourseRepository courseRepository,
                                        GradeRepository gradeRepository) {
        return args -> {

//            // students
//            Student student1 = new Student("Jan", "Kowalski", LocalDate.now(), "123456");
//            studentRepository.save(student1);
//
//            Student student2 = new Student("Mateusz", "Woźniak", LocalDate.now(), "111111");
//            studentRepository.save(student2);
//
//            Student student3 = new Student("Adam", "Nowak", LocalDate.now(), "555111");
//            studentRepository.save(student3);
//
//            // courses
//            Course course1 = new Course("Calculus");
//            course1.assignStudent(student1);
//            course1.assignStudent(student2);
//            courseRepository.save(course1);
//
//            Course course2 = new Course("Algorithms and Data Structures");
//            course2.assignStudent(student1);
//            course2.assignStudent(student3);
//            courseRepository.save(course2);
//
//            // grades
//            Grade grade1 = new Grade(1.0F, course1);
//            gradeRepository.save(grade1);
//
//            Grade grade2 = new Grade(2.0F, course1);
//            gradeRepository.save(grade2);
//
//            Grade grade3 = new Grade(3.0F, course2);
//            gradeRepository.save(grade3);
//
//            Grade grade4= new Grade(4.0F, course1);
//            gradeRepository.save(grade4);
//
//            Grade grade5 = new Grade(5.0F, course2);
//            gradeRepository.save(grade5);
//
//            // grade students
//            student1.giveGrade(grade1);
//            student1.giveGrade(grade2);
//            student1.giveGrade(grade3);
//            studentRepository.save(student1);
//
//            student2.giveGrade(grade4);
//            studentRepository.save(student2);
//
//            student3.giveGrade(grade5);
//            studentRepository.save(student3);
        };
    }
}

