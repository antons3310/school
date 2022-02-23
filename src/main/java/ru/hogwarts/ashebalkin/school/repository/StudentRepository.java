package ru.hogwarts.ashebalkin.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.ashebalkin.school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);

    List<Student> findByAgeBetween(int ageMin, int ageMax);
}
