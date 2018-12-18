package com.playcom.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.playcom.Database.Model.EmailFunction;
import com.playcom.Database.Model.FunctionCategory;

import java.util.List;

@Dao
public interface IEmailFunctionDoa {
    @Insert
    Long Insert(EmailFunction i);

    @Update
    void Update(EmailFunction i);

    @Delete
    void Delete(EmailFunction i);

    @Query("SELECT * FROM `EmailFunction` WHERE `Id` = :i")
    EmailFunction FindByPlanId(int i);
}
