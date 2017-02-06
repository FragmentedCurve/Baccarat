package baccarat;

public class Card {
	private int value;
	private int suit;
	
	public static final int SPADES   = 0;
	public static final int CLUBS    = 1;
	public static final int HEARTS   = 2;
	public static final int DIAMONDS = 3;

	public static final int ACE   = 1;
	public static final int TWO   = 2;
	public static final int THREE = 3;
	public static final int FOUR  = 4;
	public static final int FIVE  = 5;
	public static final int SIX   = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	public static final int NINE  = 9;
	public static final int TEN   = 10;
	public static final int JACK  = 11;
	public static final int QUEEN = 13;
	public static final int KING  = 14;
	
	public static final int[] SUITS = {SPADES, CLUBS, HEARTS, DIAMONDS};
	public static final int[] VALUES = {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};
	
	public Card(int value, int suit) {
		// TODO: Check values for range errors
		this.value = value;
		this.suit = suit;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getSuit() {
		return suit;
	}
	
	public String valueToString() {
		return valueToString(value);
	}
	
	public String suitToString() {
		return suitToString(suit);
	}
	
	public static String valueToString(int value) {
		switch(value) {
		case ACE:
			return "ace";
		case TWO:
		case THREE:
		case FOUR:
		case FIVE:
		case SIX:
		case SEVEN:
		case EIGHT:
		case NINE:
		case TEN:  // Fall-through 
			return String.valueOf(value);
		case JACK:
			return "jack";
		case QUEEN:
			return "queen";
		case KING:
			return "king";
		}
		return null;
	}

	
	public static String suitToString(int suit) {
		switch(suit) {
		case SPADES:
			return "spades";
		case CLUBS:
			return "clubs";
		case HEARTS:
			return "hearts";
		case DIAMONDS:
			return "diamonds";
		}
		return null;
	}
	
	public boolean equals(Card c) {
		if(c.getValue() == value && c.getSuit() == suit)
			return true;
		return false;
	}
	
	public String toString() {
		return String.format("%s of %s", valueToString(), suitToString());
	}
	
	public boolean isFaceCard() {
		if(value == JACK || value == QUEEN || value == KING)
			return true;
		return false;
	}
	
	public static boolean isSuit(int suit) {
		for(int n : SUITS)
			if(n == suit)
				return true;
		return false;
	}
	
	public static boolean isValue(int value) {
		for(int n : VALUES)
			if(n == value)
				return true;
		return false;
	}
}
