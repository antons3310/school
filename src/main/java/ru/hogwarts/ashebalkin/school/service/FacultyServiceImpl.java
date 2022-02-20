package ru.hogwarts.ashebalkin.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.ashebalkin.school.model.Faculty;
import ru.hogwarts.ashebalkin.school.repository.FacultyRepository;

import java.util.Collection;

@Service
public class FacultyServiceImpl implements FacultyService {


    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty getFaculty(Long id) {
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getFacultyByColor(String color) {
//        return facultyRepository.findAll().stream().filter(student -> Objects.equals(student.getColor(), color)).collect(Collectors.toList());
        return facultyRepository.findByColor(color);
    }

}
