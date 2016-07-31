package com.rymal.games.arena.model.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rymal.games.arena.model.deck.card.Card;
import com.rymal.games.arena.model.deck.card.CreatureType;

public class Deck {
	
	private List<Card> _cards;
	
	public Deck() {
		_cards = new ArrayList<Card>();
	}
	
	public Deck(Deck deck, List<CreatureType> creatureSet) {
		this();
		
		for(Card card : deck._cards) {
			if (card.getCreatureType() == CreatureType.NO_TYPE 
					|| creatureSet.contains(card.getCreatureType()))
			_cards.add(card);
		}
	}

	public void setCards(List<Card> cards) {
		_cards.addAll(cards);
	}
	
	public void shuffle() {
		Collections.shuffle(_cards);
	}
	
	public List<Card> drawCards(int cardCount) {
		List<Card> cards = new ArrayList<Card>();
		
		for (int i = cardCount; i > 0; i-- ) {
			Card nextCard = _cards.iterator().next();
			_cards.remove(nextCard);
			cards .add(nextCard);
		}
		return cards;
	}
	
	public boolean areMoreCards() {
		return _cards.iterator().hasNext();
	}

}
