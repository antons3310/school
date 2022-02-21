package ru.hogwarts.ashebalkin.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.ashebalkin.school.model.Faculty;
import ru.hogwarts.ashebalkin.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping(params = {"color"})
    public ResponseEntity<Collection<Faculty>> getFacultyByColor(@RequestParam(value = "color", required = false) String color) {
        Collection<Faculty> facultyByColor = facultyService.getFacultyByColor(color);
        if (facultyByColor.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(facultyByColor);
    }

    @GetMapping(params = {"color", "name"})
    public ResponseEntity<Collection<Faculty>> getFacultyByColorOrName(@RequestParam(value = "color", required = false) String color,
                                                                       @RequestParam(value = "name", required = false) String name) {
        Collection<Faculty> facultyByColorOrName = facultyService.getFacultyByColorOrName(color, name);
        if (facultyByColorOrName.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(facultyByColorOrName);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty facultyForUpdate = facultyService.updateFaculty(faculty);
        if (facultyForUpdate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(facultyForUpdate);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

}
