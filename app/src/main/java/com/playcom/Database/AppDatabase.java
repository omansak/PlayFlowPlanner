package com.playcom.Database;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.playcom.Database.Dao.IActionDoa;
import com.playcom.Database.Dao.IEmailFunctionDoa;
import com.playcom.Database.Dao.IFunctionCategory;
import com.playcom.Database.Dao.IPlanCategoryDao;
import com.playcom.Database.Dao.IPlanDoa;
import com.playcom.Database.Dao.ISmsFunctionDao;
import com.playcom.Database.Model.Action;
import com.playcom.Database.Model.EmailFunction;
import com.playcom.Database.Model.FunctionCategory;
import com.playcom.Database.Model.Plan;
import com.playcom.Database.Model.PlanCategory;
import com.playcom.Database.Model.SmsFunction;

import java.util.List;
import java.util.concurrent.Executors;

@Database(entities = {Plan.class,PlanCategory.class,FunctionCategory.class,EmailFunction.class,Action.class,SmsFunction.class}, version = 1,exportSchema = true)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase _instance;
    public static final String DATABASE_NAME = "FlowPlannerDB";

    public abstract IPlanDoa PlanDoa();
    public abstract IPlanCategoryDao PlanCategoryDao();
    public abstract IFunctionCategory FunctionCategory();
    public abstract IEmailFunctionDoa EmailFunctionDoa();
    public abstract ISmsFunctionDao SmsFunctionDao();
    public abstract IActionDoa ActionDoa();

    private final MutableLiveData<Boolean> IsDatabaseCreated = new MutableLiveData<>();
    public static AppDatabase GetInstance(final Context context) {
        if (_instance == null) {
            synchronized (AppDatabase.class) {
                if (_instance == null) {
                    _instance = BuildDatabase(context.getApplicationContext());
                }
            }
        }
        return _instance;
    }

    private static AppDatabase BuildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                InsertPlanCategoris(GetInstance(context),new DataGenerator().GenerateDefaultCategories());
                                InsertFunctionCategoris(GetInstance(context),new DataGenerator().GenerateDefaultFunctionCategories());
                            }
                        });
                    }
                })
                //.allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                //.addMigrations()
                .build();
    }
    private static void InsertPlanCategoris(AppDatabase appDatabase, List<PlanCategory> planCategories)
    {
        appDatabase.PlanCategoryDao().InsertAll(planCategories);
    }
    private static void InsertFunctionCategoris(AppDatabase appDatabase, List<FunctionCategory> functionCategories)
    {
        appDatabase.FunctionCategory().InsertAll(functionCategories);
    }
}
