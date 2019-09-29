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

    /**
     *
     * @return
     */
     public String[] deck() {

         String[] deck = new String[];

         return deck;

     }


    /**
     * Changes the string value of the card to a int value for totalling
     *
     * @param card
     * @return
     */
    public int cardValue(String card) {

        int cardVal;

        // Splits line (card name ex. ace of diamionds) into individual words within an array (ie.
        // splitStr[0] = "ace", splitStr[1]="of", splitStr[2]="diamonds"
        String[] splitStr = card.split("\\s+");

        // splitStr[0] holds first word of each card name (ex. ACE of hearts, TEN of diamonds, etc)
        // We convert that index to int for value
        if(splitStr[0].equals("Ace") || splitStr[0].equals("King") || splitStr[0].equals("Queen") || splitStr[0].equals("Jack")) {

            cardVal = 10;

        }
        else {

            int changeValue = Integer.parseInt(splitStr[0]);
            cardVal = changeValue;

        }

        return cardVal;

     }


    /**
     *
     * Determines if player or dealer hits blackjack (21)
     *
     * @return int
     */
    public boolean blackjackCheck(int total){

        boolean gameover = false;

        if (total == 21)
        {
            gameover = true;
        }

        return gameover;

     }


    /**
     *  Determines if player wins, loses or ties
     *
     * @param player
     * @param dealer
     * @return String;
     */
    public String winner (int player, int dealer) {

        String win = "";

        if (player > dealer)
        {
            win = "Congrats you've won!";
        }
        else if(player == dealer)
        {
            win = "It's a tie!";
        }
        else
            win = "dealer wins";

        return win;

     }





}
