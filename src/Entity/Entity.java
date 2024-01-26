package Entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	
	public BufferedImage idle_01, idle_02, idle_03, idle_04, runningDown_01, runningDown_02, runningUp_01, runningUp_02, 
		runningLeft_01, runningLeft_02, runningRight_01, runningRight_02;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
