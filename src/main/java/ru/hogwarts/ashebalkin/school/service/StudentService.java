package ru.hogwarts.ashebalkin.school.service;

import ru.hogwarts.ashebalkin.school.model.Student;

import java.util.Collection;

public interface StudentService {

    Student createStudent(Student student);

    Student getStudent(Long id);

    Student updateStudent(Student student);

    void deleteStudent(Long id);

    Collection<Student> getStudensByAge(int age);

    Collection<Student> getStudensByAgeRange(int ageMin, int ageMax);

    Integer getAllStudentsCount();

    Integer getAllStudentsAvgAge();

    Collection<Student> getLastFiveStudents();

    Collection<String> getAllStudentsWithAName();

    Double getAllStudentsAvgAgeWithStream();

    void getStudentsStream();

    void getStudentsStreamSynchronized();
}
