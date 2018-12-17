package com.playcom.Database.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.playcom.Database.Model.FunctionCategory;

import java.util.List;
@Dao
public interface IFunctionCategory {
    @Insert
    void Insert(FunctionCategory functionCategory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(List<FunctionCategory> functionCategories);

    @Update
    void Update(FunctionCategory functionCategory);

    @Delete
    void Delete(FunctionCategory functionCategory);

    @Query("SELECT * FROM `FunctionCategory`")
    List<FunctionCategory> GetAll();

    @Query("SELECT * FROM `FunctionCategory` WHERE `Id` = :functionCategoryId")
    FunctionCategory FindByPlanId(int functionCategoryId);
}
