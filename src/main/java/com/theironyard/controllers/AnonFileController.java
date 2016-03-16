package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vajrayogini on 3/16/16.
 */
@RestController
public class AnonFileController {
    @Autowired
    AnonFileRepository files;

    Server dbui = null;

    @PostConstruct
    public void init() throws SQLException {
        dbui = Server.createWebServer().start();
    }

    @PreDestroy
    public void destroy() {
        dbui.stop();
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, HttpServletResponse response, String comment, boolean isPerm) throws IOException {
        File dir = new File("public/files"); //if empty can't upload so if empty makes directories (can also create dummy file)
        dir.mkdirs();
        File f = File.createTempFile("file", file.getOriginalFilename(), dir); //arguments: prefix, suffix (filename), tell where to go


        if (comment.equals("")) {
           comment = file.getOriginalFilename();
        }
        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename(), comment, LocalDateTime.now(), isPerm);



        FileOutputStream fos = new FileOutputStream(f); //file writer
        fos.write(file.getBytes());
        files.save(anonFile);

//        if (anonFile.getId()) >
//        if (anonFile.getDateTime() == null) {
//
//        }
//        files.save(anonFile);
        response.sendRedirect("/");
    }
        @RequestMapping(path="/files", method = RequestMethod.GET)
        public List<AnonFile> getFiles() {

            List<AnonFile> falsePerms = files.findByIsPermanentFalseOrderByDateTimeAsc();

            if (falsePerms.size() > 3) {
                AnonFile old = falsePerms.get(0);  //to delete from db, asc: oldest first
                files.delete(old);
                File oldOnDisk = new File("public/files/" + old.getFilename()); //to delete from disk
                oldOnDisk.delete();
            }

                return (List<AnonFile>) files.findAll();
        }


    }



