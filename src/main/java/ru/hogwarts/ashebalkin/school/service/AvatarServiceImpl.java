package ru.hogwarts.ashebalkin.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.ashebalkin.school.model.Avatar;
import ru.hogwarts.ashebalkin.school.model.Student;
import ru.hogwarts.ashebalkin.school.repository.AvatarRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {
    @Value("${student.avatars.dir.path}")
    private String avatarsDir;

    Logger logger = LoggerFactory.getLogger(AvatarServiceImpl.class);

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile file) throws IOException {
        logger.info("Was invoked method for upload avatar");
        Student student = studentService.getStudent(studentId);

        Path filePath = Path.of(avatarsDir, student + "." + getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        logger.debug("File path was set to {}", filePath);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatarByStudentId(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData((file.getBytes()));
        avatarRepository.save(avatar);
    }

    private String getExtension(String originalFilename) {
        String originalFilenameExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        logger.debug("File name extension was set to {}", originalFilenameExtension);
        return originalFilenameExtension;
    }

    @Override
    public Avatar findAvatarByStudentId(Long studentId) {
        logger.info("Was invoked method for find avatar by studentId");
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    @Override
    public List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for get all avatars");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
