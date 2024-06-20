package com.alcity.utility;

import com.alcity.entity.alenum.BinaryContentType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ImageUtil {
    public static byte[] getImage(String imageDirectory, String imageName) throws IOException {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null; // Handle missing images
        }
    }
    public static BinaryContentType getBinaryContentType(String extension){
        if(extension.equalsIgnoreCase("image/jpeg"))
            return BinaryContentType.Image;

        return BinaryContentType.Other;
    }
}
