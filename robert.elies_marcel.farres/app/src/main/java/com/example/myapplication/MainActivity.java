package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private boolean buttonresult;
    private int[] responded = new int[6];
    private int p1_score = 0;
    private int p2_score = 0;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mFinishButton;
    private ImageButton mRightArrow;
    private ImageButton mLeftArrow;
    public TextView currentQuestion;
    public TextView currentPlayer;
    private Question[] arr = new Question[]{
            new Question("Canberra is the capital of Australia?",true, 1),
            new Question("Is Paris a country?",false, 2),
            new Question("Italian is the most spoken language?",false, 3),
            new Question("The highest mountain in the world is Mount Everest?",true, 4),
            new Question("The USA has 50 states?",true, 5),
            new Question("India has the largest population?", false, 6)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userone = intent.getStringExtra("first_user");
        String usertwo = intent.getStringExtra("second_user");

        currentQuestion = (TextView) findViewById(R.id.questions_text);
        currentQuestion.setText(arr[counter].getName());

        currentPlayer = (TextView) findViewById(R.id.name_player);
        currentPlayer.setText(userone);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick (View v){
                //meter dentro un switch con todos los botones, cuando clica el de next o previuoyus ca,nias el texto y el id
                buttonresult = true;
                if(responded[counter] == 1){
                    Toast.makeText(MainActivity.this, R.string.repeated_toast, Toast.LENGTH_SHORT).show();
                }
                else{
                    if(arr[counter].getResult() == buttonresult){
                        Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                        if(counter % 2 == 0){
                            p1_score = p1_score + 1;
                        }
                        else{
                            p2_score = p2_score + 1;
                        }
                        responded[counter] = 1;
                    }
                    else{
                        Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                        responded[counter] = 1;
                    }
                }
            }
            });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                buttonresult = false;
                if(arr[counter].getResult() == buttonresult){
                    if(responded[counter] == 1){
                        Toast.makeText(MainActivity.this, R.string.repeated_toast, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                        if(counter % 2 == 0){
                            p1_score = p1_score + 1;
                        }
                        else{
                            p2_score = p2_score + 1;
                        }
                        responded[counter] = 1;
                    }
                }
                else{
                    if(responded[counter] == 1){
                        Toast.makeText(MainActivity.this, R.string.repeated_toast, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
                        responded[counter] = 1;
                    }
                }
            }
            });
        mLeftArrow = (ImageButton) findViewById(R.id.previous_button);
        mLeftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter == 0){
                    counter = 5;
                }
                else{
                    counter = counter - 1;
                }
                currentQuestion.setText(arr[counter].getName());
                if(counter % 2 == 0){
                    currentPlayer.setText(userone);
                }
                else{
                    currentPlayer.setText(usertwo);
                }
            }
            });
        mRightArrow = (ImageButton) findViewById(R.id.next_button);
        mRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter == 5){
                    counter = 0;
                }
                else{
                    counter = counter + 1;
                }
                currentQuestion.setText(arr[counter].getName());
                if(counter % 2 == 0){
                    currentPlayer.setText(userone);
                }
                else{
                    currentPlayer.setText(usertwo);
                }
            }
            });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Cheat.class);
                intent.putExtra("question_cheat", arr[counter].getName());
                intent.putExtra("result_cheat", arr[counter].getResult());
                startActivity(intent);
            }
        });

        mFinishButton = (Button) findViewById((R.id.finish_button));
        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Finish.class);
                intent2.putExtra("score_p1", p1_score);
                intent2.putExtra("score_p2", p2_score);
                intent2.putExtra("first_user", userone);
                intent2.putExtra("second_user", usertwo);
                startActivity(intent2);
            }
        });
    }
}