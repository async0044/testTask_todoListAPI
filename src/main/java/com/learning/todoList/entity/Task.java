package com.learning.todoList.entity;

import com.learning.todoList.util.Status;
import jakarta.persistence.*;

@Entity
@Table(name="todotask")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private String date;
    private String author;
    private Status status;
    private String file1link;
    private String file2link;

    public Task() {}

    public Task(String title, String content, String date, String author, Status status, String file1link, String file2link) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
        this.status = status;
        this.file1link = file1link;
        this.file2link = file2link;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFile1link() {
        return file1link;
    }

    public void setFile1link(String file1link) {
        this.file1link = file1link;
    }

    public String getFile2link() {
        return file2link;
    }

    public void setFile2link(String file2link) {
        this.file2link = file2link;
    }
}
