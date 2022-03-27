package ru.hogwarts.ashebalkin.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.ashebalkin.school.model.Faculty;
import ru.hogwarts.ashebalkin.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FacultyServiceImpl implements FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method - createFaculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        logger.info("Was invoked method - getFaculty");
        if (facultyRepository.findById(id).isEmpty()) {
            logger.info("No such faculty with id {}", id);
        }
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method - updateFaculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        logger.info("Was invoked method - deleteFaculty");
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getFacultyByColor(String color) {
        logger.info("Was invoked method - getFacultyByColor");
        return facultyRepository.findByColor(color);
    }

    @Override
    public Collection<Faculty> getFacultyByColorOrName(String color, String name) {
        logger.info("Was invoked method - getFacultyByColorOrName");
        return facultyRepository.findByColorOrName(color, name);
    }

    @Override
    public String getLongestFacultyName() {
        logger.info("Was invoked method - getLongestFacultyName");

        List<Faculty> facultyList = facultyRepository.findAll();

        if (facultyList.isEmpty()) {
            logger.warn("There are no Faculties in database");
        }

        return facultyList.stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .orElse(null);
    }

    @Override
    public Integer getIntegerValue() {
        List<Integer> integers = Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .collect(Collectors.toList());

        return integers.stream().parallel().mapToInt(Integer::intValue).sum();
    }

}
