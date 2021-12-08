package spaceInvaders;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{
	private static final long serialVersionUID = 6772466813070329878L;
	private Window win;
	private JButton startButton = new JButton("START");
	private JButton loadButton  = new JButton("LOAD");
	private JButton exitButton = new JButton("EXIT");
	
	public MenuPanel(Window win) {
		super();
		this.win = win;
		
		this.startButton.addActionListener(e -> {
			System.out.println("Starting game...");
			win.setGamePanel(new GamePanel(this.win));
			this.win.getCards().add(win.getGamePanel(),"Game");
			this.win.getCardLayout().show(win.getCards(), "Game");
		});
		
		this.loadButton.addActionListener(e -> {
			System.out.println("Loading...");
			try {
				this.win.load();
				this.win.getCardLayout().show(win.getCards(), "Game");
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("Loaded!");
		});
		
		this.exitButton.addActionListener(e -> {
			System.out.println("Exiting...");
			win.dispose();
			System.out.println("Exited!");
		});
		
		this.add(startButton);
		this.add(loadButton);
		this.add(exitButton);
	}
}
