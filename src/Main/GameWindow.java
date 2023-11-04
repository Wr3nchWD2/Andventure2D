package Main;

import javax.swing.JPanel;

import Entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameWindow extends JPanel implements Runnable{
	
	// SCREEN SETTINGS
	final int mainTileSize = 32; // 32 x 32 tile
	final int scale = 2; 
	
	public final int tileSize = mainTileSize * scale; // 64 x 64 tiles
	final int maxTileOnScreenY = 16;
	final int maxTileOnScreenX = 20;
	final int screenWidth = tileSize * maxTileOnScreenX; // 1280p
	final int screenHeight = tileSize * maxTileOnScreenY; // 1024p
	
	final int fps = 60;
	
	
	KeyHandler kH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, kH);
	
	
	// player default pos.
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 8;
	
	public GameWindow() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(kH);
		this.setFocusable(true);
	}

	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		float drawInt = 1000000000/fps;
		double nextDrawTime = System.nanoTime() + drawInt;
		
		while (gameThread != null) {
			
			update();		
			
			repaint();
						
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if (remainingTime < 0) {
					remainingTime = 0;					
				}
				
				Thread.sleep((long)remainingTime);	
				
				nextDrawTime += drawInt;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	public void update() {
		
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		player.draw(g2d);
		
		g2d.dispose();
	}
	
}



