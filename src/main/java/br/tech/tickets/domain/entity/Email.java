package br.tech.tickets.domain.entity;

import lombok.NoArgsConstructor;


public class Email {

    private String recipient;
    private String subject;
    private String body;
    private String attachment;

    public Email(String recipient, String subject, String body, String attachment) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.attachment = attachment;
    }

    public Email() {
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
