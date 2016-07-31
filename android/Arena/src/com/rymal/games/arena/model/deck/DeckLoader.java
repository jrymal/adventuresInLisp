package com.rymal.games.arena.model.deck;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.rymal.games.arena.constant.xml.DeckXmlConstants;
import com.rymal.games.arena.model.deck.card.Card;
import com.rymal.games.arena.model.deck.card.CardName;
import com.rymal.games.arena.model.deck.card.CreatureType;
import com.rymal.games.common.xml.XmlUtil;

import android.os.AsyncTask;

public class DeckLoader extends AsyncTask<InputStream, String, Deck> {

	private static DeckLoader _deckLoader = new DeckLoader();
	private static Deck _deck = null;
	
	public static void createDeck(InputStream inputStream) {
		_deckLoader.execute(inputStream);
	}
	
	public static synchronized Deck getDeck() throws InterruptedException, ExecutionException {
		if (_deck == null) {
			_deck = _deckLoader.get();
		}
		return _deck;
	}

	
	
	private List<Card> loadDeck(InputStream is) throws XmlPullParserException, IOException {
		XmlPullParser xrp = XmlPullParserFactory.newInstance().newPullParser();
		xrp.setInput(is, null);
		
		List<Card> cards = new ArrayList<Card>();
		
        int eventType = xrp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
        
        	if(eventType == XmlPullParser.START_TAG 
        			&& DeckXmlConstants.ELE_CARD.equals(xrp.getName())) {
        		String cardName = xrp.getAttributeValue(DeckXmlConstants.EMPTY_STRING, DeckXmlConstants.ATTR_CARDNAME);
        		String creatureType = xrp.getAttributeValue(DeckXmlConstants.EMPTY_STRING, DeckXmlConstants.ATTR_CREATURETYPE);
        		
        		String value_str = xrp.getAttributeValue(DeckXmlConstants.EMPTY_STRING, DeckXmlConstants.ATTR_VALUE);
        		String count_str = xrp.getAttributeValue(DeckXmlConstants.EMPTY_STRING, DeckXmlConstants.ATTR_COUNT);
        		String range_min_str = xrp.getAttributeValue(DeckXmlConstants.EMPTY_STRING, DeckXmlConstants.ATTR_RANGE_MIN);
        		String range_max_str = xrp.getAttributeValue(DeckXmlConstants.EMPTY_STRING, DeckXmlConstants.ATTR_RANGE_MAX);
        		
        		int value = XmlUtil.parseXmlValue(value_str, Card.NO_VALUE);
        		int count = XmlUtil.parseXmlValue(count_str, 1);
        		int range_min = XmlUtil.parseXmlValue(range_min_str, -1);
        		int range_max = XmlUtil.parseXmlValue(range_max_str, -1);
        		
        		for (int cnt = 0; cnt < count; cnt++) {
        			if (value != -1) {
	        				cards.add(new Card(CardName.valueOf(cardName), value, CreatureType.toCreatureType(creatureType)));
        			} else {
	        			for(int i = range_min; i <= range_max; i++) {
	        				cards.add(new Card(CardName.valueOf(cardName), i, CreatureType.toCreatureType(creatureType)));
	        			}
        			}
        		}
        	}
        	eventType = xrp.next();
        }
        return cards;
	}

	@Override
	protected Deck doInBackground(InputStream... params) {
		Deck deck = new Deck();
		try {
			deck.setCards(loadDeck(params[0]));
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return deck;
	}
}
