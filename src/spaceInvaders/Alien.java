package spaceInvaders;

import java.awt.Color;
import java.awt.Graphics;

public final class Alien extends Character{
	private static final long serialVersionUID = -7006851620257510650L;
	private boolean goingLeft = false;
	private boolean alive;
	private Plasma plasma;
	private int points = 10;
	
	public Alien() {
		super();
	}

	public Alien(int x, int y, int width, int height, int speed, int point) {
		super(x, y, width, height, speed);
		alive = true;
		points = point;
	}

	public void paint(Graphics g) {
		if(alive) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, 20, 20);
		}		
		if(shot) {
			g.setColor(Color.RED);
			g.fillRect(x, y, 20, 20);
		}
	}
	
	public void move() {
		if (goingLeft) {
			x -= speed;
		}
		else if (!goingLeft){
			x += speed;
		}
	}
	
	public void shoot() {
		if(!this.shot && this.alive) {
			plasma = new Plasma(x+width/2, y, 7, 20, 1, false, this);
			this.shot = true;
		}
	}
	
	
	public void update(GamePanel gp) {
		move();
		if(shot) {
			plasma.move();
			if (plasma.y < 0) {
				shot = false;
			}
			plasma.update(gp);
		}
	}
	
	public boolean collideWithPlayer(Player p) {
		return characters_collide(this, p);
	}

	@Override
	public String toString() {
		return "Alien [alive=" + alive + ", x=" + x + ", y=" + y + "]";
	}

	public boolean isGoingLeft() {
		return goingLeft;
	}

	public void setGoingLeft(boolean goingLeft) {
		this.goingLeft = goingLeft;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Plasma getPlasma() {
		return plasma;
	}

	public void setPlasma(Plasma plasma) {
		this.plasma = plasma;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
