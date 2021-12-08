package spaceInvaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AlienTest {
	
	Alien a;
	Alien old;
	GamePanel gp;
	Window win;
	
	@Before
	public void setUp() {
		win = new Window();
		gp = new GamePanel(win);
		a = new Alien(40, 40, 20, 20, 1, 10);
		old = new Alien(40, 40, 20, 20, 1, 10);
	}

	@Test
	public void testAlien() {
		assertEquals(40, a.x);
		assertEquals(40, a.y);
		assertEquals(20, a.width);
		assertEquals(20, a.height);
		assertEquals(1, a.speed);
		assertEquals(10, a.getPoints());
		assertTrue(a.isAlive());
	}
	
	@Test
	public void testMove() {
		a.update(gp);
		if (!a.isGoingLeft()) {
			assertEquals(a.x, old.x + a.speed);
		}
		a.setGoingLeft(true);
		a.update(gp);
		a.update(gp);
		if (a.isGoingLeft()) {
			assertEquals(a.x, old.x - a.speed);
		}
	}
	
	@Test
	public void testShoot() {
		a.shoot();
		assertTrue(a.shot);		
		assertEquals(a.getPlasma().x, a.x + a.width / 2);
		assertEquals(a.getPlasma().y, a.y);
	}
	
	@Test
	public void testUpdate() {
		a.update(gp);
		assertEquals(a.x-1, old.x);
	}
	
	@Test
	public void testCollideWithPlayer() {
		Player p = new Player(40, 30, 20, 20, 1, gp);
		assertTrue(a.collideWithPlayer(p));
	}

}
