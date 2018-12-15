package com.playcom.playflowplanner.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.playcom.Database.Model.Plan;
import com.playcom.Database.Service.PlanCategoryService;
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
            holder.No = (TextView) row.findViewById(R.id.planNo);
            holder.Name = (TextView) row.findViewById(R.id.planName);
            holder.Explanation = (TextView) row.findViewById(R.id.planExplanation);
            holder.Category = (TextView) row.findViewById(R.id.planCategory);
            row.setTag(holder);
        } else {
            holder = (PlanListViewHolder) row.getTag();
        }
        Plan plan = (Plan) this.getItem(position);
        holder.No.setText(String.valueOf(plan.getId()));
        holder.Name.setText(plan.getName());
        holder.Explanation.setText(plan.getExplanation());
        holder.Category.setText(new PlanCategoryService(_context).GetById(plan.getCategoryId()).getName());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
