package com.example.moviereviewplatform.servlet;

import com.example.moviereviewplatform.service.impl.ImageServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;

//@WebServlet("/images/*")
public class ImageController extends HttpServlet {

    private final ImageServiceImpl imageServiceImpl = ImageServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var requestUri = req.getRequestURI();
        var imagePath = requestUri.replace("/images", "");

        imageServiceImpl.get(imagePath)
                .ifPresentOrElse(image -> {
                    resp.setContentType("application/octet-stream");
                    writeImage(image, resp);
                }, () -> resp.setStatus(404));
    }

    @SneakyThrows
    private void writeImage(InputStream image, HttpServletResponse resp) {
        try (image; var outputStream = resp.getOutputStream()) {
            int currentByte;
            while ((currentByte = image.read()) != -1) {
                outputStream.write(currentByte);
            }
        }
    }
}