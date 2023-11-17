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
	final int mainTileSize = 16; // 16 x 16 tile
	final int scale = 4; // 96 x 96 tiles

	public final int tileSize = mainTileSize * scale; // 48 x 48 tiles
	public final int maxTileOnScreenY = 12;
	public final int maxTileOnScreenX = 16;
	public final int screenWidth = tileSize * maxTileOnScreenX;
	public final int screenHeight = tileSize * maxTileOnScreenY;

	final int fps = 30;

	TileManager tileM = new TileManager(this);
	KeyHandler kH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, kH);

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
