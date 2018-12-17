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
import android.widget.Button;
import android.widget.TextView;

import com.playcom.Database.Model.Action;
import com.playcom.Database.Service.ActionService;
import com.playcom.playflowplanner.FunctionListActivity;
import com.playcom.playflowplanner.R;

import java.util.Date;

public class ActionAddDialog extends DialogFragment {
    int _planId;
    Context _context;
    public ActionAddDialog GetInstance(Context context, int planId){
        _planId = planId;
        _context = context;
        return this;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.action_add_dialog, null);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        ((Button)dialogView.findViewById(R.id.dialog_actionAdd_btn_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ((Button)dialogView.findViewById(R.id.dialog_actionAdd_btn_create)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tn=(TextView)dialogView.findViewById(R.id.dialog_actionAdd_name);
                TextView te=(TextView)dialogView.findViewById(R.id.dialog_actionAdd_explanation);
                Action action = new Action();
                action.setName(tn.getText().toString());
                action.setExplanation(te.getText().toString());
                action.setDate(new Date());
                action.setPlanId(_planId);
                action.setIsDone(false);
                int _actionId=new ActionService(_context).InsertAsync(action);
                alertDialog.dismiss();
                startActivity(new Intent(_context, FunctionListActivity.class).putExtra("planId", _planId).putExtra("actionId",_actionId));
            }
        });

        return alertDialog;
    }
}
