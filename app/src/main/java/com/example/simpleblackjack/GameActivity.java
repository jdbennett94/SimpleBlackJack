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
    public boolean DealerTurn= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        blackJack = new Game();
    }

    @Override
    protected void onStart() {
        super.onStart();


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

        if(blackJack.blackJack(blackJack.playerTotal)){
            win();
        }


    }


    public void restart(View v) {
        Button result = (Button) findViewById(R.id.result);
        result.setVisibility(View.INVISIBLE);
        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        playAgain.setEnabled(false);

        this.finish();
    }

    public void hit(View view) {
        //increment player deck

        List<String> playerCards = blackJack.player;
        int hitCount = blackJack.hit(DealerTurn);
        boolean win = false;
        if (!DealerTurn) {
            if (hitCount == 1) {

                Button playercard3 = (Button) findViewById(R.id.playercard3);
                changeCardView(playercard3, playerCards.get(2));
                playercard3.setVisibility(View.VISIBLE);

            }

            if (hitCount == 2) {
                Button playercard4 = (Button) findViewById(R.id.playercard4);
                changeCardView(playercard4, playerCards.get(3));
                playercard4.setVisibility(View.VISIBLE);
            }

            if (hitCount == 3) {
                Button playercard5 = (Button) findViewById(R.id.playercard5);
                changeCardView(playercard5, playerCards.get(4));
                playercard5.setVisibility(View.VISIBLE);
                Button hitButton = (Button) findViewById(R.id.deck);
                hitButton.setEnabled(false);

                if (blackJack.blackJack(blackJack.playerTotal)){
                    win();
                win = true;
            }
                else if (blackJack.bust(blackJack.playerTotal))
                    lost();

            }


            if (blackJack.blackJack(blackJack.playerTotal)){
                win();
                win = true;
            }
            else if (blackJack.bust(blackJack.playerTotal))
                lost();
            //else if (blackJack.playerWin())
              //  win();
            if(!win && hitCount==3){
                DealerTurn = true;

            }
        }


        if (DealerTurn)
            // change to dealerhit code
            dealerHit();
    }






        /*
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
        if(hitCount==3) {
            Button playercard5 = (Button) findViewById(R.id.playercard5);
            changeCardView(playercard5, playerCards.get(4));
            playercard5.setVisibility(View.VISIBLE);
            Button hitButton = (Button) findViewById(R.id.deck);
            hitButton.setEnabled(false);
        }
        Boolean contunePlaying =playerEndGame(blackJack.playerTotal);
        if (contunePlaying && hitCount==3)
            dealerHit();
        */


    public void dealerHit() {

        List<String> dealerCards = blackJack.dealer;
        List<Button> dealerNew = new ArrayList<Button>();
        dealerNew.add((Button) findViewById(R.id.dealercard3));
        dealerNew.add((Button) findViewById(R.id.dealercard4));
        dealerNew.add((Button) findViewById(R.id.dealercard5));
        int cardidx = 0;
        //blackJack.hitCount = 0;

        while(cardidx<3) {
            int dealerHit = blackJack.hit(DealerTurn);
            SystemClock.sleep(1000);
            Button dealercard = dealerNew.get(cardidx);
            changeCardView(dealercard, dealerCards.get(cardidx + 2));
            dealercard.setVisibility(View.VISIBLE);

            if (blackJack.blackJack(blackJack.dealerTotal)) {
                lost();
                break;
            }
            else if (blackJack.bust(blackJack.dealerTotal)){
                win();
                break;
            }
            else if (blackJack.dealerWin()){
                lost();
                break;
            }
            cardidx++;
        }

        if(blackJack.playerWin())
            win();
        else if(blackJack.tie())
            tie();

    }

    /*
    Returns True if we keep playing
     */
    public boolean playerEndGame(int total)
    {
        if (total==21) {
            win();
            return false;
        }
        else if(total>21) {
            lost();
            return false;
        }
        else
            return true;
    }

    public boolean dealerEndGame(int total)
    {
        if (total==21) {
            lost();
            return false;
        }
        else if(total>21) {
            win();
            return false;
        }
        else
            return true;
    }

    public void stop(View view){
        Button hitButton = (Button) findViewById(R.id.deck);
        hitButton.setEnabled(false);

        dealerHit();
    }

    public void lost() {
        Button result = (Button) findViewById(R.id.result);
        result.setVisibility(View.VISIBLE);

        Button restart = (Button) findViewById(R.id.restartbutton);
        restart.setVisibility(View.INVISIBLE);
        restart.setEnabled(false);

        Button stop = (Button) findViewById(R.id.stopbutton);
        stop.setVisibility(View.INVISIBLE);

        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.VISIBLE);
        playAgain.setEnabled(true);

        Button hit = (Button) findViewById(R.id.deck);
        hit.setVisibility(View.INVISIBLE);
        hit.setEnabled(false);
    }

    public void win()
    {
        Button result = (Button) findViewById(R.id.result);
        result.setText("You won!!");
        result.setVisibility(View.VISIBLE);

        Button restart = (Button) findViewById(R.id.restartbutton);
        restart.setVisibility(View.INVISIBLE);
        restart.setEnabled(false);

        Button deck = (Button) findViewById(R.id.deck);
        deck.setVisibility(View.INVISIBLE);
        deck.setEnabled(false);

        Button stop = (Button) findViewById(R.id.stopbutton);
        stop.setVisibility(View.INVISIBLE);

        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.VISIBLE);
        playAgain.setEnabled(true);
    }

    public void tie()
    {
        Button result = (Button) findViewById(R.id.result);
        result.setText(R.string.tie);
        result.setVisibility(View.VISIBLE);

        Button restart = (Button) findViewById(R.id.restartbutton);
        restart.setVisibility(View.INVISIBLE);
        restart.setEnabled(false);

        Button stop = (Button) findViewById(R.id.stopbutton);
        stop.setVisibility(View.INVISIBLE);

        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.VISIBLE);
        playAgain.setEnabled(true);
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
