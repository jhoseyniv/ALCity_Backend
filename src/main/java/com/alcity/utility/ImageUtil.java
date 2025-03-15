package com.alcity.utility;

import com.alcity.dto.Interpreter.PLData;
import com.alcity.entity.alenum.BinaryContentType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.coobird.thumbnailator.*;
public class ImageUtil {
    public static String imageUrls="src/main/resources/images/image-utility/";
    public static byte[] getImage(String imageDirectory, String imageName) throws IOException {
        Path imagePath = Path.of(imageDirectory, imageName);

        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null; // Handle missing images
        }
    }
    public static String getFileExtension(String fileName){
        String ext =  fileName.substring(fileName.lastIndexOf(".") + 1);
        return ext;
    }

    public static byte[] getThumbnail(File file) throws IOException {
       // File file = new File(directory, fileName);
        String ext = getFileExtension(file.getName());
        byte[] imageBytes=null;
        switch (ext.toLowerCase()) {
            case "gif":
            case "jpg":
            case "jpeg":
            case "png":
            case "bmp":
            case "tiff":
            case "tif":
            {
                System.out.println("file extension is image :");
                imageBytes = getBytesOfThumbnail(file);
                break;

            }
            case "pdf": {
                System.out.println("file extension is pdf :");
                file = new File(imageUrls,"pdf.jpg");
                imageBytes = getBytesOfThumbnail(file);

                break;
            }
            case "doc":
            case "docx":
            {
                System.out.println("file extension is doc :");
                file = new File(imageUrls, "doc.jpg");
                imageBytes = getBytesOfThumbnail(file);
                break;
            }

            case "ppt":
            case "pptx":
            {
                System.out.println("ppt");
                System.out.println("file extension is ppt or pptx :");
                file = new File(imageUrls, "ppt.jpg");
                imageBytes = getBytesOfThumbnail(file);
                break;
            }
            case "xls":
            case "xlsx":
            {
                System.out.println("ppt");
                System.out.println("file extension is ppt or excel :");
                file = new File(imageUrls, "excel.jpg");
                imageBytes = getBytesOfThumbnail(file);
                break;
            }
            case "perfab":
            {
                System.out.println("perfab");
                System.out.println("file extension is perfab :");
                file = new File(imageUrls, "excel.jpg");
                imageBytes = getBytesOfThumbnail(file);
                break;
            }
            default:
                System.out.println("unkown");
                System.out.println("file extension is unkown :");
                file = new File(imageUrls, "unkown.jpg");
                imageBytes = getBytesOfThumbnail(file);
                break;

        }

        return imageBytes;

    }

    public static byte[] getThumbnail(byte[] content,String fileName) throws IOException {
        Path path = Paths.get("src/main/resources/temp/"+ fileName );
        Files.write(path, content);
        File file = path.toFile();

        String ext = getFileExtension(file.getName());
        byte[] imageBytes=null;
        switch (ext.toLowerCase()) {
            case "gif":
            case "jpg":
            case "jpeg":
            case "png":
            case "bmp":
            case "tiff":
            case "tif":
            {
                System.out.println("file extension is image  and size is:  "+content.length);
                imageBytes = getBytesOfThumbnail(file);
                System.out.println("Thumbnail size is  : "+imageBytes.length);
                break;

            }
            case "pdf": {
                System.out.println("file extension is pdf :");
                file = new File(imageUrls,"pdf.jpg");
                imageBytes = getBytesOfThumbnail(file);

                break;
            }
            case "doc":
            case "docx":
            {
                System.out.println("file extension is doc :");
                file = new File(imageUrls, "doc.jpg");
                imageBytes = getBytesOfThumbnail(file);
                break;
            }

            case "ppt":
            case "pptx":
            {
                System.out.println("ppt");
                System.out.println("file extension is ppt or pptx :");
                file = new File(imageUrls, "ppt.jpg");
                imageBytes = getBytesOfThumbnail(file);
                break;
            }
            case "xls":
            case "xlsx":
            {
                System.out.println("ppt");
                System.out.println("file extension is ppt or excel :");
                file = new File(imageUrls, "excel.jpg");
                imageBytes = getBytesOfThumbnail(file);
                break;
            }
            default:
                System.out.println("unkown");
                System.out.println("file extension is unkown :");
                file = new File(imageUrls, "unkown.jpg");
                imageBytes = getBytesOfThumbnail(file);
                break;

        }
        Files.delete(path);
       return imageBytes;

    }

    public static byte[] convertObjectToBytes(Object obj) {
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        try (ObjectOutputStream ois = new ObjectOutputStream(boas)) {
            ois.writeObject(obj);
            return boas.toByteArray();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        throw new RuntimeException();
    }
    public  static byte[] getBytesOfThumbnail(File file) throws IOException {
        byte[] imageBytes=null;
        Thumbnails.of(file).size(100,100)
                .toFile(new File(file.getName()+"-tumb.png"));
        Path imagePath = Path.of(file.getName()+"-tumb.png");
        if (Files.exists(imagePath)) {
            imageBytes = Files.readAllBytes(imagePath);
            Files.delete(imagePath);
            return imageBytes;
        }
        return null;
    }
    public static byte[] getNoPhoto() throws IOException {
        Path imagePath = Path.of("src/main/resources/images/", "no-photo");
        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null; // Handle missing images
        }
    }
    public static byte[] getNoPhotoBinaryContent() throws IOException {
        Path imagePath = Path.of("src/main/resources/images/", "no-photo");
        if (Files.exists(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return imageBytes;
        } else {
            return null; // Handle missing images
        }
    }
    public static byte[] getNoAvatar() throws IOException {
        Path imagePath = Path.of("src/main/resources/images/", "avatar.png");

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

//    public static void main(String[] args) throws IOException {
//        //System.out.println("this is a get tumbnile of images.....");
//        byte[]  tumb= getThumbnail("e://","pic.jpg");
//        byte[]  tumb_pdf= getThumbnail("e://","1703595056696.pdf");
//        //String
//    }


}
