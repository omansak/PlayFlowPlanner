package com.playcom.playflowplanner;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.playcom.Database.Service.PlanCategoryService;
import com.playcom.Database.Service.PlanService;
import com.playcom.playflowplanner.Dialog.PlanAddDialog;
import com.playcom.playflowplanner.ListAdapters.PlanListAdapter;

;


public class HomeActivity extends AppCompatActivity {

    private Application _application;
    private Context _context;
    private ListView PlanListView;
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
        setContentView(R.layout.activity_home);
        _application = this.getApplication();
        _context = this;
        SetComponents();
        Listeners();
        SetPlanList();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void Listeners() {
        ((FloatingActionButton) findViewById(R.id.btn_planAdd)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                new PlanAddDialog()
                        .GetInstance(_application, new PlanCategoryService(_context).GetAll(),0)
                        .show(ft, "dialog");
            }
        });

        PlanListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int planId = (int) id;
                startActivity(new Intent(getApplicationContext(), ActionActivity.class).putExtra("planId", planId));
            }
        });

    }

    private void SetPlanList() {
        ((ListView) findViewById(R.id.listView_mainPlan))
                .setAdapter(
                        new PlanListAdapter(
                                _context,
                                new PlanService(_context).GetAll())
                );
        new PlanService(_context).GetAll();
    }

    private void SetComponents() {
        PlanListView = ((ListView) findViewById(R.id.listView_mainPlan));
        PlanListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

}
