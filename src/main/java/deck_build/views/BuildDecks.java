package deck_build.views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class BuildDecks {

	public static void main(String[] args) {
		new BuildDecks();
	}

	private JFrame frame;
	private JPanel panel;
	private JScrollPane sp_Cards, sp_Decks;
	private JButton btn_LoadCards, btn_RandomDeck, btn_SaveDeck, btn_Left, btn_Right, btn_checkDeckName;
	private JTextField tf_checkDeckName;
	
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
		
		this.panel.add(this.btn_LoadCards, "align center");
		this.panel.add(this.btn_RandomDeck, "align center");
		this.panel.add(this.tf_checkDeckName, "align left, wrap, pushx, growx");
		this.panel.add(this.btn_checkDeckName, "skip 2, wrap, pushx, growx");
		
		this.frame.setContentPane(this.panel);
		this.frame.setTitle("Constructor de Barajas 100% REAL NO FAKE 1 LINK MEGA");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setSize(600, 480);
		this.frame.pack();
		this.frame.setVisible(true);
	}

}
