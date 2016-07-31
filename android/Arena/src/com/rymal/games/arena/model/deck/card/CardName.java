package com.rymal.games.arena.model.deck.card;

public enum CardName {
	COLOSSUS(CardType.COMBAT),
	SERAPHIM(CardType.COMBAT),
	TITAN(CardType.COMBAT),
	TROLL(CardType.COMBAT),
	GORGON(CardType.COMBAT),
	ETTIN(CardType.COMBAT),
	MAGUS(CardType.COMBAT),
	UNICORN(CardType.COMBAT),
	DAIMON(CardType.COMBAT),
	AMAZON(CardType.COMBAT),
	CYCLOPS(CardType.COMBAT),
	WYRM(CardType.COMBAT),
	PREFECT(CardType.REFEREE),
	MAGISTER(CardType.REFEREE),
	BEHEMOTH(CardType.SPECTATOR),
	JINN(CardType.SPECTATOR),
	KRAKEN(CardType.SPECTATOR),
	CHERUBIM(CardType.SPECTATOR),
	MINOTAUR(CardType.SPECTATOR),
	GOBLINS(CardType.SPECTATOR),
	URSA(CardType.SPECTATOR),
	GRYPHON(CardType.SPECTATOR),
	GARGOYLE(CardType.SPECTATOR),
	CENTAUR(CardType.SPECTATOR),
	SERPENT(CardType.SPECTATOR);

	private CardType _type;
	
	private CardName(CardType type) {
		_type = type;
	}
	
	public CardType getCardType() {
		return _type;
	}
}
