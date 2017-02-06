package baccarat;

import java.util.ArrayList;

public class Player {
	private String name;
	private int score;
	
	private ArrayList<Card> hand;

	private int id;
	public static int numberOfPlayers = 0;
	
	public Player(String name) {
		this(name, 0);
	}
	
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
		id = ++numberOfPlayers;
		hand = new ArrayList<Card>();
	}
	
	public void gain(int n) {
		score += n;
	}
	
	public void loss(int n) {
		score -= n;
	}
	
	public void reset() {
		score = 0;
		hand = new ArrayList<Card>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean equals(Player p) {
		if(name.equals(p.getName()) && id == p.getId())
			return true;
		return false;
	}
	
	public void giveCard(Card c) {
		hand.add(c);
	}
	
	public void setHand(Card[] hand) {
		for(Card c : hand)
			this.hand.add(c);
	}
	
	public Card[] getHand() {
		Card[] c = new Card[hand.size()];
		c = hand.toArray(c);
		return c;
	}
	
	public void clearHand() {
		hand = new ArrayList<Card>();
	}
	
	public int handSize() {
		return hand.size();
	}
	
	public boolean has(Card c) {
		for(Card k : getHand())
			if(c.equals(k))
				return true;
		return false;
	}
	
	public boolean hasFaceCard() {
		for(Card c : getHand())
			if(c.isFaceCard())
				return true;
		return false;
	}
	
	public boolean hasValue(int value) {
		for(Card c : getHand())
			if(value == c.getValue())
				return true;
		return false;
	}
}
