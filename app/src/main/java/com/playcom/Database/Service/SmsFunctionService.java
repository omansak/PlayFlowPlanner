package com.playcom.Database.Service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.playcom.Database.AppDatabase;
import com.playcom.Database.Dao.IEmailFunctionDoa;
import com.playcom.Database.Dao.ISmsFunctionDao;
import com.playcom.Database.Model.EmailFunction;
import com.playcom.Database.Model.SmsFunction;

public class SmsFunctionService {
    ISmsFunctionDao _service;

    public SmsFunctionService(Context context) {
        _service = AppDatabase.GetInstance(context).SmsFunctionDao();
    }

    @SuppressLint("StaticFieldLeak")
    public int InsertAsync(final SmsFunction i) {
        try {
            return new AsyncTask<SmsFunction, Void, Integer>() {
                @Override
                protected Integer doInBackground(SmsFunction... obj) {
                    return _service.Insert(obj[0]).intValue();
                }
            }.execute(i).get();
        } catch (Exception e) {
            return 0;
        }
    }
    @SuppressLint("StaticFieldLeak")
    public SmsFunction GetById(int id) {
        try {
            return new AsyncTask<Integer, Void, SmsFunction>() {
                @Override
                protected SmsFunction doInBackground(Integer... obj) {
                    return _service.FindByPlanId(obj[0]);
                }
            }.execute(id).get();
        } catch (Exception e) {
            return null;
        }
    }
}

