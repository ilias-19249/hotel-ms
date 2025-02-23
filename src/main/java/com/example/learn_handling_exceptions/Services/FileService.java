package com.example.learn_handling_exceptions.Services;


import com.example.learn_handling_exceptions.Repositories.FileRepository;
import com.example.learn_handling_exceptions.entities.File;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class FileService {


    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File save(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(FileDB);
    }


    public Optional<File> getFileById(Integer id) {
        return fileRepository.findById(id);
    }
}
