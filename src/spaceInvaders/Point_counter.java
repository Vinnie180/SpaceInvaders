package spaceInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Point_counter extends JComponent{
	private static final long serialVersionUID = -6330374679004405165L;
	private GamePanel gamePanel;
	
	public Point_counter() {

	}
	
	public Point_counter(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.drawString("Points: " + gamePanel.getPlayer().getPoints(), gamePanel.getWIDTH(), gamePanel.getWIDTH());
    }
}
