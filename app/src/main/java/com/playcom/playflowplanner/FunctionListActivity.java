package com.playcom.playflowplanner;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.playcom.Database.Service.FunctionCategoryService;
import com.playcom.playflowplanner.Dialog.FunctionMailAddDialog;
import com.playcom.playflowplanner.ListAdapters.FunctionCategoryListAdapter;

public class FunctionListActivity extends AppCompatActivity {

    private Context _context;
    private Application _application;
    private int _planId;
    private int _actionId;

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
                switch ((int)id)
                {
                    case 1:
                    {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                        if (prev != null) {
                            ft.remove(prev);
                        }
                        ft.addToBackStack(null);
                        new FunctionMailAddDialog()
                                .GetInstance(_context,_actionId,_planId)
                                .show(ft, "dialog");
                        break;
                    }
                }
            }
        });
    }
}
