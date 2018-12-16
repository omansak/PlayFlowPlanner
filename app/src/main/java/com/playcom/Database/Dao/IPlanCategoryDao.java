package com.playcom.Database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.playcom.Database.Model.PlanCategory;

import java.util.List;
@Dao
public interface IPlanCategoryDao {
    @Insert
    void Insert(PlanCategory category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertAll(List<PlanCategory> categories);

    @Update
    void Update(PlanCategory category);

    @Delete
    void Delete(PlanCategory category);
    @Query("SELECT * FROM `PlanCategory`")
    List<PlanCategory> GetAll();

    @Query("SELECT * FROM `PlanCategory` WHERE `Id` = :categoryId")
    PlanCategory FindByPlanId(int categoryId);

    @Query("SELECT * FROM `PlanCategory` WHERE `Name` = :categoryName")
    PlanCategory FindByName(String categoryName);

}
