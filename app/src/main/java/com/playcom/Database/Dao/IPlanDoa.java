package com.playcom.Database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.playcom.Database.Model.Plan;

import java.util.List;

@Dao
public interface IPlanDoa {
    @Insert
    Long Insert(Plan plan);

    @Update
    void Update(Plan plan);

    @Delete
    void Delete(Plan plan);

    @Query("SELECT * FROM `Plan`")
    List<Plan> GetAll();

    @Query("SELECT * FROM `Plan` WHERE `Id` = :planId")
    Plan FindByPlanId(int planId);
}
