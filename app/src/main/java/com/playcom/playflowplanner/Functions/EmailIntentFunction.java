package com.playcom.playflowplanner.Functions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class EmailIntentFunction {
    String _subject;
    String _message;
    String _messageTo;
    Context _context;

    public EmailIntentFunction(Context _context,String _subject, String _message, String _messageTo) {
        this._subject = _subject;
        this._message = _message;
        this._messageTo = _messageTo;
        this._context = _context;
    }

    public void SendToMail(){
        try {
        _context.startActivity(Intent.createChooser(GetIntent(), "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            ex.getMessage();
        }
    }
    public Intent GetIntent(){
        try {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{_messageTo});
            i.putExtra(Intent.EXTRA_SUBJECT, _subject);
            i.putExtra(Intent.EXTRA_TEXT, _message);
           return i;
        } catch (android.content.ActivityNotFoundException ex) {
            return null;
        }
    }
}
