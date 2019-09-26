package com.example.simpleblackjack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Start Game Button
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {

                //Deck initialized and shuffled

                //Dealer given two cards

                //Player given two cards

                //Check for 21 on both (method)

                //Option for player to hit/stay

                //Check over 21

                //Option to hit stay

                //Dealer draw


            }
        });


    }
}
