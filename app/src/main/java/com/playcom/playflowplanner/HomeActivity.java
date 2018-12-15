package com.playcom.playflowplanner;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.playcom.playflowplanner.Dialog.PlanAddDialog;
import com.playcom.playflowplanner.ListAdapters.PlanListAdapter;
import com.playcom.Database.Model.Plan;
import com.playcom.playflowplanner.Service.PlanCategoryService;
import com.playcom.playflowplanner.Service.PlanService;

import java.util.List;


public class HomeActivity extends AppCompatActivity {

    private Application _application;
    private Context _context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        _application = this.getApplication();
        _context = this;
        ((ListView) findViewById(R.id.listView_mainPlan))
                .setAdapter(
                        new PlanListAdapter(
                                _context,
                                new PlanService(_context).GetAll())
                );
                new PlanService(_context).GetAllLive().observe(this, new Observer<List<Plan>>() {
            @Override
            public void onChanged(@Nullable List<Plan> plans) {

            }
        });

        ButtonClickListeners();
    }

    private void ButtonClickListeners() {
        ((Button) findViewById(R.id.btn_planAdd)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                new PlanAddDialog()
                        .GetInstance(_application, new PlanCategoryService(_context).GetAll())
                        .show(ft, "planAddDialog");
            }
        });
    }

    /* Examples
    private void Foo() {
        for (PlanCategory i : Objects.requireNonNull(FooAsync())) {
            Toast.makeText(this, i.getName(), Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private List<PlanCategory> FooAsync() {
        try {
            return new AsyncTask<AppDatabase, Void, List<PlanCategory>>() {
                @Override
                protected List<PlanCategory> doInBackground(AppDatabase... appDatabases) {
                    return appDatabases[0].PlanCategoryDao().GetAll();
                }
            }.execute(appDatabase).get();
        } catch (Exception e) {
            return null;
        }
    }*/

}
