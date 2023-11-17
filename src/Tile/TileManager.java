package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GameWindow;

public class TileManager {
	
	GameWindow gw;
	Tile[] tile;
	int mapTileNum[] [];
	
	
	public TileManager(GameWindow gW) {
		this.gw = gW;
		
		tile = new Tile[4];
		mapTileNum = new int[gw.maxTileOnScreenX] [gw.maxTileOnScreenY];
		GetTileImage();
		LoadMap("/TextMaps/TempMap.txt");
	}
	
	public void GetTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Grass_01.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Grass_02.png"));	
				
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/MesaBrick_01.png"));
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void LoadMap(String filePath) {
		try {			
			InputStream is = getClass().getResourceAsStream(filePath);			
			BufferedReader br = new BufferedReader(new InputStreamReader (is));
			
			int col = 0;
			int row = 0;
			
			while(col < gw.maxTileOnScreenX && row < gw.maxTileOnScreenY) {
				String line = br.readLine();
				
				while(col < gw.maxTileOnScreenX) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gw.maxTileOnScreenX) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2) {
		
		int col = 0;		
		int row = 0; 
		int x = 0;
		int y = 0;
		
		while(col < gw.maxTileOnScreenX && row < gw.maxTileOnScreenY) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gw.tileSize, gw.tileSize, null);
			col++;
			x += gw.tileSize;
			
			if (col == gw.maxTileOnScreenX) {
				col = 0;
				x = 0;
				row++;
				y += gw.tileSize;
			}
		}
		
	}
}
