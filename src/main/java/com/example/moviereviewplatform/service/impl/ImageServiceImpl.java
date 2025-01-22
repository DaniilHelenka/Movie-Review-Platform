package com.example.moviereviewplatform.service.impl;

import com.example.moviereviewplatform.service.ImageService;
import com.example.moviereviewplatform.util.PropertiesUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;


public class ImageServiceImpl implements ImageService {

    private static final ImageServiceImpl INSTANCE = new ImageServiceImpl();
    private PropertiesUtil config;
    private final String basePath;

    private ImageServiceImpl() {
        this.config = new PropertiesUtil();
        this.basePath = config.get();
    }
    public void upload(String imagePath, InputStream imageContent) throws IOException {
        System.out.println("image.base.url=F:/work/images:  " + basePath + "ImagePath:  " + imagePath);
        var imageFullPath = Path.of(basePath, imagePath);
        try (imageContent) {
            Files.createDirectories(imageFullPath.getParent());
            Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }
    public Optional<InputStream> get(String imagePath) throws IOException {
        var imageFullPath = Path.of(basePath, imagePath);

        return Files.exists(imageFullPath)
                ? Optional.of(Files.newInputStream(imageFullPath))
                : Optional.empty();
    }
    public static ImageServiceImpl getInstance() {
        return INSTANCE;
    }
}