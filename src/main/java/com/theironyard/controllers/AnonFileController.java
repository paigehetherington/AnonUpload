package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by vajrayogini on 3/16/16.
 */
@RestController
public class AnonFileController {
    @Autowired
    AnonFileRepository files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response) throws IOException {
        File dir = new File("public/files"); //if empty can't upload so if empty makes directories (can also create dummy file)
        dir.mkdirs();
        File f = File.createTempFile("file", file.getOriginalFilename(), dir); //arguments: prefix, suffix (filename), tell where to go
        FileOutputStream fos = new FileOutputStream(f); //file writer
        fos.write(file.getBytes());

        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename());
        files.save(anonFile);
        response.sendRedirect("/");
    }
        @RequestMapping(path="/files", method = RequestMethod.GET)
                public List<AnonFile> getFiles() {
            return (List<AnonFile>) files.findAll();
        }

    }



