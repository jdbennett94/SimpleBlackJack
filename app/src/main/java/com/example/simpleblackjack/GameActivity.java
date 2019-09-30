package com.example.simpleblackjack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends Activity {
    public static Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ImageView playercard1 = (ImageView) findViewById(R.id.playercard1);
        String card ="ace of clubs";
        changeCardView(playercard1, card);



    }

    protected void changeCardView(ImageView card, String cardName){
        String[] name = cardName.toLowerCase().split(" ");
        switch (name[0]){
            case"ace":
                switch (name[2]){
                    case "clubs":
                        card.setBackgroundResource(R.drawable.ace_of_clubs);
                }
        }
    }

    public void restart(View v) {
        this.finish();
    }


}
