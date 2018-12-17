package com.playcom.Database.Service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.playcom.Database.AppDatabase;
import com.playcom.Database.Dao.IFunctionCategory;
import com.playcom.Database.Dao.IPlanCategoryDao;
import com.playcom.Database.Model.FunctionCategory;
import com.playcom.Database.Model.PlanCategory;

import java.util.List;

public class FunctionCategoryService {
    IFunctionCategory _functionCategory;

    public FunctionCategoryService(Context context) {
        _functionCategory = AppDatabase.GetInstance(context).FunctionCategory();
    }

    @SuppressLint("StaticFieldLeak")
    public List<FunctionCategory> GetAll() {
        try {
            return new AsyncTask<Void, Void, List<FunctionCategory>>() {
                @Override
                protected List<FunctionCategory> doInBackground(Void... voids) {
                    return _functionCategory.GetAll();
                }
            }.execute().get();
        } catch (Exception e) {
            return null;
        }
    }

}
