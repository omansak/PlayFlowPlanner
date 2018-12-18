package com.playcom.Database.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.playcom.Database.DateConverter;

import java.util.Date;

@Entity
public class Action {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private int PlanId;
    private int FunctionId;
    private int FunctionCategoryId;
    private int NextAction;
    private String Name;
    private String Explanation;
    @TypeConverters(DateConverter.class)
    private Date Date;
    private boolean IsDone;
    private boolean IsProcessed;

    @NonNull
    public int getId() {
        return Id;
    }

    public void setId(@NonNull int id) {
        Id = id;
    }

    public int getPlanId() {
        return PlanId;
    }

    public void setPlanId(int planId) {
        PlanId = planId;
    }

    public int getFunctionId() {
        return FunctionId;
    }

    public void setFunctionId(int functionId) {
        FunctionId = functionId;
    }

    public int getNextAction() {
        return NextAction;
    }

    public void setNextAction(int nextAction) {
        NextAction = nextAction;
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

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public boolean getIsDone() {
        return IsDone;
    }

    public void setIsDone(boolean done) {
        IsDone = done;
    }

    public int getFunctionCategoryId() {
        return FunctionCategoryId;
    }

    public void setFunctionCategoryId(int functionCategoryId) {
        FunctionCategoryId = functionCategoryId;
    }

    public boolean getIsProcessed() {
        return IsProcessed;
    }

    public void setIsProcessed(boolean processed) {
        IsProcessed = processed;
    }
}
