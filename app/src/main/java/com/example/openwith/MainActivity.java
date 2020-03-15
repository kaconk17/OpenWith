package com.example.openwith;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText Turl, Ttext, Tnumber, Tcall, Ttime, Tname, Tphone, Temail;
    String url, InText, Dnumber, Cnumber, pesan, name, phone, email;
    int hour, minutes, setHour, setMinute;
    TimePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Aplikasi Sender");
        Ttime = findViewById(R.id.texttime);
        Ttime.setInputType(InputType.TYPE_NULL);
        Ttime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                hour = cldr.get(Calendar.HOUR_OF_DAY);
                minutes = cldr.get(Calendar.MINUTE);

                picker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sminute) {
                        Ttime.setText(sHour+":"+sminute);
                        setHour= sHour;
                        setMinute = sminute;
                    }
                },hour, minutes, true );
                picker.show();
            }
        });

    }

    public void OpenUrl(View view) {
        Turl = findViewById(R.id.texturl);
        url = Turl.getText().toString();
        Uri link = Uri.parse("https://"+url);
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

        pesan = "test";

        Intent setalarm = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, pesan)
                .putExtra(AlarmClock.EXTRA_HOUR, setHour)
                .putExtra(AlarmClock.EXTRA_MINUTES, setMinute);
        if (setalarm.resolveActivity(getPackageManager()) != null) {
            startActivity(setalarm);
        }


    }

    public void SaveContact(View view) {
        Tname = findViewById(R.id.textname);
        Tphone = findViewById(R.id.textnomer);
        Temail = findViewById(R.id.textemail);

        name = Tname.getText().toString();
        phone = Tphone.getText().toString();
        email = Temail.getText().toString();

        Intent savecontact = new Intent(Intent.ACTION_INSERT);
        savecontact.setType(ContactsContract.Contacts.CONTENT_TYPE);
        savecontact.putExtra(ContactsContract.Intents.Insert.NAME, name);
        savecontact.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
        savecontact.putExtra(ContactsContract.Intents.Insert.PHONE, phone);

        if (savecontact.resolveActivity(getPackageManager()) != null) {
            startActivity(savecontact);
        }
    }
}
