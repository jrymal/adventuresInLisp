package com.rymal.games.arena.ui.mainmenu;

import java.io.IOException;
import java.util.ArrayList;

import com.rymal.games.arena.R;
import com.rymal.games.arena.model.deck.DeckLoader;
import com.rymal.games.arena.ui.gameui.GameUI;
import com.rymal.games.common.content.TitleItem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends Activity {

	private ArrayList<TitleItem> listItems=new ArrayList<TitleItem>();

    private ArrayAdapter<TitleItem> adapter;
    private ListView listview;
	
	private static final String NEW_GAME = "new game";
	private static final String SETTINGS = "settings";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		try {
			DeckLoader.createDeck(getAssets().open("deck.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		listview = (ListView) findViewById(R.id.mainmenu_list);
		
        adapter=new ArrayAdapter<TitleItem>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        
	    addItem(NEW_GAME, getString(R.string.item_title_new_game));
	    addItem(SETTINGS, getString(R.string.item_title_settings));
	    
	    adapter.notifyDataSetChanged();
	    
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
	            final TitleItem item = (TitleItem) parent.getItemAtPosition(position);
	            
	            Intent detailIntent = null;
	        	if (NEW_GAME.equals(item.getId())) {
	        		detailIntent = new Intent(MainMenu.this, GameUI.class);
	        	} else if (SETTINGS.equals(item.getId())) {
	        	}
	        	
	        	if (detailIntent != null){
	        		startActivity(detailIntent);
	        	} else {
	        		// log badness
	        	}

			}

	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	private void addItem(String id, String context) {
		TitleItem ti = new TitleItem(id, context);
		listItems.add(ti);
	}

	
}
