package com.playcom.playflowplanner;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.playcom.Database.AppDatabase;
import com.playcom.Database.Model.Plan;

import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        appDatabase = AppDatabase.GetInstance(this);
        Foo();
    }

    private void Foo() {

        Plan plan = new Plan();
        plan.setPlanName("Yılbaşı Planı");
        plan.setExplanation("Yıl başında yapılacaklar");
        plan.setPlanDate(new Date());
        appDatabase.planDoa().InsertPlan(plan);
        for (Plan i: appDatabase.planDoa().GetAll())
        {
            Toast.makeText(this, i.getPlanName(), Toast.LENGTH_LONG).show();
        }

    }

}
