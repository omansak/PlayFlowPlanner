package com.playcom.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.playcom.Database.Model.Plan;

import java.util.List;

@Dao
public interface IPlanDoa {
    @Query("SELECT * FROM `Plan`")
    List<Plan> GetAll();
    @Query("SELECT * FROM `Plan` WHERE `PlanId` = :planId")
    Plan FindByPlanId(int planId);
    @Insert
    void InsertPlan(Plan plan);
    @Update
    void UpdatePlan(Plan plan);
    @Delete
    void DeletePlan(Plan plan);
}
