package com.playcom.Database.Service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.playcom.Database.AppDatabase;
import com.playcom.Database.Dao.IActionDoa;
import com.playcom.Database.Dao.IEmailFunctionDoa;
import com.playcom.Database.Model.Action;
import com.playcom.Database.Model.EmailFunction;

import java.util.List;

public class ActionService {
    IActionDoa _service;

    public ActionService(Context context) {
        _service = AppDatabase.GetInstance(context).ActionDoa();
    }

    @SuppressLint("StaticFieldLeak")
    public int InsertAsync(final Action i) {
        try {
            return new AsyncTask<Action, Void, Integer>() {
                @Override
                protected Integer doInBackground(Action... obj) {
                    return _service.Insert(obj[0]).intValue();
                }
            }.execute(i).get();
        } catch (Exception e) {
            return 0;
        }
    }

    @SuppressLint("StaticFieldLeak")
    public void Update(Action i)
    {
        new AsyncTask<Action, Void, Void>() {
            @Override
            protected Void doInBackground(Action... obj) {
                _service.Update(obj[0]);
                return null;
            }
        }.execute(i);
    }
    @SuppressLint("StaticFieldLeak")
    public void Delete(Action i)
    {
        new AsyncTask<Action, Void, Void>() {
            @Override
            protected Void doInBackground(Action... obj) {
                _service.Delete(obj[0]);
                return null;
            }
        }.execute(i);
    }
    @SuppressLint("StaticFieldLeak")
    public Action GetById(int i)
    {
        try {
            return new AsyncTask<Integer, Void, Action>() {
                @Override
                protected Action doInBackground(Integer... obj) {
                    return _service.GetById(obj[0]);
                }
            }.execute(i).get();
        } catch (Exception e) {
            return null;
        }
    }
    @SuppressLint("StaticFieldLeak")
    public List<Action> GetAll()
    {
        try {
            return new AsyncTask<Void, Void, List<Action>>() {
                @Override
                protected List<Action>  doInBackground(Void... voids) {
                    return _service.GetAll();
                }
            }.execute().get();
        } catch (Exception e) {
            return null;
        }
    }
    @SuppressLint("StaticFieldLeak")
    public List<Action> GetListByPlanId(int i)
    {
        try {
            return new AsyncTask<Integer, Void, List<Action>>() {
                @Override
                protected List<Action>  doInBackground(Integer... obj) {
                    return _service.GetListPlanId(obj[0]);
                }
            }.execute(i).get();
        } catch (Exception e) {
            return null;
        }
    }
}
