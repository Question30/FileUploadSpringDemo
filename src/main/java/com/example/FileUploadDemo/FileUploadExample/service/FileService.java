package com.example.FileUploadDemo.FileUploadExample.service;

import com.example.FileUploadDemo.FileUploadExample.FileStorageException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {
    public String uploadDir = "C:\\Users\\alexander.gomez\\Downloads";
    public void uploadFile(MultipartFile file){
        try{
            Path copyLocation =
                    Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation,
                    StandardCopyOption.REPLACE_EXISTING);

            String fileName = file.getOriginalFilename();
            int fileLength = file.getBytes().length;
            System.out.println("File uploaded successfully, " + "file name is" +
                    " :: " + fileName + " and length is :: " + fileLength);

        }catch (IOException e){
            e.printStackTrace();
            throw new FileStorageException("File Not Found");
        }
    }
}
