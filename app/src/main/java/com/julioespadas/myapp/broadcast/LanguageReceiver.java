package com.julioespadas.myapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.julioespadas.myapp.utils.NotificationUtils;

import java.util.Locale;

public class LanguageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_LOCALE_CHANGED)) {

            Log.w("TAG_", "Changement de langue : " + Locale.getDefault().getDisplayLanguage());
            Toast.makeText(context, "Changement de langue : " + Locale.getDefault().getDisplayLanguage(),
                    Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.w("TAG_", "Boot");
            NotificationUtils.createInstantNotification(context, "Démarrage");
        }

    }
}
