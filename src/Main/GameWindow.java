package Main;

import javax.swing.JPanel;

import Entity.Player;
import Tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameWindow extends JPanel implements Runnable {
	
	// SCREEN SETTINGS
	final int mainTileSize = 16; // initially 16 x 16 tile 
	final int scale = 4; // scaled 64 x 64 tiles

	public final int tileSize = mainTileSize * scale; // 64 x 64 tiles
	public final int maxTileOnScreenY = 12;
	public final int maxTileOnScreenX = 16;
	public final int screenWidth = tileSize * maxTileOnScreenX;
	public final int screenHeight = tileSize * maxTileOnScreenY;
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// FPS
	final int fps = 30;

	TileManager tileM = new TileManager(this);
	KeyHandler kH = new KeyHandler();
	Thread gameThread;
	public CollisionCode cCode = new CollisionCode(this);
	public Player player = new Player(this, kH);
	
	// INITIALIZING THE WINDOW
	public GameWindow() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(kH);
		this.setFocusable(true);
	}
	
	// STARTING THE GAME THREAD/ STARTING THE GAME
	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		// CREATING THE FPS
		float drawInt = 1000000000 / fps;
		double nextDrawTime = System.nanoTime() + drawInt;

		while (gameThread != null) {

			update();

			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

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

		Graphics2D g2 = (Graphics2D) g;

		tileM.draw(g2);
		player.draw(g2);

		g2.dispose();
	}

}
