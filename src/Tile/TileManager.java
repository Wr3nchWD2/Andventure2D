package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import Main.GameWindow;

public class TileManager {
	
	// INSTANTIATIONS
	GameWindow gw;
	public Tile[] tile;
	public int mapTileNum[] [];
	
	
	public TileManager(GameWindow gw) {
		this.gw = gw;
		
		// NUMBER OF TILE ASSETS
		tile = new Tile[8];
		mapTileNum = new int[gw.maxWorldCol] [gw.maxWorldRow];
		
		// GETTING TILES AND TEXT MAPS
		GetTileImage();
		LoadMap("/TextMaps/WorldMap_01.txt");
	}
	
	public void GetTileImage() {
		
		try {
			
			// GRASS
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Grass_01.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/Grass_02.png"));	
			
			// BRICK
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/StoneBrick_01.png"));
			tile[2].collision = true;
			
			// TREES
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/LightningTrunk_01.png"));
			tile[3].collision = true;
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/OakTreeBottom_01.png"));
			tile[4].collision = true;	
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/OakTreeTop_01.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	// DECRYPTING THE TEXTMAP
	public void LoadMap(String filePath) {
		try {			
			InputStream is = getClass().getResourceAsStream(filePath);			
			BufferedReader br = new BufferedReader(new InputStreamReader (is));
			
			int col = 0;
			int row = 0;
			
			while(col < gw.maxWorldCol && row < gw.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gw.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gw.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	// DRAWING TILES ON SCREEN
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;		
		int worldRow = 0; 
		
		while(worldCol < gw.maxWorldCol && worldRow < gw.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gw.tileSize;
			int worldY = worldRow * gw.tileSize;
			int screenX = worldX - gw.player.worldX + gw.player.screenX;
			int screenY = worldY - gw.player.worldY + gw.player.screenY;
			
			g2.drawImage(tile[tileNum].image, screenX, screenY, gw.tileSize, gw.tileSize, null);
			worldCol++;
			
			if (worldCol == gw.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
		
	}
}
