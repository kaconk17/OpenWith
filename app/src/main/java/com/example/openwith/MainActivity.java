package com.example.openwith;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText Turl;
    String url;
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
}
