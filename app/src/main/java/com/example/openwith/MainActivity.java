package com.example.openwith;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    EditText Turl, Ttext, Tnumber, Tcall;
    String url, InText, Dnumber, Cnumber, am_pm, pesan;
    int hour, minutes;
    TimePicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Aplikasi Sender");

    }

    public void OpenUrl(View view) {
        Turl = findViewById(R.id.texturl);
        url = Turl.getText().toString();
        Uri link = Uri.parse(url);
        Intent openweb = new Intent(Intent.ACTION_VIEW, link);

        if (openweb.resolveActivity(getPackageManager()) != null){
            startActivity(openweb);
        }

    }

    public void OpenText(View view) {
        Ttext = findViewById(R.id.inputext);
        InText = Ttext.getText().toString();

        Intent sendtext = new Intent(Intent.ACTION_SEND);
        sendtext.putExtra(Intent.EXTRA_TEXT,InText);
        sendtext.setType("text/plain");

        if (sendtext.resolveActivity(getPackageManager()) != null){
            startActivity(sendtext);
        }
    }

    public void OpenDial(View view) {
        Tnumber = findViewById(R.id.phonenumber);
        Dnumber = Tnumber.getText().toString();
        Uri tel = Uri.parse("tel:"+Dnumber);
        Intent phonedial = new Intent(Intent.ACTION_DIAL, tel);

        if (phonedial.resolveActivity(getPackageManager()) != null){
            startActivity(phonedial);
        }
    }

    public void OpenCall(View view) {
        Tcall = findViewById(R.id.numbercall);
        Cnumber = Tcall.getText().toString();
        Uri call = Uri.parse("tel:"+Cnumber);
        Intent callnumber = new Intent(Intent.ACTION_CALL,call);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
        }else {
            if (callnumber.resolveActivity(getPackageManager()) != null) {
                startActivity(callnumber);
            }
        }

    }

    public void SetAlarm(View view) {

        picker = findViewById(R.id.datePicker1);
        if (Build.VERSION.SDK_INT >= 23 ){
            hour = picker.getHour();
            minutes = picker.getMinute();
        }
        else{
            hour = picker.getCurrentHour();
            minutes= picker.getCurrentMinute();
        }
        if(hour > 12) {
            am_pm = "PM";
            hour = hour - 12;
        }
        else
        {
            am_pm="AM";
        }

        pesan = "test";
        Intent setalarm = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, pesan)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (setalarm.resolveActivity(getPackageManager()) != null) {
            startActivity(setalarm);
        }
    }
}
