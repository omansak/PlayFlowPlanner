package com.playcom.playflowplanner.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.playcom.Database.Model.Plan;
import com.playcom.Database.Service.PlanCategoryService;
import com.playcom.Database.Service.PlanService;
import com.playcom.playflowplanner.ActionActivity;
import com.playcom.playflowplanner.Dialog.PlanAddDialog;
import com.playcom.playflowplanner.HomeActivity;
import com.playcom.playflowplanner.R;

import java.util.List;

public class PlanListAdapter extends BaseAdapter {
    Context _context;
    List<Plan> _plans;

    public PlanListAdapter(Context context ,List<Plan> plans) {
        _context = context;
        _plans = plans;
    }

    @Override
    public int getCount() {
        return _plans.size();
    }

    @Override
    public Object getItem(int position) {
        return _plans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return _plans.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        PlanListViewHolder holder;
        if (row == null) {
            row = ((Activity) _context).getLayoutInflater().inflate(R.layout.plan_item, parent, false);
            holder = new PlanListViewHolder();
            holder.Name = (TextView) row.findViewById(R.id.planName);
            holder.Explanation = (TextView) row.findViewById(R.id.planExplanation);
            holder.Category = (TextView) row.findViewById(R.id.planCategory);
            row.setTag(holder);
        } else {
            holder = (PlanListViewHolder) row.getTag();
        }
        final Plan plan = (Plan) this.getItem(position);
        holder.Name.setText(plan.getName());
        holder.Explanation.setText(plan.getExplanation());
        holder.Category.setText(new PlanCategoryService(_context).GetById(plan.getCategoryId()).getName());
        ((Button)row.findViewById(R.id.plan_btn_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlanService planService = new PlanService(_context);
                planService.DeleteAsync(plan);
                _context.startActivity(new Intent(_context, HomeActivity.class));
            }
        });
        ((Button)row.findViewById(R.id.plan_btn_edit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = ((FragmentActivity)_context).getSupportFragmentManager().beginTransaction();
                Fragment prev = ((FragmentActivity)_context).getSupportFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                new PlanAddDialog()
                        .GetInstance(_context, new PlanCategoryService(_context).GetAll(),plan.getId())
                        .show(ft, "dialog");
            }
        });
        return row;
    }

    private class PlanListViewHolder {
        TextView No;
        TextView Name;
        TextView Explanation;
        TextView Category;
    }
}
