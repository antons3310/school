package ru.hogwarts.ashebalkin.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.ashebalkin.school.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColor(String color);

    List<Faculty> findByColorOrName(String color, String name);
}
