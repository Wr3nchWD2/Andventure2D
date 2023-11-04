package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GameWindow;
import Main.KeyHandler;

public class Player extends Entity{
	
	GameWindow gW;
	KeyHandler kH;
	
	public Player(GameWindow gW, KeyHandler kH) {
		this.gW = gW;
		this.kH = kH;
		
		setDefaultValue();
		getPlayerImage();
	}
	
	public void setDefaultValue() {
		
		x = 100;
		y = 100;
		speed = 8;
		direction = "down";
			
	}	
	public void getPlayerImage() {
		try {
			
			idle_01 = ImageIO.read(getClass().getResourceAsStream("/player/Idle_01.png"));
			idle_02 = ImageIO.read(getClass().getResourceAsStream("/player/Idle_02.png"));
			idle_03 = ImageIO.read(getClass().getResourceAsStream("/player/Idle_03.png"));
			idle_04 = ImageIO.read(getClass().getResourceAsStream("/player/Idle_04.png"));
			runningDown_01 = ImageIO.read(getClass().getResourceAsStream("/player/RunningDown_01.png"));
			runningDown_02 = ImageIO.read(getClass().getResourceAsStream("/player/RunningDown_02.png"));
			runningDown_03 = ImageIO.read(getClass().getResourceAsStream("/player/RunningDown_03.png"));
			runningDown_04 = ImageIO.read(getClass().getResourceAsStream("/player/RunningDown_04.png"));
			runningDown_05 = ImageIO.read(getClass().getResourceAsStream("/player/RunningDown_05.png"));
			runningDown_06 = ImageIO.read(getClass().getResourceAsStream("/player/RunningDown_06.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {

		if (kH.upPressed == true) {
			direction = "up";
			y -= speed;
		}
		else if (kH.downPressed == true) {
			direction = "down";
			y += speed;
		}
		else if (kH.leftPressed == true) {
			direction = "left";
			x -= speed;
		}
		else if (kH.rightPressed == true) {
			direction = "right";
			x += speed;
		}
		else {
			direction = "idle";
		}
		
		spriteCounter++;
		if(spriteCounter > 7) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
				spriteNum = 3;
			}
			else if (spriteNum == 3) {
				spriteNum = 4;
			}
			else if (spriteNum == 4) {
				spriteNum = 5;
			}
			else if (spriteNum == 5) {
				spriteNum = 6;
			}
			else if (spriteNum == 6) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2d) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = runningDown_01;
			}			
			if(spriteNum == 2) {
				image = runningDown_02;
			}
			if(spriteNum == 3) {
				image = runningDown_03;
			}			
			if(spriteNum == 4) {
				image = runningDown_04;
			}
			if(spriteNum == 5) {
				image = runningDown_05;
			}			
			if(spriteNum == 6) {
				image = runningDown_06;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = runningDown_01;
			}			
			if(spriteNum == 2) {
				image = runningDown_02;
			}
			if(spriteNum == 3) {
				image = runningDown_03;
			}			
			if(spriteNum == 4) {
				image = runningDown_04;
			}
			if(spriteNum == 5) {
				image = runningDown_05;
			}			
			if(spriteNum == 6) {
				image = runningDown_06;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = runningDown_01;
			}			
			if(spriteNum == 2) {
				image = runningDown_02;
			}
			if(spriteNum == 3) {
				image = runningDown_03;
			}			
			if(spriteNum == 4) {
				image = runningDown_04;
			}
			if(spriteNum == 5) {
				image = runningDown_05;
			}			
			if(spriteNum == 6) {
				image = runningDown_06;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = runningDown_01;
			}			
			if(spriteNum == 2) {
				image = runningDown_02;
			}
			if(spriteNum == 3) {
				image = runningDown_03;
			}			
			if(spriteNum == 4) {
				image = runningDown_04;
			}
			if(spriteNum == 5) {
				image = runningDown_05;
			}			
			if(spriteNum == 6) {
				image = runningDown_06;
			}
			break;
		case "idle":
			if(spriteNum == 1) {
				image = idle_01;
			}			
			if(spriteNum == 2) {
				image = idle_02;
			}
			if(spriteNum == 3) {
				image = idle_03;
			}			
			if(spriteNum == 4) {
				image = idle_02;
			}
			if(spriteNum == 5) {
				image = idle_04;
			}			
			if(spriteNum == 6) {
				image = idle_01;
			}
			break;
			
		}
		
		g2d.drawImage(image, x, y, gW.tileSize, gW.tileSize, null);
	}
			
}














