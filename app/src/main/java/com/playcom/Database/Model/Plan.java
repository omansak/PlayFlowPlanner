package com.playcom.Database.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.playcom.Database.DateConverter;

import java.util.Date;


@Entity
public class Plan {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Name;
    @TypeConverters(DateConverter.class)
    private Date Date;
    private String Explanation;
    private int CategoryId;
    private boolean IsDone;

    @NonNull
    public int getId() {
        return Id;
    }

    public void setId(@NonNull int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        Explanation = explanation;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }

    public boolean getIsDone() {
        return IsDone;
    }

    public void setIsDone(boolean done) {
        IsDone = done;
    }
}
