package ru.hogwarts.ashebalkin.school.service;

import ru.hogwarts.ashebalkin.school.model.Student;

import java.util.Collection;

public interface StudentService {

    Student createStudent(Student student);

    Student getStudent(Long id);

    Student updateStudent(Student student);

    Student deleteStudent(Long id);

    Collection<Student> getStudensByAge(int age);
}
