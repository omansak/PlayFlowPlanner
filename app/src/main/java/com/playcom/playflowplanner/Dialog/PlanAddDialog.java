package com.playcom.playflowplanner.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.playcom.Database.Model.Plan;
import com.playcom.Database.Model.PlanCategory;
import com.playcom.Database.Service.PlanCategoryService;
import com.playcom.Database.Service.PlanService;
import com.playcom.playflowplanner.ActionActivity;
import com.playcom.playflowplanner.HomeActivity;
import com.playcom.playflowplanner.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlanAddDialog extends DialogFragment {

    List<PlanCategory> _categories;
    Context _context;
    public PlanAddDialog GetInstance(Context context, List<PlanCategory> categories){
        _categories = categories;
        _context = context;
        return this;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.plan_add_dialog, null);

        Spinner spinner = (Spinner)dialogView.findViewById(R.id.dialog_planAdd_spinner_categories);
        List<String> arraySpinner = new ArrayList<String>();
        for (PlanCategory i: _categories) {
            arraySpinner.add(i.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(_context,R.layout.list_textview,R.id.item_textView,arraySpinner);
        adapter.setDropDownViewResource(R.layout.list_textview);
        spinner.setAdapter(adapter);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        ((Button)dialogView.findViewById(R.id.dialog_planAdd_btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ((Button)dialogView.findViewById(R.id.dialog_planAdd_btn_create)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner s= (Spinner)dialogView.findViewById(R.id.dialog_planAdd_spinner_categories);
                TextView tn=(TextView)dialogView.findViewById(R.id.dialog_planAdd_name);
                TextView te=(TextView)dialogView.findViewById(R.id.dialog_planAdd_explanation);
                Plan plan =new Plan();
                plan.setName(tn.getText().toString());
                plan.setExplanation(te.getText().toString());
                plan.setDate(new Date());
                plan.setCategoryId(new PlanCategoryService(_context).GetByName(s.getSelectedItem().toString()).getId());
                alertDialog.dismiss();
                startActivity( new Intent(getActivity(), ActionActivity.class).putExtra("planId",new PlanService(_context).InsertAsync(plan)));
            }
        });

        return alertDialog;
    }
}
