package ru.hogwarts.ashebalkin.school.service;

import ru.hogwarts.ashebalkin.school.model.Faculty;

import java.util.Collection;
import java.util.Optional;

public interface FacultyService {
    Faculty createFaculty(Faculty faculty);

    Faculty getFaculty(Long id);

    Faculty updateFaculty(Faculty faculty);

    void deleteFaculty(Long id);

    Collection<Faculty> getFacultyByColor(String color);

    Collection<Faculty> getFacultyByColorOrName(String color, String name);

    Optional<String> getLongestFacultyName();

    Integer getIntegerValue();
}
