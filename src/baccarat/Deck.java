package baccarat;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	public static final int MAX_CARDS = 52;
	
	private ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>();
		reset();
	}
	
	public void reset() {
		ArrayList<Card> new_deck = new ArrayList<Card>();
		for(int suit : Card.SUITS)
			for(int value : Card.VALUES)
				new_deck.add(new Card(value, suit));
		deck = new_deck;
	}
	
	public void shuffle() {
		ArrayList<Card> new_deck = new ArrayList<Card>();
		int index;
		Random rand = new Random();
		for(int count = deck.size(); count > 0; count--) {
			index = rand.nextInt(count);
			new_deck.add(deck.get(index));
			deck.remove(index);
		}
		deck = new_deck;
	}
	
	public Card deal() {
		Card c = deck.get(0);
		deck.remove(0);
		return c;
	}
	
	public Card[] deal(int n) {
		Card[] hand = new Card[n];
		for(int i = 0; i < n; i++)
			hand[i] = deal();
		return hand;
	}
	
	public void putUnderDeck(Card c) {
		if(deck.size() == MAX_CARDS)
			return;
		deck.add(c);
	}
	
	public void putUnderDeck(Card[] hand) {
		for(Card c : hand)
			putUnderDeck(c);
	}
	
	public void putAboveDeck(Card c) {
		if(deck.size() == MAX_CARDS)
			return;
		deck.add(0, c);
	}
	
	public void putAboveDeck(Card[] hand) {
		for(Card c : hand)
			putAboveDeck(c);
	}
	
	public Card cardAt(int index) {
		return deck.get(index);
	}
	
	public Card peek() {
		return cardAt(0);
	}
	
	public Card[] peek(int n) {
		Card[] cards = new Card[n];
		for(int i = 0; i < n; i++)
			cards[i] = deck.get(i);
		return cards;
	}
	
	public int size() {
		return deck.size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
}
