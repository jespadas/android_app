package com.julioespadas.myapp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

public class LanguageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Language changed : " + Locale.getDefault().getDisplayLanguage(), Toast.LENGTH_SHORT).show();
        Log.w("TAG_", "Language changed : " + Locale.getDefault().getDisplayLanguage());
    }
}
