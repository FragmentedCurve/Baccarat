package baccarat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Baccarat extends JFrame implements ActionListener {
	private BaccaratGame game;
	private final int MAX_ROUNDS = 10;
	
	private int height = 650;
	private int width = 850;
	private Dimension preferredSize = new Dimension(width, height);
	
	private LayoutManager mainLayout = new BorderLayout();
	
	private JPanel betPanel = new JPanel();
	private JLabel betLabel = new JLabel("Bet: ");
	private JButton bet1Button = new JButton("$1");
	private JButton bet2Button = new JButton("$2");
	private JButton bet3Button = new JButton("$3");
	private JButton bet4Button = new JButton("$4");
	private JButton bet5Button = new JButton("$5");

	private JButton startButton = new JButton("Start/Continue");
	
	private JPanel tablePanel = new JPanel();
	private JPanel playerPanel = new JPanel();
	private JLabel playerLabel = new JLabel("Player's Hand");
	private JPanel dealerPanel = new JPanel();
	private JLabel dealerLabel = new JLabel("Dealer's Hand");
	
	private JPanel playerTable = new JPanel();
	private JPanel dealerTable = new JPanel();
	
	private JPanel statusPanel = new JPanel();
	private JLabel playerBank = new JLabel();
	private JLabel dealerBank = new JLabel();
	private JLabel roundLabel = new JLabel();
	private JLabel currentBet = new JLabel();
	private JLabel playerHand = new JLabel();
	private JLabel dealerHand = new JLabel();
	private JLabel roundWinner = new JLabel();
	
	private Baccarat() {
		// Create window
		super("Baccarat");
		setSize(width, height);
		setMinimumSize(preferredSize);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(mainLayout);
		setVisible(true);

		// Setting up button menu
		betPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
		betPanel.add(startButton);
		betPanel.add(betLabel);
		betPanel.add(bet1Button);
		betPanel.add(bet2Button);
		betPanel.add(bet3Button);
		betPanel.add(bet4Button);
		betPanel.add(bet5Button);
	
		// Setting up table Panel
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.add(playerPanel, BorderLayout.WEST);
		tablePanel.add(dealerPanel, BorderLayout.EAST);
		
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		playerTable.setLayout(new BoxLayout(playerTable, BoxLayout.X_AXIS));
		dealerPanel.setLayout(new BoxLayout(dealerPanel, BoxLayout.Y_AXIS));
		dealerTable.setLayout(new BoxLayout(dealerTable, BoxLayout.X_AXIS));
		
		playerPanel.add(playerLabel, BorderLayout.NORTH);
		playerPanel.add(playerTable, BorderLayout.SOUTH);
		dealerPanel.add(dealerLabel, BorderLayout.NORTH);
		dealerPanel.add(dealerTable, BorderLayout.SOUTH);
		
		// Setting up status panel
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		statusPanel.add(new JLabel("Player's bank: "));
		statusPanel.add(playerBank);
		statusPanel.add(new JLabel("  |  Dealer's bank: "));
		statusPanel.add(dealerBank);
		statusPanel.add(new JLabel("  |  Current bet: "));
		statusPanel.add(currentBet);
		statusPanel.add(new JLabel("  |  Round: "));
		statusPanel.add(roundLabel);
		statusPanel.add(new JLabel("  |  Player's Hand Value: "));
		statusPanel.add(playerHand);
		statusPanel.add(new JLabel("  |  Dealer's Hand Value: "));
		statusPanel.add(dealerHand);
		statusPanel.add(new JLabel("  |  ROUND WINNER: "));
		statusPanel.add(roundWinner);
		
		// Adding contents to main window
		add(tablePanel, BorderLayout.NORTH);
		add(statusPanel, BorderLayout.SOUTH);
		add(betPanel, BorderLayout.CENTER);

		// Setting up bet and start buttons
		bet1Button.addActionListener(this);
		bet2Button.addActionListener(this);
		bet3Button.addActionListener(this);
		bet4Button.addActionListener(this);
		bet5Button.addActionListener(this);
		startButton.addActionListener(this);
		
		game = new BaccaratGame();
		playerBank.setText("" + game.getPlayer().getScore());
		dealerBank.setText("" + game.getDealer().getScore());
		currentBet.setText("" + game.getPlayer().getBet());
		roundLabel.setText("" + game.getRound());
		repaint();
	}
	
	private ImageIcon cardToImageIcon(Card c) {
		StringBuilder imagePath = new StringBuilder("images/Playing_card_");

		switch(c.getSuit()) {
		case Card.SPADES:
			imagePath.append("spade_");
			break;
		case Card.CLUBS:
			imagePath.append("club_");
			break;
		case Card.HEARTS:
			imagePath.append("heart_");
			break;
		case Card.DIAMONDS:
			imagePath.append("diamond_");
			break;
		}
		
		switch(c.getValue()) {
		case Card.ACE:
			imagePath.append("A.jpg");
			break;
		case Card.JACK:
			imagePath.append("J.jpg");
			break;
		case Card.QUEEN:
			imagePath.append("Q.jpg");
			break;
		case Card.KING:
			imagePath.append("K.jpg");
			break;
		default:
			imagePath.append(c.getValue() + ".jpg");
			break;
		}
		
		java.net.URL imageURL = getClass().getResource(imagePath.toString());
		return new ImageIcon(imageURL);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source == bet1Button)
			updateBet(1);
		else if(source == bet2Button)
			updateBet(2);
		else if(source == bet3Button)
			updateBet(3);
		else if(source == bet4Button)
			updateBet(4);
		else if(source == bet5Button)
			updateBet(5);
		else if(source == startButton)
			runRound();
	}
	
	private void updateBet(int n) {
		BaccaratPlayer player = game.getPlayer();
		player.placeBet(n);
		currentBet.setText("" + n);
	}
	
	private void runRound() {
		if(game.getRound() == MAX_ROUNDS)
			System.exit(0);
		playerTable.removeAll();
		dealerTable.removeAll();
		game.playRound();
		for(Card c : game.getPlayer().getHand())
			playerTable.add(new JLabel(cardToImageIcon(c)));
		for(Card c : game.getDealer().getHand())
			dealerTable.add(new JLabel(cardToImageIcon(c)));
		playerBank.setText("" + game.getPlayer().getScore());
		dealerBank.setText("" + game.getDealer().getScore());
		playerHand.setText("" + game.getPlayer().getHandValue());
		dealerHand.setText("" + game.getDealer().getHandValue());
		Player p = game.getWinner();
		roundWinner.setText(p == null ? "Tie" : p.getName());
		game.endRound();
		roundLabel.setText("" + game.getRound());
		if(game.getRound() == MAX_ROUNDS)
			endGame();
		repaint();
	}
	
	private void endGame() {
		startButton.setText("Exit");
		JOptionPane.showMessageDialog(this, "The game has ended. You have $" + game.getPlayer().getScore() + " and the dealer has $" + game.getDealer().getScore() + ". Thanks for playing.");
		repaint();
	}
	
	public static void main(String[] args) {
		new Baccarat();
	}
}
