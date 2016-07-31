package com.rymal.games.arena.model.player;

import java.util.ArrayList;
import java.util.List;

import com.rymal.games.arena.model.deck.Hand;

public class Player {
	private Hand _hand;
	private String _name;
	private int _coins;
	private List<Bet> _bets;
	private Bet _secretBet;
	private Color _color;
	
	public Player(String name, PlayerMode mode, Color color) {
		_name = name;
		_hand = new Hand();
		_bets = new ArrayList<Bet>();
		_color = color;
	}
	
	public Hand getHand(){
		return _hand;
	}
	
	public String getName(){
		return _name;
	}
	
	public int getTokensLeft() {
		return _coins;
	}
	
	public boolean placeBet(Bet bet) {
		if (_coins > 0) {
			_bets.add(bet);
			_coins--;
			return true;
		}
		return false;
	}

	public void placeSecretBet(Bet bet) {
		_secretBet = bet;
	}

	public void setTokenCount(int startTokenCount) {
		_coins = startTokenCount;
	}
}
