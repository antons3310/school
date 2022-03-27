package ru.hogwarts.ashebalkin.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("default")
public class MainInfoServiceImpl implements InfoService {
    @Value("${server.port}")
    private Integer localPort;

    @Override
    public Integer getPort() {
        return localPort;
    }
}
