package com.example.simpleblackjack;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends Activity {
    public static Game blackJack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        blackJack = new Game();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //blackJack = new Game();

        // change views for players first two cards

        List<String> playerCards = blackJack.player;
        List<String> dealerCards = blackJack.dealer;

        // change views for players first two cards
        Button playercard1 = (Button) findViewById(R.id.playercard1);
        changeCardView(playercard1, playerCards.get(0));

        Button playercard2 = (Button) findViewById(R.id.playercard2);
        changeCardView(playercard2, playerCards.get(1));

        // change views for dealer's first two cards
        Button dealercard1 = (Button) findViewById(R.id.dealercard1);
        changeCardView(dealercard1, dealerCards.get(0));

        Button dealercard2 = (Button) findViewById(R.id.dealercard2);
        changeCardView(dealercard2, dealerCards.get(1));
    }


    public void restart(View v) {
        this.finish();
    }

    public void hit(View view)
    {
        List<String> playerCards = blackJack.player;
        int hitCount = blackJack.hit();
        if(hitCount==1)
        {
            Button playercard3 = (Button) findViewById(R.id.playercard3);
            changeCardView(playercard3, playerCards.get(2));
            playercard3.setVisibility(View.VISIBLE);
        }
        if(hitCount==2){
            Button playercard4 = (Button) findViewById(R.id.playercard4);
            changeCardView(playercard4, playerCards.get(3));
            playercard4.setVisibility(View.VISIBLE);
        }
        if(hitCount==3){
            Button playercard5 = (Button) findViewById(R.id.playercard5);
            changeCardView(playercard5, playerCards.get(4));
            playercard5.setVisibility(View.VISIBLE);

            Button hitButton = (Button) findViewById(R.id.deck);
            hitButton.setEnabled(false);
            dealerHit();

        }
    }

    public void dealerHit(){

        List<String> dealerCards = blackJack.dealer;
        List<Button>  dealerNew = new ArrayList<Button>();
        dealerNew.add((Button)findViewById(R.id.dealercard3));
        dealerNew.add((Button)findViewById(R.id.dealercard4));
        dealerNew.add((Button)findViewById(R.id.dealercard5));
        int cardidx= 0;
        int hitNum = blackJack.dealerHit();
        while(blackJack.dealerTotal<blackJack.playerTotal && blackJack.dealerTotal != 21){
            Button dealercard = dealerNew.get(cardidx);
            changeCardView(dealercard, dealerCards.get(cardidx+2));
            dealercard.setVisibility(View.VISIBLE);
            cardidx++;
            SystemClock.sleep(1000);

            if(hitNum == 2){
                break;
            }
            else
            {
                hitNum= blackJack.dealerHit();
            }
        }






    }







    protected void changeCardView(Button card, String card_string){
        String cardName = card_string.toLowerCase();

        // aces
        if(cardName.contains("ace") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.ace_of_clubs);
        else if(cardName.contains("ace") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.ace_of_diamonds);
        else if(cardName.contains("ace") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.ace_of_hearts);
        else if(cardName.contains("ace") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.ace_of_spades);


            // Twos
        else if(cardName.contains("2") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.two_of_clubs);
        else if(cardName.contains("2") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.two_of_diamonds);
        else if(cardName.contains("2") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.two_of_hearts);
        else if(cardName.contains("2") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.two_of_spades);

            //Threes
        else if(cardName.contains("3") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.three_of_clubs);
        else if(cardName.contains("3") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.three_of_diamonds);
        else if(cardName.contains("3") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.three_of_hearts);
        else if(cardName.contains("3") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.three_of_spades);


            // Fours
        else if(cardName.contains("4") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.four_of_clubs);
        else if(cardName.contains("4") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.four_of_diamonds);
        else if(cardName.contains("4") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.four_of_hearts);
        else if(cardName.contains("4") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.four_of_spades);
        
        //Fives
        else if(cardName.contains("5") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.five_of_clubs);
        else if(cardName.contains("5") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.five_of_diamonds);
        else if(cardName.contains("5") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.five_of_hearts);
        else if(cardName.contains("5") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.five_of_spades);

            //Sixs
        else if(cardName.contains("6") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.six_of_clubs);
        else if(cardName.contains("6") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.six_of_diamonds);
        else if(cardName.contains("6") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.six_of_hearts);
        else if(cardName.contains("6") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.six_of_spades);

        //Sevens
        else if(cardName.contains("7") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.seven_of_clubs);
        else if(cardName.contains("7") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.seven_of_diamonds);
        else if(cardName.contains("7") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.seven_of_hearts);
        else if(cardName.contains("7") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.seven_of_spades);

            //Eights
        else if(cardName.contains("8") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.eight_of_clubs);
        else if(cardName.contains("8") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.eight_of_diamonds);
        else if(cardName.contains("8") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.eight_of_hearts);
        else if(cardName.contains("8") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.eight_of_spades);

            //Nines
        else if(cardName.contains("9") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.nine_of_clubs);
        else if(cardName.contains("9") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.nine_of_diamonds);
        else if(cardName.contains("9") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.nine_of_hearts);
        else if(cardName.contains("9") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.nine_of_spades);

            //Tens
        else if(cardName.contains("10") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.ten_of_clubs);
        else if(cardName.contains("10") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.ten_of_diamonds);
        else if(cardName.contains("10") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.ten_of_hearts);
        else if(cardName.contains("10") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.ten_of_spades);

            //Jacks
        else if(cardName.contains("jack") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.jack_of_clubs);
        else if(cardName.contains("jack") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.jack_of_diamonds);
        else if(cardName.contains("jack") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.jack_of_hearts);
        else if(cardName.contains("jack") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.jack_of_spades);

            //Queens
        else if(cardName.contains("queen") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.queen_of_clubs);
        else if(cardName.contains("queen") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.queen_of_diamonds);
        else if(cardName.contains("queen") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.queen_of_hearts);
        else if(cardName.contains("queen") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.queen_of_spades);

        //Kings
        else if(cardName.contains("king") && cardName.contains("clubs"))
            card.setBackgroundResource(R.drawable.king_of_clubs);
        else if(cardName.contains("king") && cardName.contains("diamonds"))
            card.setBackgroundResource(R.drawable.king_of_diamonds);
        else if(cardName.contains("king") && cardName.contains("hearts"))
            card.setBackgroundResource(R.drawable.king_of_hearts);
        else if (cardName.contains("king") && cardName.contains("spades"))
            card.setBackgroundResource(R.drawable.king_of_spades);

    }




}
