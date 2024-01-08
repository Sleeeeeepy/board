package com.jungle.board.application;

import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile file);
    UrlResource downloadImage(String filename);
}