package ru.hogwarts.ashebalkin.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.ashebalkin.school.model.Faculty;
import ru.hogwarts.ashebalkin.school.repository.FacultyRepository;

import java.util.Collection;

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

}
