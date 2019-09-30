package com.example.simpleblackjack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

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
        /*Button buttonID4 = (Button) findViewById(R.id.stick4);
        buttonID4.setBackgroundResource(R.drawable.graybutton);
        buttonID4.setEnabled(false);

         */
    }

    public void restart(View v) {
        this.finish();
    }


}
