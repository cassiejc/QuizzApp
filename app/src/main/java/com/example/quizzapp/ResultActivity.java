package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    private TextView ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ts = findViewById(R.id.textView2);

        Bundle extras = getIntent().getExtras();
        String info = extras.getString("info");
//        String scores = extras.getString("scorequizz");

        Toast.makeText(getApplicationContext(), info, Toast.LENGTH_LONG).show();

        int score = getIntent().getIntExtra("score", 0);
        ts.setText("score : " + score);
    }
}