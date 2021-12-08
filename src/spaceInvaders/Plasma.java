package spaceInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public final class Plasma extends Character implements Serializable{
	private static final long serialVersionUID = -1094439992224815169L;
	private Character source;
	private boolean player;
	
	//Default constructor
	public Plasma() {
		super();
	}

	//Constructor
	public Plasma(int x, int y, int width, int height, int speed, boolean p, Character ch) {
		super(x, y, width, height, speed);
		player = p;
		source = ch;
	
	}
	
	//Drawing the plasma
	public void paint(Graphics g) {
		if(player) {
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
		}
		else {
			g.setColor(Color.MAGENTA);
			g.fillRect(x, y, width, height);
		}
		
	}
	
	public void move() {
		if(player) {
			y -= speed;
		}
		else {
			y += speed;
		}
	}
	
	public void update(GamePanel gp) {
		move();
		if(player) {
			//killing aliens
			for(Alien alien: gp.getAliens().getAliensAL()) {
				if (alien.isAlive()) {
					if (characters_collide(this,alien)){
						alien.setAlive(false);
						source.shot = false;
						gp.getPlayer().killed(alien);
					}
				}
			}
			//plasmas collide
			for (Alien alien: gp.getAliens().getAliensAL()) {
				if(alien.shot && characters_collide(this, alien.getPlasma())) {
					alien.shot = false;
					source.shot = false;
				}
			}
			//exits map
			if (y < 0) {
				source.shot = false;
			}
		}
		else {
			if(!gp.getPlayer().isInvurable() && characters_collide(this, gp.getPlayer())) {
				gp.getPlayer().setInvurable(true);
				source.shot = false;
				gp.getPlayer().setLives(gp.getPlayer().getLives() - 1);
				
				if (gp.getPlayer().isInvurable()) {
					
					Timer timer = new Timer();
					TimerTask task = new TimerTask() {
						
						@Override
						public void run() {
							gp.getPlayer().setInvurable(false);
						}
					};
					
					timer.schedule(task, 500);
					
				}
			}
			if (y > gp.getBOARD_HEIGHT()) {
				source.shot = false;
			}
		}
	}

	public Character getSource() {
		return source;
	}

	public void setSource(Character source) {
		this.source = source;
	}

	public boolean isPlayer() {
		return player;
	}

	public void setPlayer(boolean player) {
		this.player = player;
	}
}
