package com.rymal.games.arena.model.player;

import com.rymal.games.arena.model.deck.card.CreatureType;

public class Bet {
	private final int _round;
	private final CreatureType _creatureType;
	
	public Bet(CreatureType creatureType, int round) {
		_creatureType = creatureType;
		_round = round;
	}
	
	public int getRound() {
		return _round;
	}
	
	public CreatureType getCreatureType() {
		return _creatureType;
	}
	
}
