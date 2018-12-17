package com.playcom.Database.Service;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import com.playcom.Database.AppDatabase;
import com.playcom.Database.Dao.IPlanDoa;
import com.playcom.Database.Model.Plan;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PlanService extends ViewModel {
    IPlanDoa _planDoa;
    private MediatorLiveData<List<Plan>> _observablePlans;

    public PlanService(Context context) {
        _planDoa = AppDatabase.GetInstance(context).PlanDoa();
    }

    @SuppressLint("StaticFieldLeak")
    public int InsertAsync(final Plan plan) {
        try {
            return new AsyncTask<Plan, Void, Integer>() {
                @Override
                protected Integer doInBackground(Plan... plans) {
                    return _planDoa.Insert(plans[0]).intValue();
                }
            }.execute(plan).get();
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public List<Plan> GetAll() {
        try {
            return new AsyncTask<Void, Void, List<Plan>>() {
                @Override
                protected List<Plan> doInBackground(Void... voids) {
                    return _planDoa.GetAll();
                }
            }.execute().get();
        } catch (Exception e) {
            return null;
        }
    }
}
