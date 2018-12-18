package com.playcom.playflowplanner;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.playcom.Database.Model.Action;
import com.playcom.Database.Model.EmailFunction;
import com.playcom.Database.Service.ActionService;
import com.playcom.Database.Service.EmailFunctionService;
import com.playcom.playflowplanner.Dialog.ActionAddDialog;
import com.playcom.playflowplanner.Functions.EmailIntentFunction;
import com.playcom.playflowplanner.Functions.NotificationFunction;
import com.playcom.playflowplanner.ListAdapters.ActionListAdapter;

import java.util.Calendar;
import java.util.List;

public class ActionActivity extends AppCompatActivity {

    private Context _context;
    private Application _application;
    private int _planId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        _application = this.getApplication();
        _context = this;
        _planId = getIntent().getIntExtra("planId",0);
        SetFunctionListView();
        SetListeners();
        if (getIntent().getIntExtra("actionId", 0) != 0) {
            Action action = new ActionService(this).GetById(getIntent().getIntExtra("actionId", 0));
            if (action != null) {
                switch (action.getFunctionCategoryId()) {
                    case 1: {
                        SetDone(action.getId());
                        SetAction(action.getNextAction());
                        EmailFunction emailFunction = new EmailFunctionService(_context).GetById(action.getFunctionId());
                        new EmailIntentFunction(_context, emailFunction.getSubject(), emailFunction.getMessage(), emailFunction.getMessageTo()).SendToMail();
                        break;
                    }
                }
            }
        }
        else
        {
            if (getIntent().getBooleanExtra("setFunction", false)) {
                List<Action> actions = new ActionService(_context).GetListByPlanId(_planId);
                for (Action i : actions) {
                    if (!i.getIsDone()) {
                        if(!i.getIsProcessed())
                        {
                            switch (i.getFunctionCategoryId()) {
                                case 1: {
                                    SetAction(i.getId());
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    private void SetFunctionListView() {
        ((ListView) findViewById(R.id.listView_actions)).setAdapter(
                new ActionListAdapter(
                        _context,
                        new ActionService(_context).GetListByPlanId(_planId))
        );

    }

    private void SetListeners(){
        ((FloatingActionButton)findViewById(R.id.action_add_function)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                new ActionAddDialog()
                        .GetInstance(_context,_planId)
                        .show(ft, "dialog");
            }
        });
        ((ListView) findViewById(R.id.listView_actions)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Action action = new ActionService(_context).GetById((int) id);
                switch (action.getFunctionCategoryId()) {
                    case 1: {
                        EmailFunction emailFunction = new EmailFunctionService(_context).GetById(action.getFunctionId());
                        new EmailIntentFunction(_context, emailFunction.getSubject(), emailFunction.getMessage(), emailFunction.getMessageTo()).SendToMail();
                        break;
                    }
                }

            }
        });
    }

    private void SetDone(int actionId) {
        Action action = new ActionService(this).GetById(actionId);
        action.setIsDone(true);
        new ActionService(this).Update(action);
    }

    private void SetAction(int actionId) {
        Action action = new ActionService(this).GetById(actionId);
        if(action != null)
        {
            switch (action.getFunctionCategoryId()) {
                case 1: {
                    EmailFunction emailFunction = new EmailFunctionService(this).GetById(action.getFunctionId());
                    long delay = Math.abs(emailFunction.getDate().getTime() - Calendar.getInstance().getTime().getTime());
                    Intent intent = new Intent(_context, ActionActivity.class).putExtra("planId", _planId).putExtra("actionId", actionId);
                    new NotificationFunction().ScheduleNotification(this, delay, _planId * 1000000 + actionId, action.getName(), action.getExplanation(), intent);
                    action.setIsProcessed(true);
                    new ActionService(_context).Update(action);
                    break;
                }
            }

        }
    }
}
