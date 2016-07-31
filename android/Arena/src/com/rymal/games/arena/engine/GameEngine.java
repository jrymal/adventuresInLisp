package com.rymal.games.arena.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.rymal.games.arena.model.deck.Deck;
import com.rymal.games.arena.model.deck.DeckLoader;
import com.rymal.games.arena.model.deck.card.CreatureType;
import com.rymal.games.arena.model.player.Player;

public class GameEngine {

	private static final int START_TOKEN_COUNT = 5;
	private static final int START_CARD_COUNT = 8;
	
	private List<Player> _players;
	private int _playerIdx;
	private Deck _deck;
	private Phase _phase;
	private int _round;
	
	public GameEngine(){
		_playerIdx = 0;
		_round = 0;
		_players = new ArrayList<Player>();
		_phase = Phase.getFirstPhase();
	}

	public void startGames(List<Player> players) {
		_players.clear();
		_players.addAll(players);
		
		List<CreatureType> creatureSet = CreatureType.createSet(8); 
		try {
			_deck = new Deck(DeckLoader.getDeck(), creatureSet);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
		for (Player player : players) {
			player.setTokenCount(START_TOKEN_COUNT);
			player.getHand().addCards(_deck.drawCards(START_CARD_COUNT));
		}
	}
	
	
	public int getCurrentRound() {
		return _round;
	}

	public Phase getCurrentPhase() {
		return _phase;
	}

	public Player getCurrentPlayer() {
		return _players.get(_playerIdx);
	}

	public Phase executePhase() {
		return _phase;
		/*
		switch (_phase) {
		case PLACE_BET:
			return Phase.
		case PLAY_CARD:
		case DISCARD_DRAW:
		case ELIMINATION:
		}*/
	}
	
	public Phase phaseDone() {
		_phase = Phase.getNextPhase(_phase);
		if (_phase == Phase.getFirstPhase()) {
			if (_playerIdx + 1 >= _players.size()) {
				_round += 1;
			}
			_playerIdx = (_playerIdx + 1) % _players.size();
		}
		return _phase;
	}
}
