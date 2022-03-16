package ru.hogwarts.ashebalkin.school.service;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.ashebalkin.school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {

    void uploadAvatar(Long studentId, MultipartFile file) throws IOException;

    Avatar findAvatarByStudentId(Long studentId);

    List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize);
}
