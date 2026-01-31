package com.example.admindesktopapp.dto;

public class SavedNotification {

    private Long id;
    private String email;
    private String subject;
    private String content;
    private String sentTimeAndDate;

    public SavedNotification(Long id, String email, String subject, String content, String sentTimeAndDate) {
        this.id = id;
        this.email = email;
        this.subject = subject;
        this.content = content;
        this.sentTimeAndDate = sentTimeAndDate;
    }

    public SavedNotification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSentTimeAndDate() {
        return sentTimeAndDate;
    }

    public void setSentTimeAndDate(String sentTimeAndDate) {
        this.sentTimeAndDate = sentTimeAndDate;
    }
}
