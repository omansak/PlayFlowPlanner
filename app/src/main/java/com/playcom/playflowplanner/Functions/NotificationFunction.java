package com.playcom.playflowplanner.Functions;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.playcom.Database.Model.Action;
import com.playcom.Database.Model.EmailFunction;
import com.playcom.Database.Service.ActionService;
import com.playcom.Database.Service.EmailFunctionService;
import com.playcom.playflowplanner.R;

import java.util.Calendar;

public class NotificationFunction extends BroadcastReceiver {
    private static final String CHANNEL_NAME = "Channel_NAME_OSK";
    private static final String CHANNEL_ID = "CHANNEL_ID_OSK";
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra("n_name");
        int notificationId = intent.getIntExtra("n_id", 0);
        notificationManager.notify(notificationId, notification);
    }
    public void ScheduleNotification(Context context, long delay, int notificationId,String title,String message,Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel myChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(myChannel);
        }
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setContentTitle(title)
                .setSmallIcon(R.drawable.app_icon)
                .setContentIntent(pendingIntent)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, NotificationFunction.class);
        notificationIntent.putExtra("n_id", notificationId);
        notificationIntent.putExtra("n_name", notification);

        PendingIntent pendingNotificationIntent = PendingIntent.getBroadcast(context, notificationId, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingNotificationIntent);

    }

}
