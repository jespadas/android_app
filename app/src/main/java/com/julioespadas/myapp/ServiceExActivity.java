package com.julioespadas.myapp;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.julioespadas.myapp.service.LocationService;
import com.squareup.otto.Subscribe;

public class ServiceExActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_ex);
        tv = findViewById(R.id.tv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getBus().unregister(this);
    }

    public void onBtStartClick(View view) {
        startService(new Intent(this, LocationService.class));
    }

    public void onBtStopClick(View view) {
        stopService(new Intent(this, LocationService.class));
    }

    @Subscribe
    public void receiveLocation(Location location) {
        tv.setText(location.toString());
    }


}
