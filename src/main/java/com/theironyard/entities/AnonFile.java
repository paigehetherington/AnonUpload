package com.theironyard.entities;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by vajrayogini on 3/16/16.
 */
@Entity
@Table(name = "files")
public class AnonFile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String originalFilename;

    @Column(nullable = false)
    LocalDateTime dateTime;

    @Column(nullable = false)
    String comment;

    @Column
    boolean isPermanent;

    public AnonFile(String filename, String originalFilename, String comment, LocalDateTime dateTime, boolean isPermanent) {
        this.filename = filename;
        this.originalFilename = originalFilename;
        this.comment = comment;
        this.dateTime = dateTime;
        this.isPermanent = isPermanent;

    }

    public AnonFile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPermanent() {
        return isPermanent;
    }

    public void setPermanent(boolean permanent) {
        isPermanent = permanent;
    }
}
