package spaceInvaders;

import java.awt.CardLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame{
	private static final long serialVersionUID = -2827894707885970168L;
	//panels
	private GamePanel gamePanel;
	private MenuPanel menuPanel;
	private PausePanel pausePanel;
	
	//layout
	private JPanel cards;
	private CardLayout cardLayout;
	
	//Constructor
	public Window() {
		super("SpaceInvaders");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		menuPanel = new MenuPanel(this);
		pausePanel = new PausePanel(this);
			
		cards = new JPanel(new CardLayout());
		cards.add(menuPanel,"Menu");
		cards.add(pausePanel,"Pause");
		
		cardLayout = (CardLayout) cards.getLayout();
		
		this.add(cards);
		this.setVisible(true);
		
	}
	
	//saving the gamepanel to save.txt
	public void save() throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream("save.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this.gamePanel);
		oos.close();
		fos.close();
	}

	//loading the gamepanel from save.txt
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream is=new ObjectInputStream(new FileInputStream("save.txt"));
		gamePanel = null;
		gamePanel = (GamePanel)is.readObject();
		is.close();
		gamePanel.addListeners();
		cards.add(gamePanel,"Game");
		gamePanel.setPaused(false);
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public MenuPanel getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(MenuPanel menuPanel) {
		this.menuPanel = menuPanel;
	}

	public PausePanel getPausePanel() {
		return pausePanel;
	}

	public void setPausePanel(PausePanel pausePanel) {
		this.pausePanel = pausePanel;
	}

	public JPanel getCards() {
		return cards;
	}

	public void setCards(JPanel cards) {
		this.cards = cards;
	}
}
