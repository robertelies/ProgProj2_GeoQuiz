package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    private ImageButton mNextActivity;
    private EditText mEdit1;
    private EditText mEdit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mEdit1 = (EditText)findViewById(R.id.editText1);
        mEdit2 = (EditText)findViewById(R.id.editText2);

        mNextActivity = (ImageButton) findViewById(R.id.next_button);
        mNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userone = mEdit1.getText().toString();
                String usertwo = mEdit2.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("first_user", userone);
                intent.putExtra("second_user", usertwo);
                startActivity(intent);
            }
        });
    }
}