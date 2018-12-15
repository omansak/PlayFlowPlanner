package com.playcom.playflowplanner.Service;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.playcom.Database.AppDatabase;
import com.playcom.Database.Dao.IPlanDoa;
import com.playcom.Database.Model.Plan;

import java.util.List;

public class PlanService extends ViewModel {
    IPlanDoa _planDoa;
    private MediatorLiveData<List<Plan>> _observablePlans;

    public PlanService(Context context) {
        _planDoa = AppDatabase.GetInstance(context).PlanDoa();
        _observablePlans = new MediatorLiveData<>();
        _observablePlans.setValue(this.GetAll());
        _observablePlans.addSource(_planDoa.GetAllLive(), new Observer<List<Plan>>() {
            @Override
            public void onChanged(@Nullable List<Plan> plans) {
                    _observablePlans.setValue(plans);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    public void InsertAsync(final Plan plan) {
        new AsyncTask<Plan, Void, Void>() {
            @Override
            protected Void doInBackground(Plan... plans) {
                _planDoa.Insert(plans[0]);
                return null;
            }
        }.execute(plan);
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

    public LiveData<List<Plan>> GetAllLive() {
        return _observablePlans;
    }
}
