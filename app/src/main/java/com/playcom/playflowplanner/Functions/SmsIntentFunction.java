package com.playcom.playflowplanner.Functions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class SmsIntentFunction {
    String _message;
    String _messageTo;
    Context _context;

    public SmsIntentFunction(Context _context,String _message, String _messageTo) {
        this._message = _message;
        this._messageTo = _messageTo;
        this._context = _context;
    }
    public void SendToSms() {
        try {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + Uri.encode(_messageTo)));
            intent.putExtra("address", _messageTo);
            intent.putExtra("sms_body", _message);
            _context.startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {
            ex.getMessage();
        }
    }
}
