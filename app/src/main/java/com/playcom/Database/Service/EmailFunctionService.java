package com.playcom.Database.Service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.playcom.Database.AppDatabase;
import com.playcom.Database.Dao.IEmailFunctionDoa;
import com.playcom.Database.Dao.IFunctionCategory;
import com.playcom.Database.Model.EmailFunction;
import com.playcom.Database.Model.FunctionCategory;
import com.playcom.Database.Model.Plan;
import com.playcom.Database.Model.PlanCategory;

import java.util.List;

public class EmailFunctionService {
    IEmailFunctionDoa _service;

    public EmailFunctionService(Context context) {
        _service = AppDatabase.GetInstance(context).EmailFunctionDoa();
    }

    @SuppressLint("StaticFieldLeak")
    public int InsertAsync(final EmailFunction i) {
        try {
            return new AsyncTask<EmailFunction, Void, Integer>() {
                @Override
                protected Integer doInBackground(EmailFunction... obj) {
                    return _service.Insert(obj[0]).intValue();
                }
            }.execute(i).get();
        } catch (Exception e) {
            return 0;
        }
    }
    @SuppressLint("StaticFieldLeak")
    public EmailFunction GetById(int id) {
        try {
            return new AsyncTask<Integer, Void, EmailFunction>() {
                @Override
                protected EmailFunction doInBackground(Integer... obj) {
                    return _service.FindByPlanId(obj[0]);
                }
            }.execute(id).get();
        } catch (Exception e) {
            return null;
        }
    }
}
