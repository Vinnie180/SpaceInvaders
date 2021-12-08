package spaceInvaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlasmaTest {

	Alien a;
	Player pl;
	Plasma plp;
	Plasma alp;
	Plasma old;
	GamePanel gp;
	Window win;

	@Before
	public void setUp() {
		win = new Window();
		gp = new GamePanel(win);
		pl = new Player(10, 20, 30, 30, 1, gp);
		a = new Alien(0, 0, 20, 20, 1, 10);
	}
	
	@Test
	public void testPlasmaPlayer() {
		pl.shoot();
		a.shoot();
		assertEquals(pl.x + pl.width/2, pl.getPlasma().x);
		assertEquals(pl.getPlasma().y, pl.y);
		assertEquals(pl.getPlasma().getSource(), pl);
	}
	
	@Test
	public void testPlasmaAlien() {
		pl.shoot();
		a.shoot();
		assertEquals(a.x + a.width/2, a.getPlasma().x);
		assertEquals(a.getPlasma().y, a.y);
		assertEquals(a.getPlasma().getSource(), a);
		assertFalse(a.getPlasma().isPlayer());
	}
	
	@Test
	public void testMovePlayer(){
		pl.shoot();
		a.shoot();
		int oldx = pl.getPlasma().x;
		int oldy = pl.getPlasma().y;
		pl.getPlasma().move();
		assertEquals(oldx, pl.getPlasma().x);
		assertEquals(oldy - pl.getPlasma().speed, pl.getPlasma().y);
	}
	
	@Test
	public void testMoveAlien(){
		pl.shoot();
		a.shoot();
		int oldx = a.getPlasma().x;
		int oldy = a.getPlasma().y;
		a.getPlasma().move();
		assertEquals(oldx, a.getPlasma().x);
		assertEquals(oldy + a.getPlasma().speed, a.getPlasma().y);
	}
	
	@Test
	public void testUpdate(){
		pl = new Player(50, 50, 20, 20, 3, gp);
		pl.shoot();
		a = new Alien(50, 29, 20, 20, 0, 10);
		assertFalse(pl.characters_collide(pl.getPlasma(), a));
		pl.getPlasma().update(gp);
		assertTrue(pl.characters_collide(pl.getPlasma(), a));	}
	
	@Test
	public void testUpdatePlayerOutOfMap() {
		pl.shoot();
		assertTrue(pl.shot);
		while(pl.getPlasma().y > -10) {
			pl.getPlasma().update(gp);
		}
		assertFalse(pl.shot);
		
	}
}
