package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;



public class Leaderboard extends AppCompatActivity {

    public static void quicksort(shared_player A[], int izq, int der) {

        shared_player pivote=A[izq]; // tomamos primer elemento como pivote
        int i=izq;         // i realiza la búsqueda de izquierda a derecha
        int j=der;         // j realiza la búsqueda de derecha a izquierda
        shared_player aux;

        while(i < j){                          // mientras no se crucen las búsquedas
            while(A[i].getPunt() <= pivote.getPunt() && i < j) i++; // busca elemento mayor que pivote
            while(A[j].getPunt() > pivote.getPunt()) j--;           // busca elemento menor que pivote
            if (i < j) {                        // si no se han cruzado
                aux= A[i];                      // los intercambia
                A[i]=A[j];
                A[j]=aux;
            }
        }

        A[izq]=A[j];      // se coloca el pivote en su lugar de forma que tendremos
        A[j]=pivote;      // los menores a su izquierda y los mayores a su derecha

        if(izq < j-1)
            quicksort(A,izq,j-1);          // ordenamos subarray izquierdo
        if(j+1 < der)
            quicksort(A,j+1,der);          // ordenamos subarray derecho

    }

    private Button next;
    private TextView leaderboard;

    private Button restart_0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        //open the shared references object in order to store info
        SharedPreferences nube = getSharedPreferences("datos", Context.MODE_PRIVATE);
        int total_users = nube.getInt("total_users", 0);

        if(total_users > 1) {
            //create the array of all players in order to sort it and show it
            shared_player[] all_players = new shared_player[total_users];


            String s3 = " ";
            for (int i = 0; i < total_users; i++) {
                String[] aux = nube.getString(Integer.toString(i), "0;0").split(";");
                all_players[i] = new shared_player(aux[0], Integer.parseInt(aux[1]));

            }
            quicksort(all_players, 0, total_users - 1);

            for (int i = 0; i < total_users; i++) {
                s3 = all_players[i].getName() + ": " + all_players[i].getPunt() + "\n" + s3;
            }

            //display the list of users
            leaderboard = (TextView) findViewById(R.id.leaderboard_view);
            leaderboard.setText(s3);

        }else{
            leaderboard = (TextView) findViewById(R.id.leaderboard_view);
            leaderboard.setText("No players yet..");
        }

        //restart
        restart_0 = (Button) findViewById(R.id.restart_button);
        restart_0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Registration.class);
               finish();
               startActivity(intent2);
            }
        });
        //leaderboard.setText();
    }
}