package com.playcom.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.playcom.Database.Model.EmailFunction;
import com.playcom.Database.Model.FunctionCategory;
import com.playcom.Database.Model.SmsFunction;
@Dao
public interface ISmsFunctionDao {
    @Insert
    Long Insert(SmsFunction i);

    @Update
    void Update(SmsFunction i);

    @Delete
    void Delete(SmsFunction i);

    @Query("SELECT * FROM `SmsFunction` WHERE `Id` = :i")
    SmsFunction FindByPlanId(int i);
}
