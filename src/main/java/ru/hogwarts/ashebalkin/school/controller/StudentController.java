package ru.hogwarts.ashebalkin.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.ashebalkin.school.model.Student;
import ru.hogwarts.ashebalkin.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping(params = {"age"})
    public ResponseEntity<Collection<Student>> getStudensByAge(@RequestParam(value = "age", required = false) int age) {
        Collection<Student> studensByAge = studentService.getStudensByAge(age);
        if (studensByAge.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studensByAge);
    }

    @GetMapping(params = {"ageMin", "ageMax"})
    public ResponseEntity<Collection<Student>> getStudensByAgeRange(@RequestParam(value = "ageMin", required = false) int ageMin,
                                                                    @RequestParam(value = "ageMax", required = false) int ageMax) {
        Collection<Student> studensByAgeRange = studentService.getStudensByAgeRange(ageMin, ageMax);
        if (studensByAgeRange.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studensByAgeRange);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student studentForUpdate = studentService.updateStudent(student);
        if (studentForUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(studentForUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allstudentcount")
    public Integer getAllStudentsCount() {
        return studentService.getAllStudentsCount();
    }

    @GetMapping("/allstudentsavgage")
    public Integer getAllStudentsAvgAge() {
        return studentService.getAllStudentsAvgAge();
    }

    @GetMapping("/lastfivestudents")
    public ResponseEntity<Collection<Student>> getLastFiveStudents() {
        Collection<Student> lastFiveStudents = studentService.getLastFiveStudents();
        if (lastFiveStudents.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(lastFiveStudents);
    }

}
