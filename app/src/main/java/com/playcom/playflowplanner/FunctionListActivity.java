package com.playcom.playflowplanner;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.playcom.Database.Service.FunctionCategoryService;
import com.playcom.playflowplanner.Dialog.FunctionAlertAddDialog;
import com.playcom.playflowplanner.Dialog.FunctionMailAddDialog;
import com.playcom.playflowplanner.Dialog.FunctionSmsAddDialog;
import com.playcom.playflowplanner.ListAdapters.FunctionCategoryListAdapter;

public class FunctionListActivity extends AppCompatActivity {

    private Context _context;
    private Application _application;
    private int _planId;
    private int _actionId;
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
        setContentView(R.layout.activity_function_list);
        _application = this.getApplication();
        _context = this;
        _planId = getIntent().getIntExtra("planId", 0);
        _actionId = getIntent().getIntExtra("actionId", 0);
        SetFunctionListView();
        SetListeners();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void SetFunctionListView() {
        ((ListView) findViewById(R.id.listView_functions)).setAdapter(
                new FunctionCategoryListAdapter(
                        _context,
                        new FunctionCategoryService(_context).GetAll())
        );

    }
    private void SetListeners(){
        ((ListView) findViewById(R.id.listView_functions)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                switch ((int)id)
                {
                    case 1:
                    {
                        new FunctionMailAddDialog()
                                .GetInstance(_context,_actionId,_planId)
                                .show(ft, "dialog");
                        break;
                    }
                    case 2:
                    {
                        ft.addToBackStack(null);
                        new FunctionSmsAddDialog()
                                .GetInstance(_context,_actionId,_planId)
                                .show(ft, "dialog");
                        break;
                    }
                    case 3:
                    {
                        ft.addToBackStack(null);
                        new FunctionAlertAddDialog()
                                .GetInstance(_context,_actionId,_planId,0)
                                .show(ft, "dialog");
                        break;
                    }
                    case 4:
                    {
                        ft.addToBackStack(null);
                        new FunctionAlertAddDialog()
                                .GetInstance(_context,_actionId,_planId,1)
                                .show(ft, "dialog");
                        break;
                    }
                }
            }
        });
    }
}
