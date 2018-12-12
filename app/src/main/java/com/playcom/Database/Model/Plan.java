package com.playcom.Database.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;


@Entity
public class Plan {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int PlanId;
    private String PlanName;
    private Date PlanDate;
    private String Explanation;

    @NonNull
    public int getPlanId() {
        return PlanId;
    }

    public void setPlanId(@NonNull int planId) {
        PlanId = planId;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }

    public Date getPlanDate() {
        return PlanDate;
    }

    public void setPlanDate(Date planDate) {
        PlanDate = planDate;
    }

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        Explanation = explanation;
    }


}
