package spaceInvaders;

import java.awt.Graphics;
import java.io.Serializable;

public abstract class Character implements Serializable{
	private static final long serialVersionUID = -5465203115521018915L;
	protected int x,y,width,height;
	protected int speed;
	protected boolean shot;
	
	//Default constructor
	public Character() {
		
	}

	//constructor
	public Character(int x, int y, int width, int height, int speed) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}	
	
	public abstract void paint(Graphics g);
	
	public boolean characters_collide(Character f, Character s) {
		if (f.x <= s.x + s.width &&
				f.x + f.width >= s.x &&
				f.y <= s.y + s.height &&
				f.height + f.y >= s.y)  {
			// collision
			return true;
		}
		else {
			// no collision
			return false;
		}
	}
}
