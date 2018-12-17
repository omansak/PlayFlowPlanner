package com.playcom.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.playcom.Database.Model.Action;

import java.util.List;
@Dao
public interface IActionDoa {
    @Insert
    Long Insert(Action i);

    @Update
    void Update(Action i);

    @Delete
    void Delete(Action i);

    @Query("SELECT * FROM `Action`")
    List<Action> GetAll();

    @Query("SELECT * FROM `Action` WHERE `Id` = :i")
    Action GetById(int i);

    @Query("SELECT * FROM `Action` WHERE `PlanId` = :i")
    List<Action> GetListPlanId(int i);

}
