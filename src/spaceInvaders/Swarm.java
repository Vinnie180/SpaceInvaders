package spaceInvaders;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class Swarm implements Serializable{
	private static final long serialVersionUID = 7914463767905185390L;
	private GamePanel gamePanel;
	private ArrayList<Alien> aliens;
	private Ticker shooterTimer;
	private Alien leftest = new Alien();
	private Alien rightest = new Alien();
	private int size;
	private int speed = 1;
	private int point = 10;
	
	public Swarm() {
		
	}
	
	public ArrayList<Alien> getAliensAL() {
		return getAliens();
	}
	
	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	private void generateNewAliens(int row, int column, int speed) {
		setAliens(new ArrayList<>());
		for (int j = 0; j < row; j++) {
    		for(int i = 0; i < column; i++) {
    			this.getAliens().add(new Alien((getGamePanel().getBOARD_WIDTH()/3)+i*(getGamePanel().getGAP() + getGamePanel().getWIDTH()),
    					(getGamePanel().getGAP() + getGamePanel().getWIDTH())*j+(getGamePanel().getGAP() + getGamePanel().getWIDTH()),
    					getGamePanel().getWIDTH(), getGamePanel().getWIDTH(), speed, point ));
    			this.size++;
    		}
    	}
	}
	
	public Swarm(GamePanel gamePanel, int row, int column) {
		super();
		this.gamePanel = gamePanel;
		setShooterTimer(new Ticker(this));
		
		generateNewAliens(row, column, this.speed);
	}
	
	public Alien getRandomAlien() {
		Alien ret = null;
		int shooterIndex = getRandomNumber(0, this.size);
		ret = this.getAliens().get(shooterIndex);
		
		while(!ret.isAlive() && ret.shot) {
			shooterIndex = getRandomNumber(0, this.size);
			ret = this.getAliens().get(shooterIndex);
		}
		return ret;
	}
	
	public void shoot() {
		Alien shooter = getRandomAlien();
		shooter.shoot();
	}
	
	public void removeTheDead() {
		rightest();
		leftest();
		for (var iterator = getAliens().iterator(); iterator.hasNext();) {
		    Alien alien = iterator.next();
		    if(!alien.isAlive()) {
		        iterator.remove();
		        getGamePanel().decPeriod(10);
		        this.size--;
		    }
		}
	}
	
	public void paint(Graphics g) {
		for (var iterator = getAliens().iterator(); iterator.hasNext();) {
		    Alien alien = iterator.next();
		    if(alien.isAlive()) {
		        alien.paint(g);
		    }
		}
	}
	
	public void move() {
		if(leftest.x < 0) {
			for(Alien alien: getAliens()) {
				alien.setGoingLeft(false);
				alien.y += 3;
			}
			getGamePanel().decPeriod(50);
		}
		else if(rightest.x > getGamePanel().getBOARD_WIDTH()-2*(getGamePanel().getWIDTH())) {
			for(Alien alien: getAliens()) {
				alien.setGoingLeft(true);
				alien.y += 3;
			}
			getGamePanel().decPeriod(50);

		}
	}
	
	public void paintPlasmas(Graphics g) {
		for(Alien alien: getAliens()) {
			if (alien.shot)
				alien.getPlasma().paint(g);
		}
	}
	
	public boolean allDead() {
		if (this.getAliens().size() <= 0 ) {
			return true;
		}
		return false;
	}
	
	public boolean collide() {
		for(Alien alien : getAliens()) {
			if (alien.collideWithPlayer(gamePanel.getPlayer())){
				return true;
			}
		}
		return false;
	}
	
	public void update() {
		move();
		for(Alien alien: getAliens()) {
			alien.update(getGamePanel());
		}
		removeTheDead();
		if(collide()) {
			gamePanel.setIngame(false);
		}
		
		if (allDead()) {
			point += 10;
			if(gamePanel.getPeriod() - gamePanel.getDecPeriod() > gamePanel.getPlayablePeriod())
				gamePanel.setMinPeriod(gamePanel.getMinPeriod() - gamePanel.getDecPeriod());
			generateNewAliens(gamePanel.getRow(), gamePanel.getColumn(), this.speed);
		}
	}

	public void rightest() {
		int temp = 0;
		for (Alien alien: getAliens()) {
			if (alien.x > temp)
				temp = alien.x;
		}
		for (Alien alien: getAliens()) {
			if (alien.x == temp)
				this.rightest = alien;
		}
	}
	
	public void leftest() {
		int temp = 0;
		for (Alien alien: getAliens()) {
			if (alien.x < temp)
				temp = alien.x;
		}
		for (Alien alien: getAliens()) {
			if (alien.x == temp)
				this.leftest = alien;
		}
	}
	
	public int size() {
		return size;
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public Ticker getShooterTimer() {
		return shooterTimer;
	}

	public void setShooterTimer(Ticker shooterTimer) {
		this.shooterTimer = shooterTimer;
	}

	public ArrayList<Alien> getAliens() {
		return aliens;
	}

	public void setAliens(ArrayList<Alien> aliens) {
		this.aliens = aliens;
	}
}
