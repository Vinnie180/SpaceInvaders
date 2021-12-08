package spaceInvaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	Player p;
	Player old;
	Window win;
	GamePanel gp;

	@Before
	public void setUp() {
		win = new Window();
		gp = new GamePanel(win);
		p = new Player(50,50,20,20,4,gp);
		old = new Player(50,50,20,20,4,gp);
		p.update();
		
	}
	
	@Test
	public void testPlayer() {
		assertEquals(50, p.x);
		assertEquals(50, p.y);
		assertEquals(20, p.width);
		assertEquals(20, p.height);		
		assertEquals(4, p.speed);
		assertFalse(p.isMoveLeft());
		assertFalse(p.isMoveRight());
		assertFalse(p.isInvurable());
		assertFalse(p.shot);
	}
	
	@Test
	public void testMoveLeft() {
		p.setMoveLeft(true);
		p.update();
		assertNotEquals(p.x, old.x);
		assertTrue(p.x + p.speed == old.x);
	}
	
	@Test
	public void testMoveRight() {
		p.setMoveRight(true);
		p.update();
		assertNotEquals(p.x, old.x);
		assertTrue(p.x - p.speed == old.x);
	}
	
	@Test
	public void testShoot() {
		assertFalse(p.shot);
		p.shoot();
		assertTrue(p.shot);
		//x+width/2, y, 2, 20, plasmaSpeed, true, this
		assertEquals(p.getPlasma().x, p.x + p.width /2);
		assertEquals(p.getPlasma().y, p.y);
	}
	
	@Test
	public void testUpdate() {
		p.shoot();
		old.shoot();

		if (p.isMoveLeft() || p.isMoveRight()) {
			assertNotEquals(p.x, old.x);
		}
	}
}
