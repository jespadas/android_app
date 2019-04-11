package com.julioespadas.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.julioespadas.myapp.utils.NotificationUtils;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
    }

    public void onBtNowClick(View view) {
        NotificationUtils.createInstantNotification(this, "Notification immédiate");
    }

    public void onBt10sClick(View view) {
        NotificationUtils.scheduleNotification(this, "Notification programmé", 5000);
    }

}
