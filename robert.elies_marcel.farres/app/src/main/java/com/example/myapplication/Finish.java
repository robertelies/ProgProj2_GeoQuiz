package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Finish extends AppCompatActivity {

    private TextView player_won;
    private TextView player_1_punt;
    private TextView player_2_punt;
    private Button continue_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        //open the shared references object in order to store info
        SharedPreferences nube = getSharedPreferences("datos", Context.MODE_PRIVATE);
        int total_users = nube.getInt("total_users", 0); //este 1 indica que metera en total_users un 1 en caso que no existar toatal_users en shared preferences


        Intent intent = getIntent();
        int score_p1 = intent.getIntExtra("score_p1", 0);
        int score_p2 = intent.getIntExtra("score_p2", 0);
        String userone = intent.getStringExtra("first_user");
        String usertwo = intent.getStringExtra("second_user");

        player_won = (TextView) findViewById(R.id.player_won);
        player_1_punt = (TextView) findViewById(R.id.player_1_punt);
        player_2_punt = (TextView) findViewById(R.id.player_2_punt);


        if(score_p1 > score_p2){
            player_won.setText(userone + " won");
        } else{
            if(score_p1 == score_p2){
                player_won.setText(userone + " and " + usertwo +" tied");
            } else{
                player_won.setText(usertwo + " won");
            }
        }
        player_1_punt.setText("SCORE " + userone + ": " + score_p1);
        player_2_punt.setText("SCORE " + usertwo + ": " + score_p2);


        //we send the names and its puntuations
        SharedPreferences.Editor editor=nube.edit();
        editor.putString(Integer.toString(total_users ), userone + ";" + score_p1 );
        editor.apply();



        editor.putString(Integer.toString(total_users + 1), usertwo + ";" + score_p2 );
        editor.apply();


        //now we update the total number of players stored in the shared preferences

        editor.putInt("total_users", total_users + 2 );
        editor.apply();




        continue_button = (Button) findViewById(R.id.continue_button);
        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Leaderboard.class);

                startActivity(intent2);
            }
        });



    }
}