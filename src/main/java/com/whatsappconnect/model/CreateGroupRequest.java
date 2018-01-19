package com.whatsappconnect.model;

/*
 @author Ramanpreet Singh on 19/01/18
 *
 */
public class CreateGroupRequest {

    private String subject;

    private String name;

    private String description;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang.builder.ToStringBuilder(this)
                .append("subject", subject)
                .append("name", name)
                .append("description", description)
                .toString();
    }
}
