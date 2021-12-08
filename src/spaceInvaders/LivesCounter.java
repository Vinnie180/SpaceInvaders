package spaceInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class LivesCounter extends JComponent{
	private static final long serialVersionUID = 425981852605849719L;
	private GamePanel gamePanel;
	
	public LivesCounter() {
		
	}
	
	public LivesCounter(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.drawString("Lives: " + gamePanel.getPlayer().getLives(), gamePanel.getBOARD_WIDTH()-(5*gamePanel.getWIDTH()), gamePanel.getWIDTH());
	}

}
