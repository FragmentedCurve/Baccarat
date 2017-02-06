package baccarat;

public class BaccaratPlayer extends Player{
	private int bet;
	private Card thirdCard;
	
	public BaccaratPlayer(String name) {
		super(name, 50);
		bet = 1;
		thirdCard = null;
	}
	
	public void setHand(Card[] hand) {
		// TODO: Throw exception if Card.length > 2
		if(hand.length != 2) {
			System.out.println("ERROR: Hand dealt to " + getName() + "does not have 2 card");
			return;
		}
		super.setHand(hand);
	}
	
	public void placeBet(int bet) {
		if(bet > 5 || bet < 1) {
			// TODO: Throw exception
			System.out.println("ERROR: Bet is not between 1 and 5");
			return;
		}
		this.bet = bet;
	}
	
	public void reset() {
		super.reset();
		bet = 0;
		thirdCard = null;
	}
	
	public int getHandValue() {
		Card[] h = getHand();
		int sum = 0;
		
		for(Card c : h)
			if(c.isFaceCard() == false && c.getValue() != Card.TEN)
				sum += c.getValue();
		return sum % 10;
	}
	
	public int getBet() {
		return bet;
	}
	
	public void giveCard(Card c) {
		if(thirdCard == null) {
			thirdCard = c;
			super.giveCard(c);
		}
	}
	
	public Card getThirdCard() {
		return thirdCard;
	}
	
	public void clearHand() {
		super.clearHand();
		thirdCard = null;
	}
}
