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
		tile = new Tile[60];
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
			
			// TREES
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/LightningTrunk_01.png"));
			tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/OakTreeBottom_01.png"));
			tile[3].collision = true;	
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/OakTreeTop_01.png"));
			
			//WATER
			
			tile[51] = new Tile();
			tile[51].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/WaterShallowBL_01.png"));
			tile[51].collision = true;
			
			tile[52] = new Tile();
			tile[52].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/WaterShallowB_01.png"));
			tile[52].collision = true;
			
			tile[53] = new Tile();
			tile[53].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/WaterShallowBR_01.png"));
			tile[53].collision = true;
			
			tile[54] = new Tile();
			tile[54].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/WaterShallowCL_01.png"));
			tile[54].collision = true;
			
			tile[55] = new Tile();
			tile[55].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/WaterShallow_01.png"));
			tile[55].collision = true;
			
			tile[56] = new Tile();
			tile[56].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/WaterShallowCR_01.png"));
			tile[56].collision = true;
			
			tile[57] = new Tile();
			tile[57].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/WaterShallowTL_01.png"));
			tile[57].collision = true;			
			
			tile[58] = new Tile();
			tile[58].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/WaterShallowT_01.png"));
			tile[58].collision = true;
			
			tile[59] = new Tile();
			tile[59].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/WaterShallowTR_01.png"));
			tile[59].collision = true;
			
			
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
