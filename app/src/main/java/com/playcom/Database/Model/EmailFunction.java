package com.playcom.Database.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.playcom.Database.DateConverter;

import java.util.Date;

@Entity
public class EmailFunction {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Subject;
    private String Message;
    private String MessageTo;
    @TypeConverters(DateConverter.class)
    private Date Date;

    @NonNull
    public int getId() {
        return Id;
    }

    public void setId(@NonNull int id) {
        Id = id;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getMessageTo() {
        return MessageTo;
    }

    public void setMessageTo(String messageTo) {
        MessageTo = messageTo;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }
}
