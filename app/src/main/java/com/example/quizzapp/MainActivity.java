package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Question[] questionBank = new Question[]{
            new Question(R.string.q1,true),
            new Question(R.string.q2,false),
            new Question(R.string.q3,false),
            new Question(R.string.q4, true),
    };
    private int current=0;
    private int score = 0;
    private TextView t1, scoreTextView;
    private Button bTrue, bFalse, bBack, bNext, bresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.textView);
        bFalse = findViewById(R.id.buttonfalse);
        bTrue = findViewById(R.id.buttontrue);
        bNext = findViewById(R.id.buttonnext);
        bBack = findViewById(R.id.buttonback);
        bresult = findViewById(R.id.buttonresult);
        scoreTextView = findViewById(R.id.scoreTextView);

        bTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        bFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = (current  - 1)%questionBank.length;
                updateQuestion();
            }
        });
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                current = (current + 1)%questionBank.length;
                current = current + 1;
                if(current>=questionBank.length){
                    Intent i= new Intent(getApplicationContext(), ResultActivity.class);
                    i.putExtra("info", "info from Main Activity");
                    i.putExtra("score", score);
                    startActivity(i);
                }
                updateQuestion();
            }
        });
        bresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), ResultActivity.class);
                i.putExtra("info", "info from Main Activity");
                i.putExtra("score", score);
                startActivity(i);
            }
        });
        updateQuestion();
    }
//    private void updateQuestion() {
//        if (current < questionBank.length) {
//            int question = questionBank[current].getResId();
//            t1.setText(question);
//            scoreTextView.setText("Score: " + score);
//        } else {
//            // Display the score and disable the "Next" button
//            t1.setText("Quiz Completed");
//            scoreTextView.setText("Final Score: " + score);
//            bNext.setEnabled(false);
//        }
//    }

    private void updateQuestion() {
        int question = questionBank[current].getResId();
        t1.setText(question);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);
    }
    private void checkAnswer(boolean b) {
        boolean trueAnswer = questionBank[current].getAnswer();
        if (trueAnswer==b){
            Toast.makeText(getApplicationContext(), R.string.responsetrue, Toast.LENGTH_LONG).show();
            questionBank[current].setAnswer(true);
            score++;
        }else {
            Toast.makeText(getApplicationContext(), R.string.responsefalse, Toast.LENGTH_LONG).show();
            questionBank[current].setAnswer(false);
        }
    }
}