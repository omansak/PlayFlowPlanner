package com.playcom.playflowplanner.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.playcom.Database.Model.Action;
import com.playcom.Database.Model.FunctionCategory;
import com.playcom.Database.Service.ActionService;
import com.playcom.Database.Service.PlanService;
import com.playcom.playflowplanner.ActionActivity;
import com.playcom.playflowplanner.R;

import java.util.List;

public class ActionListAdapter extends BaseAdapter {
    Context _context;
    List<Action> _list;

    public ActionListAdapter(Context context, List<Action> list) {
        _context = context;
        _list = list;
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public Object getItem(int position) {
        return _list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return _list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        ListViewHolder holder;
        if (row == null) {
            row = ((Activity) _context).getLayoutInflater().inflate(R.layout.action_item, parent, false);
            holder = new ListViewHolder();
            holder.Name = (TextView) row.findViewById(R.id.action_item_name);
            holder.Explanation = (TextView) row.findViewById(R.id.action_item_exp);
            holder.Image = (ImageView) row.findViewById(R.id.action_item_img);
            holder.DeleteButton = (Button) row.findViewById(R.id.action_item_delete);
            holder.DoneButton = (Button) row.findViewById(R.id.action_item_done);
            holder.EditButton = (Button) row.findViewById(R.id.action_item_edit);
            row.setTag(holder);
        } else {
            holder = (ListViewHolder) row.getTag();
        }
        final Action action = (Action) this.getItem(position);
        holder.Name.setText(action.getName());
        holder.Explanation.setText(action.getExplanation());
        switch (action.getFunctionCategoryId()) {
            case 1: {
                holder.Image.setBackgroundResource(R.drawable.fc_mail);
            }

        }
        holder.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int planId=action.getPlanId();
                new ActionService(_context).Delete(action);
                Toast.makeText(_context,"Deleted",Toast.LENGTH_LONG).show();
                v.getContext().startActivity( new Intent(_context, ActionActivity.class).putExtra("planId",planId));

            }
        });
        return row;
    }

    private class ListViewHolder {
        ImageView Image;
        TextView Name;
        TextView Explanation;
        TextView RemainTime;
        Button DeleteButton;
        Button EditButton;
        Button DoneButton;

    }
}
