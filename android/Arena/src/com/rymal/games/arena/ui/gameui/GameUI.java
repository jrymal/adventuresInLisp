package com.rymal.games.arena.ui.gameui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rymal.games.arena.R;
import com.rymal.games.arena.engine.GameEngine;
import com.rymal.games.arena.model.player.Color;
import com.rymal.games.arena.model.player.Player;
import com.rymal.games.arena.model.player.PlayerMode;
import com.tymal.games.common.event.Runner;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class GameUI extends Activity {

	private static final List<AIPlayer> AI_PLAYERS = new ArrayList<AIPlayer>();
	private static final int NUM_AI_PLAYERS = 3;
	
	private static class AIPlayer {
		
		private final String _name;
		private final Color _color;
	
		public AIPlayer(String name, Color color) {
			_name = name;
			_color = color;
		}
		
		public String getName() {
			return _name;
		}
		
		public Color getColor(){
			return _color;
		}
	}
	
	static {
		AI_PLAYERS.add(new AIPlayer("Josie", Color.BLACK));
		AI_PLAYERS.add(new AIPlayer("Hannible", Color.GREEN));
		AI_PLAYERS.add(new AIPlayer("Kenny", Color.PURPLE));
		AI_PLAYERS.add(new AIPlayer("Selina", Color.YELLOW));
	}
	
    private GameView gameView;
    private GameEngine _gameEngine;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getUserName( new Runner<String>(){
			@Override
			public void run(String value) {
	            List<Player> players = new ArrayList<Player>();
                
	            // Add user
	            players.add(new Player(value, PlayerMode.USER, Color.RED));
                
	            // shuffle the AI deck to give sense of other players
                Collections.shuffle(AI_PLAYERS);
                for (int i = NUM_AI_PLAYERS; i >= 0 ; i--){
                	AIPlayer player = AI_PLAYERS.get(i);
                	players.add(new Player(player.getName(), PlayerMode.COMPUTER, player.getColor()));
                }
                
                // shuffle the players to randomize the order
                Collections.shuffle(players);
                
                _gameEngine = new GameEngine();
                _gameEngine.startGames(players);
                        
                gameView = new GameView(GameUI.this);
                gameView.setEngine(_gameEngine);
                        
                setContentView(gameView);
			}
		});
	}
	
	private void getUserName(final Runner<String> runner) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getBaseContext().getString(R.string.item_gameui_get_player_name));
		// Set up the input
		final EditText input = new EditText(this);
		// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
		input.setInputType(InputType.TYPE_CLASS_TEXT);
		builder.setView(input);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        runner.run(input.getText().toString());
		    }
		});
		builder.show();
	}
}
