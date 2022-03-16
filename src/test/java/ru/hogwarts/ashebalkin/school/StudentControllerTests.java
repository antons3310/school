package ru.hogwarts.ashebalkin.school;

import netscape.javascript.JSObject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import ru.hogwarts.ashebalkin.school.controller.StudentController;
import ru.hogwarts.ashebalkin.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;

import static ru.hogwarts.ashebalkin.school.constants.TestConstants.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class StudentControllerTests {

    private Student student;

    private String baseUrl;

    private long studentId;

    @LocalServerPort
    private int localPort;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void initTestData() {
        student = new Student();
        student.setId(1L);
        student.setName("studentName");
        student.setAge(20);
    }

    @BeforeEach
    public void setBaseUrl() {
        this.baseUrl = "http://localhost:" + localPort + "/student/";
    }

    @AfterEach
    void deleteTestData() {
        restTemplate.delete(baseUrl + studentId);
    }


    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void createStudentTest() {
        Assertions
                .assertThat(this.restTemplate.postForObject(baseUrl, student, String.class))
                .isNotNull();
        Assertions
                .assertThat(this.restTemplate.postForObject(baseUrl, student, Student.class))
                .isEqualTo(student);
    }

    @Test
    void deleteStudentTest() {
        studentId = restTemplate.postForObject(baseUrl, student, Student.class).getId();
        student.setId(studentId);
        restTemplate.delete(baseUrl + studentId);

        Assertions
                .assertThat(this.restTemplate.getForObject(baseUrl + studentId, Student.class))
                .isNotIn(student);
    }

    @Test
    void getStudentByIdTest() {
        studentId = restTemplate.postForObject(baseUrl, student, Student.class).getId();

        Assertions
                .assertThat(this.restTemplate.getForObject(baseUrl + studentId, Student.class))
                .isEqualTo(student);
    }

    @Test
    void putStudentTest() {
        student.setAge(100);

        studentId = restTemplate.postForObject(baseUrl, student, Student.class).getId();

        student.setId(studentId);

        restTemplate.put(baseUrl, student);

        Assertions
                .assertThat(this.restTemplate.getForObject(baseUrl + studentId, Student.class))
                .isEqualTo(student);
    }

    @Test
    void getStudensByAgeTest() {
        student.setAge(VERY_OLD_MAN);

        studentId = restTemplate.postForObject(baseUrl, student, Student.class).getId();

        student.setId(studentId);

        Assertions
                .assertThat(this.restTemplate.getForObject(baseUrl + "?age=" + VERY_OLD_MAN, Collection.class))
                .isNotNull();
    }

    @Test
    void getStudensByRangeAgeTest() {
        student.setAge(AGE_81);
        studentId = restTemplate.postForObject(baseUrl, student, Student.class).getId();
        student.setAge(AGE_82);
        studentId = restTemplate.postForObject(baseUrl, student, Student.class).getId();
        student.setAge(AGE_83);
        studentId = restTemplate.postForObject(baseUrl, student, Student.class).getId();

        Assertions
                .assertThat(this.restTemplate.getForObject(baseUrl + "?ageMin=" + START_AGE_80 + "&ageMax=" + END_AGE_90, ArrayList.class).size())
                .isGreaterThan(3);

    }


}
