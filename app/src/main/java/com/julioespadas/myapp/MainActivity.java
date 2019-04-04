package com.julioespadas.myapp;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
    private static final int ITEM_ID_TP = 25;
    private static final int ITEM_ID_DP = 26;
    private static final int ITEM_ID_AD = 27;
    private static final int ITEM_ID_SE = 28;
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void createTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, this, 14, 33, true);
        timePickerDialog.show();
    }

    private void createDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void createAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("No money no tacos");
        alertDialogBuilder.setTitle("Alerta:");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Click ok", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ITEM_ID_TP, 0, "Time Picker").setIcon(R.mipmap.ic_timepicker).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, ITEM_ID_DP, 0, "Date Picker").setIcon(R.mipmap.ic_datepicker).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, ITEM_ID_AD, 0, "Alert Dialog").setIcon(R.mipmap.ic_alertdialog).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, ITEM_ID_SE, 0, "Service Exemple").setIcon(R.mipmap.ic_serviceexemple).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == ITEM_ID_TP) {
            createTimePicker();
        } else if (item.getItemId() == ITEM_ID_DP) {
            createDatePicker();
        } else if (item.getItemId() == ITEM_ID_AD) {
            createAlertDialog();
        } else if (item.getItemId() == ITEM_ID_SE) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(this, ServiceExActivity.class));
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, ServiceExActivity.class));
        } else {
            Toast.makeText(this, "You have to agree !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        Toast.makeText(this, simpleDateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set((Calendar.DAY_OF_MONTH), dayOfMonth);
        Toast.makeText(this, simpleDateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
    }
}
