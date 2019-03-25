package pages;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import DAOimplement.CardsDAOimplExistDB;
import DAOimplement.DeckDAOimplMongoDB;
import iDAO.DeckDAO;
import models.Card;
import models.Deck;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class DeckPage extends JFrame {
	DefaultListModel<Card> model = new DefaultListModel<Card>();
	DefaultListModel<Card> modelAdded = new DefaultListModel<Card>();
	private JTextField textField;
	JList list;
	JList list_1;

	private Deck deck;
	
	JLabel valueText;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public DeckPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnLoadCards = new JButton("Load Cards");
		btnLoadCards.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadCards();
			}
		});
		
		JButton btnRandomDeck = new JButton("Random Deck");
		btnRandomDeck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				generateRandomDeck();
			}
		});
		
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnSaveDeck = new JButton("Save Deck");
		btnSaveDeck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertDeck();
			}
		});
		
		JButton btnLoadDeck = new JButton("Load Deck");
		btnLoadDeck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getDeck();
			}
		});
		
		list = new JList(model);
		
		list_1 = new JList(modelAdded);
		
		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addCardsToDeck();
			}
		});
		
		JButton button_1 = new JButton("<");
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeCardsFromDeck();
			}
		});
		
		JLabel lblDeckValue = new JLabel("Deck Value:");
		
		valueText = new JLabel("0");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(button)
								.addComponent(button_1))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE)
							.addGap(47))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLoadCards)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRandomDeck)
							.addGap(62)
							.addComponent(lblDeckValue)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(valueText)
							.addGap(90)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnSaveDeck)
							.addComponent(btnLoadDeck))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(74))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnLoadCards)
							.addComponent(btnRandomDeck))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDeckValue)
								.addComponent(valueText))
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSaveDeck)
							.addGap(11)
							.addComponent(btnLoadDeck))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(64)
							.addComponent(button)
							.addGap(18)
							.addComponent(button_1))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
								.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(102, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
	
	private void loadCards() {
		model.clear();
		modelAdded.clear();
		ArrayList<Card> cards = new CardsDAOimplExistDB().getCards();
		for (Card card : cards) {
			model.addElement(card);
		}
	}
	
	private void addCardsToDeck() {
		try {
			if (deck == null) deck = new Deck();
			ArrayList<Card> cards = (ArrayList<Card>) list.getSelectedValuesList();
			for (Card card : cards) {
				if (deck.getDeckValue() + card.getValue() <= 20) {
					model.removeElement(card);
					modelAdded.addElement(card);
					deck.setDeckValue(deck.getDeckValue() + card.getValue());
					valueText.setText(String.valueOf(deck.getDeckValue()));
					deck.getDeck().add(card);
				}
			}
		} catch (Exception e) {}
	}
	
	private void removeCardsFromDeck() {
		try {
			ArrayList<Card> cards = (ArrayList<Card>) list_1.getSelectedValuesList();
			for (Card card : cards) {
				modelAdded.removeElement(card);
				model.addElement(card);
				deck.setDeckValue(deck.getDeckValue() - card.getValue());
				valueText.setText(String.valueOf(deck.getDeckValue()));
				deck.getDeck().remove(card);
			}
		} catch (Exception e) {}
	}
	
	private void generateRandomDeck() {
		if(deck == null) deck = new Deck();
		try {
			while (deck.getDeckValue() < 20) {
				Card card = model.get((int) (Math.random() * model.getSize()));
				if (card.getValue() + deck.getDeckValue() <= 20) {
					modelAdded.addElement(card);
					model.removeElement(card);
					deck.setDeckValue(deck.getDeckValue() + card.getValue());
					deck.getDeck().add(card);
				} else {
					break;
				}
			}
		} catch (Exception e) {}

		
		valueText.setText(String.valueOf(deck.getDeckValue()));
	}
	
	private void insertDeck() {
		try {
			if (deck != null && !textField.getText().isEmpty()) {
				deck.setDeckName(textField.getText());
				DeckDAO deckManager = new DeckDAOimplMongoDB();
				try {
					deckManager.getDeckByName(textField.getText());
					deckManager.updateDeck(deck);
				} catch (Exception e) {					
					deckManager.insertDeck(deck);
				}
				loadCards();
				cleanDeck();
			}
		} catch (Exception e) {}
	}
	
	private void getDeck() {
		try {
			if (!textField.getText().isEmpty()) {
				DeckDAO deckManager = new DeckDAOimplMongoDB();
				deck = deckManager.getDeckByName(textField.getText());
				loadCards();
				for (Card card : deck.getDeck()) {
					model.removeElement(card);
					modelAdded.addElement(card);
				}
				valueText.setText(String.valueOf(deck.getDeckValue()));
			}
		} catch (Exception e) {JOptionPane.showMessageDialog(null, "No existe una baraja con ese nombre");}
	}
	
	private void cleanDeck() {
		deck = new Deck();
		valueText.setText("0");
		textField.setText("");
	}
}
