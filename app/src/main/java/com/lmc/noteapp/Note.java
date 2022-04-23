package com.lmc.noteapp;

public class Note {
    String subject, content, time;

    public Note() {
    }

    public Note(String subject, String content, String time) {
        this.subject = subject;
        this.content = content;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
