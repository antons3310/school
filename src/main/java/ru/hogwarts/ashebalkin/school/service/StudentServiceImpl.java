package ru.hogwarts.ashebalkin.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.ashebalkin.school.model.Student;
import ru.hogwarts.ashebalkin.school.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        logger.info("Was invoked method - createStudent");
        return studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {
        logger.info("Was invoked method - getStudent");
        if (studentRepository.findById(id).isEmpty()) {
            logger.info("No such student with id {}", id);
        }
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        logger.info("Was invoked method - updateStudent");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("Was invoked method - deleteStudent");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getStudensByAge(int age) {
        logger.info("Was invoked method - getStudensByAge");
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> getStudensByAgeRange(int ageMin, int ageMax) {
        logger.info("Was invoked method - getStudensByAgeRange");
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    @Override
    public Integer getAllStudentsCount() {
        logger.info("Was invoked method - getAllStudentsCount");
        return studentRepository.getAllStudentsCount();
    }

    @Override
    public Integer getAllStudentsAvgAge() {
        logger.info("Was invoked method - getAllStudentsAvgAge");
        return studentRepository.getAllStudentsAvgAge();
    }

    @Override
    public Collection<Student> getLastFiveStudents() {
        logger.info("Was invoked method - getLastFiveStudents");
        return studentRepository.getLastFiveStudents();
    }

    @Override
    public Collection<String> getAllStudentsWithAName() {
        logger.info("Was invoked method - getAllStudentsWithAName");

        List<String> stringList = studentRepository.findAll().stream()
                .filter(student -> student.getName().charAt(0) == 'А')
                .map(student -> student.getName().toUpperCase())
                .collect(Collectors.toList());

        if (stringList.isEmpty()) {
            logger.warn("There are no Students in database with name witch starts with A letter");
        }

        return stringList.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public Double getAllStudentsAvgAgeWithStream() {
        logger.info("Was invoked method - getAllStudentsAvgAgeWithStream");

        List<Student> studentList = studentRepository.findAll();

        if (studentList.isEmpty()) {
            logger.warn("There are no Students in database");
        }

        return studentList.stream().mapToInt(Student::getAge).average().orElse(0);
    }

}
