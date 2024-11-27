package com.employee.employee_crud.helpers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GeneratePhotoPath {
    private final String IMAGE_UPLOAD_PATH ="\\employee_images\\" ;
    private final String UPLOAD_DIR = "C:\\Users\\Pralay Dutta Saw\\Downloads\\ESD_project\\Employee_Application\\Employee_Application_Backend\\src\\main\\resources\\static";
    public String savePhotograph(MultipartFile photograph, String photoPath) {
        try {
            File uploadDir = new File(UPLOAD_DIR+IMAGE_UPLOAD_PATH);
            if (!uploadDir.exists()) {
                if(!uploadDir.mkdirs())
                    throw new IOException("Unable to create directory");
            }

            String fileExtension = Objects.requireNonNull(
                            photograph.getOriginalFilename())
                    .substring(photograph.getOriginalFilename()
                            .lastIndexOf(".")
                    );
            String relativeFilePath = IMAGE_UPLOAD_PATH + photoPath + fileExtension;

            String absoluteFilePath = UPLOAD_DIR + relativeFilePath;
            photograph.transferTo(new File(absoluteFilePath));

            return relativeFilePath;
        } catch (IOException e) {
            throw new RuntimeException("Error saving photograph: " + e.getMessage(), e);
        }
    }

    public boolean deletePhotograph(String photographPath) {
        File file = new File(UPLOAD_DIR + photographPath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }
}
