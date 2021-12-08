package spaceInvaders;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SwarmTest {
	
	Swarm swarm;
	GamePanel gp;
	Window win;
	
	@Before
	public void setUp(){
		win = new Window();
		gp = new GamePanel(win);
		swarm = new Swarm(gp, 3, 8);
	}

	@Test
	public void testSwarm() {
		assertEquals(gp, swarm.getGamePanel());
		assertEquals(3*8, swarm.size());
	}
	
	@Test
	public void testGetRandomNumber() {
		int res = 0;
		for (int i = 0; i < 1000; i++) {
			res = swarm.getRandomNumber(0, 100);
			assertTrue(res >= 0 && res <= 100);
		}
	}
	
	@Test
	public void testGetRandomAlien() {
		Alien tmp = new Alien();
		for (int i = 0; i < 1000; i++) {
			tmp = swarm.getRandomAlien();
			assertTrue(tmp.isAlive());
			assertFalse(tmp.shot);			
		}
	}
	
	@Test
	public void testShoot() {
		swarm = new Swarm(gp, 1, 1);
		swarm.shoot();
		assertTrue(swarm.getAliens().get(0).shot);
	}
	
	@Test
	public void testRemoveTheDead() {
		swarm = new Swarm(gp, 10, 10);
		int oldsize = swarm.size();
		swarm.getRandomAlien().setAlive(false);
		swarm.getRandomAlien().setAlive(false);
		swarm.removeTheDead();
		
		assertEquals(oldsize, swarm.size() + 2);
	}
	
}
