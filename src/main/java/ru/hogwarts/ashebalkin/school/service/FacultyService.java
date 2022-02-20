package ru.hogwarts.ashebalkin.school.service;

import ru.hogwarts.ashebalkin.school.model.Faculty;

import java.util.Collection;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    Collection<Faculty> getFacultyByColor(String color);

}
