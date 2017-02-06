package baccarat;

import java.util.ArrayList;

public abstract class Game extends Deck {
	protected ArrayList<Player> players;
	
	public Game() {
		super();
		players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player p) {
		if(players.contains(p))
			return;
		players.add(p);
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	public Player getWinningPlayer() {
		Player winner = players.get(0);
		for(int i = 0; i < players.size(); i++)
			if(players.get(i).getScore() > winner.getScore())
				winner = players.get(i);
		return winner;
	}
	
	public void dealPlayer(Player p, int n) {
		p.setHand(deal(n));
	}
	
	public void dealAll(int n) {
		for(int i = 0; i < players.size(); i++)
			dealPlayer(players.get(i), n);
	}
}
