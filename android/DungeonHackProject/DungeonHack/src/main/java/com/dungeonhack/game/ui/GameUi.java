package com.dungeonhack.game.ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameUi extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_ui);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_ui, menu);
        return true;
    }
    
}
