package com.rymal.games.arena.model.deck.card;

public class Card {
	
	public static final int NO_VALUE = -1;
	public static final CreatureType NO_TYPE = null;
	
	private final CardType _cardType;
	private final CreatureType _creatureType;
	private final CardName _cardName;
	private final int _value;
	
	public Card(CardName cardName, int value, CreatureType creatureType) {
		_cardName = cardName;
		_value = value;
		_creatureType = creatureType;
		_cardType = cardName.getCardType();
	}
	
	public CardType getCardType(){
		return _cardType;
	}
	
	public CreatureType getCreatureType() {
		return _creatureType;
	}
	
	public CardName getCardName() {
		return _cardName;
	}
	
	public int getValue() {
		return _value;
	}
}
