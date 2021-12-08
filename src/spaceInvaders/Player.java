package spaceInvaders;

import java.awt.Color;
import java.awt.Graphics;

public final class Player extends Character{
	private static final long serialVersionUID = -5925503155652651462L;
	private GamePanel gp;
	private Plasma plasma;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean invurable;
	private int points = 0;
	private int plasmaSpeed = 20;
	private int lives = 10;	
	
	//Default constructor
	public Player() {
		super();
	}
	
	//Constructor
	public Player(int x, int y, int width, int height, int speed, GamePanel gp) {
		super(x, y, width, height, speed);
		this.moveLeft = false;
		this.moveRight = false;
		this.gp = gp;
		this.shot = false;
		this.invurable = false;
	}
	
	public void paint(Graphics g) {
		if(invurable) {
			g.setColor(Color.GREEN);
			g.fillRect(x, y, 20, 20);
		}
		else {
			g.setColor(Color.RED);
			g.fillRect(x, y, 20, 20);
		}
		
	}
	
	public void move(GamePanel gp) {
		if(moveLeft) {
			if(x >= 0+gp.getGAP())
				x -= speed;
		}
		else if(moveRight) {
			if(x <= gp.getBOARD_WIDTH()-2*width)
				x += speed;
		}
	}
	
	public void shoot() {
		if(!this.shot) {
			plasma = new Plasma(x+width/2, y, 2, 20, plasmaSpeed, true, this);
			this.shot = true;
		}
	}
	
	public void update(){
		move(gp);
		if(shot) {
			plasma.update(gp);
		}
	}
	
	public void killed(Alien alien) {
		this.points += alien.getPoints();
	}

	public int getPoints() {
		return points;
	}
	public void incPoints(int amount) {
		this.points += amount;
	}

	public GamePanel getGp() {
		return gp;
	}

	public void setGp(GamePanel gp) {
		this.gp = gp;
	}

	public Plasma getPlasma() {
		return plasma;
	}

	public void setPlasma(Plasma plasma) {
		this.plasma = plasma;
	}

	public boolean isMoveLeft() {
		return moveLeft;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public boolean isMoveRight() {
		return moveRight;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public boolean isInvurable() {
		return invurable;
	}

	public void setInvurable(boolean invurable) {
		this.invurable = invurable;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	public String toString() {
		return "x: " + this.x + "y: " + this.y;
	}
}
