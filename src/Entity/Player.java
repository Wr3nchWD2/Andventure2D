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
	
	public final int screenX;
	public final int screenY;
	
	public Player(GameWindow gW, KeyHandler kH) {
		this.gW = gW;
		this.kH = kH;
		
		screenX = gW.screenWidth/2;
		screenY = gW.screenHeight/2;
		
		setDefaultValue();
		getPlayerImage();
	}
	
	public void setDefaultValue() {
		
		worldX = gW.tileSize * 25 - (gW.tileSize/2);
		worldY = gW.tileSize * 25 - (gW.tileSize/2);
		speed = 12;
		direction = "down";
			
	}	
	public void getPlayerImage() {
		try {
			
			idle_01 = ImageIO.read(getClass().getResourceAsStream("/player/Idle_01.png"));
			idle_02 = ImageIO.read(getClass().getResourceAsStream("/player/Idle_02.png"));
			runningDown_01 = ImageIO.read(getClass().getResourceAsStream("/player/RunningDown_01.png"));
			runningDown_02 = ImageIO.read(getClass().getResourceAsStream("/player/RunningDown_02.png"));
			runningUp_01 = ImageIO.read(getClass().getResourceAsStream("/player/RunningUp_01.png"));
			runningUp_02 = ImageIO.read(getClass().getResourceAsStream("/player/RunningUp_02.png"));
			runningRight_01 = ImageIO.read(getClass().getResourceAsStream("/player/RunningRight_01.png"));
			runningRight_02 = ImageIO.read(getClass().getResourceAsStream("/player/RunningRight_02.png"));
			runningLeft_01 = ImageIO.read(getClass().getResourceAsStream("/player/RunningLeft_01.png"));
			runningLeft_02 = ImageIO.read(getClass().getResourceAsStream("/player/RunningLeft_02.png"));

			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {

		if (kH.upPressed == true) {
			direction = "up";
			worldY -= speed;
		}
		else if (kH.downPressed == true) {
			direction = "down";
			worldY += speed;
		}
		else if (kH.leftPressed == true) {
			direction = "left";
			worldX -= speed;
		}
		else if (kH.rightPressed == true) {
			direction = "right";
			worldX += speed;
		}
		else {
			direction = "idle";
		}
		
		spriteCounter++;
		if(spriteCounter > 4) {
			if (spriteNum == 1) {
				spriteNum = 2;
			}
			else if (spriteNum == 2) {
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
				image = runningUp_01;
			}			
			if(spriteNum == 2) {
				image = runningUp_02;
			}		

			break;
		case "down":
			if(spriteNum == 1) {
				image = runningDown_01;
			}			
			if(spriteNum == 2) {
				image = runningDown_02;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = runningLeft_01;
			}			
			if(spriteNum == 2) {
				image = runningLeft_02;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = runningRight_01;
			}			
			if(spriteNum == 2) {
				image = runningRight_02;
			}
			break;
		case "idle":
			if(spriteNum == 1) {
				image = idle_01;
			}			
			if(spriteNum == 2) {
				image = idle_02;
			}
			break;
			
		}
		
		g2d.drawImage(image, screenX, screenY, gW.tileSize, gW.tileSize, null);
	}
			
}














