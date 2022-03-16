package ru.hogwarts.ashebalkin.school.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.ashebalkin.school.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {

    Optional<Avatar> findByStudentId(Long studentId);
}
