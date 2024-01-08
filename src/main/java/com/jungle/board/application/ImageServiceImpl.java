package com.jungle.board.application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jungle.board.common.WebException;

import jakarta.annotation.PostConstruct;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${com.jungle.board.upload-dir}")
    private String uploadDirectory;

    @Value("${com.jungle.board.upload-max-size}")
    private Long maxFileSize;

    @PostConstruct
    protected void init() {
        File path = new File(uploadDirectory);
        System.out.println(path.getAbsolutePath());
        if (!path.exists()) {
            path.mkdirs();
            System.out.println(path.getAbsolutePath() + " directory created.");
        }
    }

    @Override
    public String uploadImage(MultipartFile file) {
        long filesize = file.getSize();
        if (filesize > this.maxFileSize) {
            throw new WebException("The File is too big");
        }

        if (file.getOriginalFilename() == null) {
            throw new WebException("Failed to upload file");
        }
        
        int index = file.getOriginalFilename().lastIndexOf(".");
        var extension = file.getOriginalFilename().substring(index);
        var filename = UUID.randomUUID().toString() + extension;
        var filePath = Paths.get(uploadDirectory + File.separator + filename);
        
        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new WebException("Failed to upload file");
        }

        return filename;
    }

    @Override
    public UrlResource downloadImage(String filename) {
        var filepath = Paths.get(uploadDirectory).resolve(filename);
        UrlResource resource;
        
        try {
            resource = new UrlResource(filepath.toUri());
        } catch (MalformedURLException e) {
            throw new WebException("Failed to create URL for file download");
        }

        if (!resource.exists() || !resource.isReadable()) {
            throw new WebException("File not found or is not readable");
        }

        return resource;
    }
}