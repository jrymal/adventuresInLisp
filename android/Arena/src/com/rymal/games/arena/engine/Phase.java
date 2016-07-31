package com.rymal.games.arena.engine;

public enum Phase {

	PLACE_BET(0),
	PLAY_CARD(1),
	DISCARD_DRAW(2),
	ELIMINATION(3);
	
	private int _order;
	
	private Phase(int order) {
		_order = order;
	}
	
	public static Phase getFirstPhase() {
		return PLACE_BET;
	}
	
	public static Phase getNextPhase(Phase currentPhase) {
		int nextPhase = ((currentPhase._order + 1) % 4);
		for (Phase phase : Phase.values()) {
			if (phase._order == nextPhase){
				return phase;
			}
		}
		throw new IllegalArgumentException("Failed to find next phase: "+currentPhase.name());
	}
	
}
