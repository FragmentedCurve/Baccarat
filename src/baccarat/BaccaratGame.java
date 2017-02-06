package baccarat;

public class BaccaratGame extends Game {
	private int round;
	private BaccaratPlayer dealer;
	private BaccaratPlayer player;
	
	public BaccaratGame() {
		super();
		dealer = new BaccaratPlayer("Dealer");
		player = new BaccaratPlayer("Player");
		addPlayer(dealer);
		addPlayer(player);
		round = 0;
	}
	
	public BaccaratPlayer getDealer() {
		return dealer;
	}
	
	public BaccaratPlayer getPlayer() {
		return player;
	}
	
	public void playRound() {
		shuffle();
		dealAll(2);
		
		if(wonBy8Or9() == false) {
			if(doesPlayerStand())
				doesDealerStand();
			else
				doTableau();
		}
		calculateWins();
	}
	
	public void endRound() {
		putUnderDeck(dealer.getHand());
		putUnderDeck(player.getHand());
		dealer.clearHand();
		player.clearHand();
		round++;
	}
	
	private boolean wonBy8Or9() {
		int d = dealer.getHandValue();
		int p = player.getHandValue();
		
		if(d == 8 || d == 9 || p == 8 || p == 9)
			return true;
		return false;
	}
	
	private boolean doesPlayerStand() {
		return doesStand(player);
	}
	
	private boolean doesDealerStand() {
		return doesStand(dealer);
	}
	
	private boolean doesStand(BaccaratPlayer p) {
		int v = p.getHandValue();
		if(v >= 0 && v <= 5) {
			p.giveCard(deal());
			return false;
		}
		return true;
	}
	
	public BaccaratPlayer getWinner() {
		int p = player.getHandValue();
		int d = dealer.getHandValue();
		
		if(p > d)
			return player;
		else if(p < d)
			return dealer;
		return null;
	}
	
	private void doTableau() {
		//int p = player.getHandValue();
		int d = dealer.getHandValue();
		
		// TODO: Error check for null
		Card c = player.getThirdCard();
		int p = c.getValue();
		
		if((p == 2 || p == 3) && (d >= 0 && d <= 4))
			dealer.giveCard(deal());
		else if((p == 4 || p == 5) && (d >= 0 && d <= 5))
			dealer.giveCard(deal());
		else if((p == 6 || p == 7) && (d >= 0 && d <= 6))
			dealer.giveCard(deal());
		else if(p == 8 && (d >= 0 && d <= 2))
			dealer.giveCard(deal());
		else if((p == Card.ACE || p == Card.NINE || p == Card.TEN || c.isFaceCard()) && (d >= 0 && d <= 3))
			dealer.giveCard(deal());
	}
	
	private void calculateWins() {
		BaccaratPlayer w = getWinner();
		if(w == null) // Round is tied
			return;
		BaccaratPlayer l = w.equals(player) ? dealer : player;
		w.gain(player.getBet());
		l.loss(player.getBet());
	}
	
	public int getRound() {
		return round;
	}
}
