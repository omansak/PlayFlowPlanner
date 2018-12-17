package com.playcom.playflowplanner;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.playcom.Database.Model.FunctionCategory;
import com.playcom.Database.Service.ActionService;
import com.playcom.Database.Service.FunctionCategoryService;
import com.playcom.playflowplanner.Dialog.ActionAddDialog;
import com.playcom.playflowplanner.Dialog.FunctionMailAddDialog;
import com.playcom.playflowplanner.ListAdapters.ActionListAdapter;
import com.playcom.playflowplanner.ListAdapters.FunctionCategoryListAdapter;

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
    }

    private void SetFunctionListView() {
        ((ListView) findViewById(R.id.listView_actions)).setAdapter(
                new ActionListAdapter(
                        _context,
                        new ActionService(_context).GetListByPlanId(_planId))
        );

    }
    private void SetListeners(){
        ((Button)findViewById(R.id.action_add_function)).setOnClickListener(new View.OnClickListener() {
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
    }
}
