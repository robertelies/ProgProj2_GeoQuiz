package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cheat extends AppCompatActivity {

    private TextView cheatquestion;
    private TextView cheatanswer;
    private Button mBackbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Intent intent = getIntent();
        String quest = intent.getStringExtra("question_cheat");
        Boolean ans = intent.getBooleanExtra("result_cheat", false);

        cheatquestion = (TextView) findViewById(R.id.question_display);
        cheatquestion.setText(quest);

        cheatanswer = (TextView) findViewById(R.id.answer_display);
        if(ans == true){
            cheatanswer.setText("TRUE");
        }
        else{
            cheatanswer.setText("FALSE");
        }

        mBackbutton = (Button) findViewById(R.id.goback_button);
        mBackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}