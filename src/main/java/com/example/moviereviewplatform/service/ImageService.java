package com.example.moviereviewplatform.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public interface ImageService {
    void upload(String imagePath, InputStream imageContent) throws IOException;

    Optional<InputStream> get(String imagePath) throws IOException;
}
