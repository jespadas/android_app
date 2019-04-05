package com.julioespadas.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.julioespadas.myapp.service.LocationService;

public class ServiceExActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_ex);
    }

    public void onBtsStartClick(View view) {
        startService(new Intent(this, LocationService.class));
    }

    public void onBtsStopClick(View view) {
        stopService(new Intent(this, LocationService.class));
    }
}
