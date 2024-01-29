package Main;

import Entity.Entity;

public class CollisionCode {
	
	GameWindow gW;
	
	// COLLISION SYSTEM
	public	CollisionCode(GameWindow gW) {
		this.gW = gW;
	}
	
	public void checkTile(Entity entity) {
	
		int entityLWX = entity.worldX + entity.collisionArea.x;
		int entityRWX =  entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
		int entityTWY = entity.worldY + entity.collisionArea.y;
		int entityBWY =  entity.worldY + entity.collisionArea.y + entity.collisionArea.height;
		
		int entityLC = entityLWX/gW.tileSize;
		int entityRC = entityRWX/gW.tileSize;
		int entityTR = entityTWY/gW.tileSize;
		int entityBR = entityBWY/gW.tileSize;
		
		int tilenum1, tilenum2;
		
		switch(entity.direction) {
		case "up":	
			entityTR = (entityTWY - entity.speed) / gW.tileSize;
			tilenum1 = gW.tileM.mapTileNum[entityLC][entityTR];
			tilenum2 = gW.tileM.mapTileNum[entityRC][entityTR];
			if(gW.tileM.tile[tilenum1].collision == true || gW.tileM.tile[tilenum2].collision == true)
			{
				entity.collisionTriggered = true;
			}
			break;			
		case "down":
			entityBR = (entityBWY + entity.speed) / gW.tileSize;
			tilenum1 = gW.tileM.mapTileNum[entityLC][entityBR];
			tilenum2 = gW.tileM.mapTileNum[entityRC][entityBR];
			if(gW.tileM.tile[tilenum1].collision == true || gW.tileM.tile[tilenum2].collision == true)
			{
				entity.collisionTriggered = true;
			}
			break;		
		case "left":
			entityLC = (entityLWX - entity.speed) / gW.tileSize;
			tilenum1 = gW.tileM.mapTileNum[entityLC][entityBR];
			tilenum2 = gW.tileM.mapTileNum[entityLC][entityTR];
			if(gW.tileM.tile[tilenum1].collision == true || gW.tileM.tile[tilenum2].collision == true)
			{
				entity.collisionTriggered = true;
			}
			break;		
		case "right":
			entityRC = (entityRWX + entity.speed) / gW.tileSize;
			tilenum1 = gW.tileM.mapTileNum[entityRC][entityBR];
			tilenum2 = gW.tileM.mapTileNum[entityRC][entityTR];
			if(gW.tileM.tile[tilenum1].collision == true || gW.tileM.tile[tilenum2].collision == true)
			{
				entity.collisionTriggered = true;
			}
			break;		
		
		}
		
	}
}
