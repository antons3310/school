package ru.hogwarts.ashebalkin.school;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.ashebalkin.school.controller.FacultyController;
import ru.hogwarts.ashebalkin.school.model.Faculty;
import ru.hogwarts.ashebalkin.school.repository.FacultyRepository;
import ru.hogwarts.ashebalkin.school.service.FacultyServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.hogwarts.ashebalkin.school.constants.TestConstants.*;

@WebMvcTest
class FacultyControllerTests {
    private Faculty faculty;

    private JSONObject facultyObject;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @BeforeEach
    void initTestData() throws JSONException {
        faculty = new Faculty();
        faculty.setId(FACULTY_ID);
        faculty.setName(FACULTY_NAME);
        faculty.setColor(FACULTY_COLOR);

        facultyObject = new JSONObject();
        facultyObject.put("name", FACULTY_NAME);
        facultyObject.put("color", FACULTY_COLOR);
    }


    @Test
    void createFacultyTest() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty") //send
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.id").value(FACULTY_ID))
                .andExpect(jsonPath("$.name").value(FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(FACULTY_COLOR));


    }
}
