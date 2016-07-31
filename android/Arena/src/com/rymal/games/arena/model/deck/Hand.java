package com.rymal.games.arena.model.deck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.rymal.games.arena.model.deck.card.Card;

public class Hand {
	
	private Collection<Card> _cards;
	
	public Hand() {
		_cards = new ArrayList<Card>();
	}
	
	public void addCards(List<Card> list) {
		for (Card c : list){
			_cards.add(c);
		}
	}
	
	public Card removeCard(Card card) {
		_cards.remove(card);
		return card;
	}
	
	public Collection<Card> viewCardSet() {
		return _cards;
	}
}
