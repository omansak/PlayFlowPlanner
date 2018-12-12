package com.playcom.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.playcom.Database.Dao.IPlanDoa;
import com.playcom.Database.Model.Plan;

@Database(entities = {Plan.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public static final String DATABASE_NAME = "FlowPlannerDB";

    public abstract IPlanDoa planDoa();

    public static AppDatabase GetInstance(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = BuildDatabase(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private static AppDatabase BuildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

}
