package spaceInvaders;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PausePanel extends JPanel{
	private static final long serialVersionUID = -3532639114173187201L;
	private Window win;
	private JButton continueButton;
	private JButton	saveButton;
	private JButton exitButton;
	
	public PausePanel(Window win) {
		this.win = win;
		
		continueButton = new JButton("Continue");
		saveButton = new JButton("Save");
		exitButton = new JButton("Exit");
		
		this.add(continueButton);
		this.add(saveButton);
		this.add(exitButton);
		
		//adding actionListeners to buttons
		exitButton.addActionListener(e -> {
			this.win.dispose();
			System.exit(0);
		});
		
		continueButton.addActionListener(e -> {
			this.win.getGamePanel().setPaused(false);
			this.win.getCardLayout().show(win.getCards(), "Game");
		});
		
		saveButton.addActionListener(e -> {
			System.out.println("Saving...");
			try {
				this.win.save();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("Saved!");
		});
	}
}
