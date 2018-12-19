package com.playcom.Database.Service;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import com.playcom.Database.AppDatabase;
import com.playcom.Database.Dao.IPlanDoa;
import com.playcom.Database.Model.Action;
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
    public void DeleteAsync(Plan i)
    {
        new AsyncTask<Plan, Void, Void>() {
            @Override
            protected Void doInBackground(Plan... obj) {
                _planDoa.Delete(obj[0]);
                return null;
            }
        }.execute(i);
    }
    @SuppressLint("StaticFieldLeak")
    public void UpdateAsync(Plan i)
    {
        new AsyncTask<Plan, Void, Void>() {
            @Override
            protected Void doInBackground(Plan... obj) {
                _planDoa.Update(obj[0]);
                return null;
            }
        }.execute(i);
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
    @SuppressLint("StaticFieldLeak")
    public Plan GetById(int i)
    {
        try {
            return new AsyncTask<Integer, Void, Plan>() {
                @Override
                protected Plan doInBackground(Integer... obj) {
                    return _planDoa.FindByPlanId(obj[0]);
                }
            }.execute(i).get();
        } catch (Exception e) {
            return null;
        }
    }
}
