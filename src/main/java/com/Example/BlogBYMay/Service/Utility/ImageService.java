package com.example.BlogBYMay.Service.Utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

@Service
public class ImageService {

    @Value("${app.profile.image}")
    private String uploadDirectory;

    public String saveImage(MultipartFile imageFile) throws IOException {

        if (imageFile.isEmpty()) {
            throw new IOException("File is Empty");
        }

        String imageFileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();

        Path pathDirectory = Paths.get(uploadDirectory, imageFileName);

        Files.createDirectories(pathDirectory.getParent());

        Files.copy(imageFile.getInputStream(), pathDirectory, StandardCopyOption.REPLACE_EXISTING);

        return imageFileName;
    }

    public String getUploadDirectory(String imageFileName) throws IOException {
        Path imagePath =Paths.get(uploadDirectory).resolve(imageFileName);

        byte[] imageBytes=Files.readAllBytes(imagePath);

        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

        return encodedImage;
    }
}
