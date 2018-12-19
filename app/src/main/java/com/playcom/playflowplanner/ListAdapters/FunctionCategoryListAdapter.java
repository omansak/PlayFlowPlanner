package com.playcom.playflowplanner.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.playcom.Database.Model.FunctionCategory;
import com.playcom.playflowplanner.R;

import java.util.List;
import java.util.Random;

public class FunctionCategoryListAdapter extends BaseAdapter {

    Context _context;
    List<FunctionCategory> _list;

    public FunctionCategoryListAdapter(Context context, List<FunctionCategory> list) {
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
            row = ((Activity) _context).getLayoutInflater().inflate(R.layout.function_item, parent, false);
            holder = new ListViewHolder();
            holder.Name = (TextView) row.findViewById(R.id.function_item_name);
            holder.Explanation = (TextView) row.findViewById(R.id.function_item_exp);
            holder.Image = (ImageView) row.findViewById(R.id.function_item_img);
            row.setTag(holder);
        } else {
            holder = (ListViewHolder) row.getTag();
        }
        final FunctionCategory functionCategory = (FunctionCategory) this.getItem(position);
        holder.Name.setText(functionCategory.getName());
        holder.Explanation.setText(functionCategory.getExplanation());
        switch (functionCategory.getId()) {
            case 1: {
                holder.Image.setBackgroundResource(R.drawable.fc_mail);
                break;
            }
            case 2: {
                holder.Image.setBackgroundResource(R.drawable.sms);
                break;
            }
            case 3: {
                holder.Image.setBackgroundResource(R.drawable.alert);
                break;
            }
            case 4: {
                holder.Image.setBackgroundResource(R.drawable.gift);
                break;
            }
        }
        return row;
    }

    private class ListViewHolder {
        ImageView Image;
        TextView Name;
        TextView Explanation;
        Button AddButton;

    }

}
