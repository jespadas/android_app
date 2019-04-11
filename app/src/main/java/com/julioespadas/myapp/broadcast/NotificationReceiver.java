package com.julioespadas.myapp.broadcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Notification notification = intent.getParcelableExtra("MaCle");

        //Envoyer la notification
        NotificationManagerCompat ncm = NotificationManagerCompat.from(context);

        //ENVOIE
        ncm.notify(29, notification);

    }
}
