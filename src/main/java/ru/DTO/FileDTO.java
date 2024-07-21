package ru.DTO;


import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class FileDTO{

    private Long id;
    private String title;
    private String filecontent;
    private String description;
    private Timestamp createdAt;

    public FileDTO() {
    }

    public FileDTO(Long id, String title, String filecontent, String description, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.filecontent = filecontent;
        this.description = description;
        this.createdAt = createdAt;
    }

    public FileDTO(String title, String filecontent, String description) {
        this.title = title;
        this.filecontent = filecontent;
        this.description = description;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilecontent() {
        return filecontent;
    }

    public void setFilecontent(String filecontent) {
        this.filecontent = filecontent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
        return createdAt;
    }
}
