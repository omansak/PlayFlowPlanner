package com.playcom.playflowplanner.Dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.playcom.Database.Model.Action;
import com.playcom.Database.Model.EmailFunction;
import com.playcom.Database.Model.PlanCategory;
import com.playcom.Database.Model.SmsFunction;
import com.playcom.Database.Service.ActionService;
import com.playcom.Database.Service.EmailFunctionService;
import com.playcom.Database.Service.SmsFunctionService;
import com.playcom.playflowplanner.ActionActivity;
import com.playcom.playflowplanner.R;

import java.util.Calendar;
import java.util.List;

public class FunctionSmsAddDialog extends DialogFragment {
    List<PlanCategory> _categories;
    Context _context;
    int _actionId;
    int _planId;

    public FunctionSmsAddDialog GetInstance(Context context, int actionId, int planId) {
        _context = context;
        _actionId = actionId;
        _planId = planId;
        return this;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.function_sms_add, null);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        ((Button) dialogView.findViewById(R.id.dialog_function_btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ((Button) dialogView.findViewById(R.id.dialog_function_btn_create)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText datetv = (EditText) dialogView.findViewById(R.id.function_sms_date);
                EditText timetv = (EditText) dialogView.findViewById(R.id.function_sms_time);
                String[] splitDate = datetv.getText().toString().split("-");
                String[] splitTime = timetv.getText().toString().split(":");
                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(splitDate[0]),Integer.parseInt(splitDate[1]),Integer.parseInt(splitDate[2]),Integer.parseInt(splitTime[0]),Integer.parseInt(splitTime[1]));
                SmsFunction smsFunction = new SmsFunction();
                smsFunction.setDate(cal.getTime());
                smsFunction.setMessage(((EditText) dialogView.findViewById(R.id.function_sms_message)).getText().toString());
                smsFunction.setSmsTo(((EditText) dialogView.findViewById(R.id.function_sms_message_to)).getText().toString());
                int functionId =new SmsFunctionService(_context).InsertAsync(smsFunction);
                Action action = new ActionService(_context).GetById(_actionId);
                action.setFunctionId(functionId);
                action.setFunctionCategoryId(2);
                new ActionService(_context).Update(action);
                startActivity(new Intent(_context, ActionActivity.class).putExtra("planId", _planId).putExtra("setFunction",true));
                alertDialog.dismiss();
            }
        });
        ((Button) dialogView.findViewById(R.id.dialog_function_btn_setDate)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(_context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ((EditText) dialogView.findViewById(R.id.function_sms_date)).setText(year + "-" + month + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay).show();
            }
        });
        ((Button) dialogView.findViewById(R.id.dialog_function_btn_setTime)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                new TimePickerDialog(_context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                ((EditText) dialogView.findViewById(R.id.function_sms_time)).setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true).show();
            }
        });

        return alertDialog;
    }
}
