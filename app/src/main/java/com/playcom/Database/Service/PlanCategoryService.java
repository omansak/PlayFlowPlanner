package com.playcom.Database.Service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.playcom.Database.AppDatabase;
import com.playcom.Database.Dao.IPlanCategoryDao;
import com.playcom.Database.Model.PlanCategory;

import java.util.List;

public class PlanCategoryService {
    IPlanCategoryDao _planCategoryDao;

    public PlanCategoryService(Context context) {
        _planCategoryDao = AppDatabase.GetInstance(context).PlanCategoryDao();
    }

    @SuppressLint("StaticFieldLeak")
    public List<PlanCategory> GetAll() {
        try {
            return new AsyncTask<Void, Void, List<PlanCategory>>() {
                @Override
                protected List<PlanCategory> doInBackground(Void... voids) {
                    return _planCategoryDao.GetAll();
                }
            }.execute().get();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public PlanCategory GetByName(String name) {
        try {
            return new AsyncTask<String, Void, PlanCategory>() {
                @Override
                protected PlanCategory doInBackground(String... strings) {
                    return _planCategoryDao.FindByName(strings[0]);
                }
            }.execute(name).get();
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public PlanCategory GetById(int id) {
        try {
            return new AsyncTask<Integer, Void, PlanCategory>() {

                @Override
                protected PlanCategory doInBackground(Integer... obj) {
                    return _planCategoryDao.FindByPlanId(obj[0]);
                }
            }.execute(id).get();
        } catch (Exception e) {
            return null;
        }
    }
}
