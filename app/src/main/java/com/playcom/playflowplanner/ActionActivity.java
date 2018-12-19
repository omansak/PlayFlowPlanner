package com.playcom.playflowplanner;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.playcom.Database.Model.Action;
import com.playcom.Database.Model.EmailFunction;
import com.playcom.Database.Model.SmsFunction;
import com.playcom.Database.Service.ActionService;
import com.playcom.Database.Service.EmailFunctionService;
import com.playcom.Database.Service.SmsFunctionService;
import com.playcom.playflowplanner.Dialog.ActionAddDialog;
import com.playcom.playflowplanner.Functions.EmailIntentFunction;
import com.playcom.playflowplanner.Functions.NotificationFunction;
import com.playcom.playflowplanner.Functions.SmsIntentFunction;
import com.playcom.playflowplanner.ListAdapters.ActionListAdapter;

import java.util.Calendar;
import java.util.List;

public class ActionActivity extends AppCompatActivity {

    private Context _context;
    private Application _application;
    private int _planId;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    return true;
                case R.id.navigation_settings:
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        _application = this.getApplication();
        _context = this;
        _planId = getIntent().getIntExtra("planId",0);
        SetFunctionListView();
        SetListeners();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                    case 2: {
                        SetDone(action.getId());
                        SetAction(action.getNextAction());
                        SmsFunction smsFunction = new SmsFunctionService(_context).GetById(action.getFunctionId());
                        new SmsIntentFunction(_context,smsFunction.getMessage(),smsFunction.getSmsTo()).SendToSms();
                        break;
                    }
                    case 3: {
                        SetDone(action.getId());
                        SetAction(action.getNextAction());
                        break;
                    }
                    case 4: {
                        SetDone(action.getId());
                        SetAction(action.getNextAction());
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.hediyesepeti.com/"));
                        startActivity(intent);
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
                            SetAction(i.getId());
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
                        .GetInstance(_context,_planId,
                                0)
                        .show(ft, "dialog");
            }
        });
        ((ListView) findViewById(R.id.listView_actions)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SetAction((int)id);
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
                case 2: {
                    SmsFunction smsFunction = new SmsFunctionService(this).GetById(action.getFunctionId());
                    long delay = Math.abs(smsFunction.getDate().getTime() - Calendar.getInstance().getTime().getTime());
                    Intent intent = new Intent(_context, ActionActivity.class).putExtra("planId", _planId).putExtra("actionId", actionId);
                    new NotificationFunction().ScheduleNotification(this, delay, _planId * 1000000 + actionId, action.getName(), action.getExplanation(), intent);
                    action.setIsProcessed(true);
                    new ActionService(_context).Update(action);
                    break;
                }
                case 3: {
                    long delay = Math.abs(action.getDate().getTime() - Calendar.getInstance().getTime().getTime());
                    Intent intent = new Intent(_context, ActionActivity.class).putExtra("planId", _planId).putExtra("actionId", actionId);
                    new NotificationFunction().ScheduleNotification(this, delay, _planId * 1000000 + actionId, action.getName(), action.getExplanation(), intent);
                    action.setIsProcessed(true);
                    new ActionService(_context).Update(action);
                    break;
                }
                case 4: {
                    long delay = Math.abs(action.getDate().getTime() - Calendar.getInstance().getTime().getTime());
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
