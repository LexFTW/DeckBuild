package deck_build.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import deck_build.implementations.exists.IExistsCard;
import deck_build.implementations.mongo.IMongoDeck;
import deck_build.models.Card;
import deck_build.models.Deck;
import net.miginfocom.swing.MigLayout;
import net.sf.saxon.functions.Exists;

public class BuildDecks implements ActionListener{

	public static void main(String[] args) {
		new BuildDecks();
	}

	private JFrame frame;
	private JPanel panel;
	private JScrollPane sp_Cards, sp_Decks;
	private JButton btn_LoadCards, btn_RandomDeck, btn_SaveDeck, btn_Left, btn_Right, btn_checkDeckName;
	private JTextField tf_checkDeckName;
	private DefaultListModel<Card> dlmcards, dlmdeck;
	private JList<Card> cards, deck;
	private IExistsCard exists;
	private ArrayList<Card> cardsExist;
	
	private BuildDecks() {
		this.panel = new JPanel(new MigLayout());
		this.frame = new JFrame();
		
		this.btn_LoadCards = new JButton("Cargar Cartas");
		this.btn_RandomDeck = new JButton("Generar Baraja Automaticamente");
		this.btn_SaveDeck = new JButton("Guardar Baraja");
		this.btn_Left = new JButton("<-");
		this.btn_Right = new JButton("->");
		this.btn_checkDeckName = new JButton("Comprobar Nombre");
		
		this.tf_checkDeckName = new JTextField(30);
		
		this.dlmcards = new DefaultListModel<Card>();
		this.dlmdeck = new DefaultListModel<Card>();
		
		this.cards = new JList(this.dlmcards);
		this.cards.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.cards.setSelectedIndex(0);
		
		this.deck = new JList(this.dlmdeck);
		this.deck.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.deck.setSelectedIndex(0);
		
		this.sp_Cards = new JScrollPane(this.cards);
		this.sp_Decks = new JScrollPane(this.deck);
		
		this.btn_checkDeckName.addActionListener(this);
		this.btn_LoadCards.addActionListener(this);
		this.btn_RandomDeck.addActionListener(this);
		
		this.panel.add(this.btn_LoadCards);
		this.panel.add(this.btn_RandomDeck, "wrap");
		this.panel.add(this.sp_Cards, "pushy, growy");
		this.panel.add(this.sp_Decks, "pushy, growy, wrap");
		this.panel.add(this.tf_checkDeckName, "split 2, align left");
		this.panel.add(this.btn_checkDeckName);
		this.panel.add(this.btn_SaveDeck, "align right");
		
		this.frame.setContentPane(this.panel);
		this.frame.setTitle("Pokemon TCG - Build Deck");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.setSize(600, 480);
		this.frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if(btn == this.btn_checkDeckName) {
				if(!this.tf_checkDeckName.getText().equals("")) {
					
				}else {
					System.err.println("[ERROR] - Introduce un nombre a la baraja.");
				}
			}else if(btn == this.btn_LoadCards) {
				this.exists = new IExistsCard();
				this.cardsExist = exists.getCards();
				for (Card card : cardsExist) {
					this.dlmcards.addElement(card);
				}
			}else if(btn == this.btn_RandomDeck) {
				IMongoDeck mongo = new IMongoDeck();
				Deck d = mongo.randomDeck(this.cardsExist);
				for (Card card : d.getCards()) {
					this.dlmdeck.addElement(card);
				}
			}
		}
	}

}
