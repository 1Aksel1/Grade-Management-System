package com.example.admindesktopapp.dto;

public class NotificationTypeDto {

    private Long id;
    private String typeName;
    private String subject;
    private String template;
    private String parameters;

    public NotificationTypeDto(Long id, String typeName, String subject, String template, String parameters) {
        this.id = id;
        this.typeName = typeName;
        this.subject = subject;
        this.template = template;
        this.parameters = parameters;
    }

    public NotificationTypeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}
