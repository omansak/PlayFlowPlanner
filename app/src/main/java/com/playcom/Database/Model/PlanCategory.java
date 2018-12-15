package com.playcom.Database.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class PlanCategory {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Name;
    private String Explanation;

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

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        Explanation = explanation;
    }
}
