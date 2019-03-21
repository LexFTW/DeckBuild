package deck_build.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import deck_build.models.Card;
import deck_build.models.Deck;
import net.miginfocom.swing.MigLayout;

public class BuildDecks implements ActionListener{

	public static void main(String[] args) {
		new BuildDecks();
	}

	private JFrame frame;
	private JPanel panel;
	private JScrollPane sp_Cards, sp_Decks;
	private JButton btn_LoadCards, btn_RandomDeck, btn_SaveDeck, btn_Left, btn_Right, btn_checkDeckName;
	private JTextField tf_checkDeckName;
	private JList<Card> cards;
	
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
		
		this.sp_Cards = new JScrollPane();
		this.sp_Cards.setViewportView(new JList<String>());
		this.sp_Decks = new JScrollPane();
		this.sp_Decks.setViewportView(new JList<String>());
		
		this.panel.add(this.btn_LoadCards);
		this.panel.add(this.btn_RandomDeck, "wrap");
		this.panel.add(this.sp_Cards, "pushy, growy");
		this.panel.add(this.sp_Decks, "pushy, growy, wrap");
		this.panel.add(this.tf_checkDeckName, "split 2, align left");
		this.panel.add(this.btn_checkDeckName);
		this.panel.add(this.btn_SaveDeck, "align right");
		
		this.frame.setContentPane(this.panel);
		this.frame.setTitle("Constructor de Barajas 100% REAL NO FAKE 1 LINK MEGA");
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
				// ...
			}else if(btn == this.btn_LoadCards) {
				// ...
			}else if(btn == this.btn_RandomDeck) {
				// ...
			}
		}
	}

}
