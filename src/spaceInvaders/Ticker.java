package spaceInvaders;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Ticker extends Timer implements Serializable{
	private static final long serialVersionUID = -8385307612408052486L;
	private GamePanel gamePanel;
	private Swarm swarm;
	
	public Ticker(Swarm swarm) {
		this.swarm = swarm;
		this.gamePanel = swarm.getGamePanel();
	}

	public void runTaskPeriord() {
	    TimerTask task = new TimerTask() {      
	    	@Override
	    	public void run() {
	    		swarm.shoot();
	    		this.cancel();
	    		runTaskPeriord();
	    	}
	    };

	    this.schedule(task, gamePanel.getPeriod(), gamePanel.getPeriod());
	}
	
}
