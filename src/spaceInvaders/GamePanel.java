package spaceInvaders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable,Serializable{
	//Basic attributes
	private static final long serialVersionUID = 4043815719790904563L;
	private Window win;
	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;
	private int GAP = 5;
	private int WIDTH = 20;
	private int row = 3;
	private int column = 8;
	private boolean ingame = false;
	private boolean paused = false;
	
	//Fine tuning
	private long period = 1500;
	private long minPeriod = 700;
	private long playablePeriod = 20;
	private long decPeriod = 200;
		
	//Complex attributes
	private transient Thread animator;	
	private Player player;
	private Swarm aliens;
	private Point_counter pointCounter = new Point_counter(this);
	private LivesCounter livesCounter = new LivesCounter(this);
	
	//Constructor
	public GamePanel(Window win) {
		super();
		this.win = win;
		this.setBOARD_WIDTH(win.getWidth());
		this.setBOARD_HEIGHT(win.getHeight());
		this.setIngame(true);
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent cEvt) {
				Component src = (Component) cEvt.getSource();
				src.requestFocusInWindow();
			}
		});
		
		this.requestFocusInWindow(true);
		
		setPlayer(new Player(getBOARD_WIDTH()/2, getBOARD_HEIGHT()-(3*getWIDTH()), getWIDTH(), getWIDTH(), 2, this));
		aliens = new Swarm(this, getRow(), getColumn());
	
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        if (animator == null || !isIngame()) {
            animator = new Thread(this);
            animator.start();
        }
        setDoubleBuffered(true);
	}
	
	//Button processing
	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
		     int key = e.getKeyCode();
		     switch(key) {
		     case KeyEvent.VK_LEFT: getPlayer().setMoveLeft(false); break;
		     case KeyEvent.VK_RIGHT: getPlayer().setMoveRight(false); break;
		     }
		}
		
		public void keyPressed(KeyEvent e) {
		    int key = e.getKeyCode();
	        switch(key) {
	        case KeyEvent.VK_LEFT: getPlayer().setMoveLeft(true); break;
	        case KeyEvent.VK_RIGHT: getPlayer().setMoveRight(true); break;
	        case KeyEvent.VK_SPACE: getPlayer().shoot(); break;
	        case KeyEvent.VK_ESCAPE: setPaused(true); win.getCardLayout().show(win.getCards(), "Pause"); break;
	        case KeyEvent.VK_K: setPaused(false); break;
	        }
		}
	}
	
	//Drawing
    public void paint(Graphics g){
		super.paint(g);
		this.setBackground(Color.BLACK);
		
		//Player
		getPlayer().paint(g);
		
		//Aliens
		aliens.paint(g);
		
		//Player Bullet
		if(getPlayer().shot) {
			getPlayer().getPlasma().paint(g);
		}
		
		//Alien shots
		aliens.paintPlasmas(g);
		
		//Point counter
		pointCounter.paintComponent(g);
		
		//Lives
		livesCounter.paintComponent(g);
		
		g.dispose();
    }
    
    //Runnable run
	@Override
	public void run() {		
		aliens.getShooterTimer().runTaskPeriord();
		
		//long beforeTime, timeDiff, sleep;
		//beforeTime = System.currentTimeMillis();
		int animationDelay = 5;
		long time = System.currentTimeMillis();
		while (isIngame() && getPlayer().getLives() > 0 ) {
			
			System.out.flush();
			
			if(!isPaused()) {
				
				
				repaint();
				getPlayer().update();
				aliens.update();				
				
				try {
					time += animationDelay;
					Thread.sleep(Math.max(0,time - System.currentTimeMillis()));
				}catch (InterruptedException e) {
					System.out.println(e);
				}
			}
		}
		System.out.println("Vesztettél :(");
	}	
	
	public void addListeners() {
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent cEvt) {
				Component src = (Component) cEvt.getSource();
				src.requestFocusInWindow();
			}
		});
		this.requestFocusInWindow(true);
		addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        if (animator == null || !isIngame()) {
            animator = new Thread(this);
            animator.start();
        }
        setDoubleBuffered(true);
		
	}
		
	public Swarm getAliens() {
		return aliens;
	}
	
	public long getPeriod() {
		return period;
	}

	public void decPeriod(int dec) {
		if(period-dec < getMinPeriod() || period-dec <= 0) {
			
		}
		else {
			period -= dec;
		}
	}

	public int getBOARD_WIDTH() {
		return BOARD_WIDTH;
	}

	public void setBOARD_WIDTH(int bOARD_WIDTH) {
		BOARD_WIDTH = bOARD_WIDTH;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public void setWIDTH(int wIDTH) {
		WIDTH = wIDTH;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getBOARD_HEIGHT() {
		return BOARD_HEIGHT;
	}

	public void setBOARD_HEIGHT(int bOARD_HEIGHT) {
		BOARD_HEIGHT = bOARD_HEIGHT;
	}

	public int getGAP() {
		return GAP;
	}

	public void setGAP(int gAP) {
		GAP = gAP;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isIngame() {
		return ingame;
	}

	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public long getDecPeriod() {
		return decPeriod;
	}

	public void setDecPeriod(long decPeriod) {
		this.decPeriod = decPeriod;
	}

	public long getMinPeriod() {
		return minPeriod;
	}

	public void setMinPeriod(long minPeriod) {
		this.minPeriod = minPeriod;
	}

	public long getPlayablePeriod() {
		return playablePeriod;
	}

	public void setPlayablePeriod(long playablePeriod) {
		this.playablePeriod = playablePeriod;
	}
}
