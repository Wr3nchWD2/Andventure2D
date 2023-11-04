package Entity;

import java.awt.image.BufferedImage;

public class Entity {
	
	public int x, y;
	public int speed;
	
	public BufferedImage idle_01, idle_02, idle_03, idle_04, runningDown_01, runningDown_02, runningDown_03, runningDown_04, runningDown_05, runningDown_06;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
}
