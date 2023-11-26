package pl.edu.agh.to.school;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.course.CourseRepository;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentRepository;

import java.time.LocalDate;

@Configuration
public class SchoolApplicationConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, CourseRepository courseRepository) {
        return args -> {

            // clear database on run!!

            Student student1 = new Student("Jan", "Kowalski", LocalDate.now(), "123456");
            studentRepository.save(student1);

            Course course1 = new Course("Calculus");
            courseRepository.save(course1);
        };
    }
}

