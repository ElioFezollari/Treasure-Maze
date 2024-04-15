package launcher;

import java.awt.Color;

import entity.Entity;

public class CollisionDetector {
    GamePanel gp;
    public CollisionDetector(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if ((tileNum1 == 2 || tileNum2 == 2) && gp.player.treasures > 3) {
                    gp.message = "Not Enough Chests!";
                    gp.messageTime= System.currentTimeMillis() + 2000;
                    gp.finishReached();
                } else if ((tileNum1 == 2 || tileNum2 == 2) && gp.player.treasures <= 3) {
                    gp.message= "Enough Chests!";
                    gp.messageColor = Color.GREEN;
                    gp.finishReached();
                }
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ) {
                    entity.collisionOn = true;
                    for (int i = 3; i <= 10; i++) {
                        if (i == tileNum1 || i == tileNum2) {
                            if (gp.tileM.tile[i].collision && gp.tileM.tile[i].object) {
                                gp.player.points =gp.player.points+ 10;
                                gp.player.treasures =gp.player.treasures -1;
                                gp.tileM.tile[i] = gp.tileM.tile[0];
                                break;
                            }
                        }
                    }
                    for (int i = 3; i <= 20; i++) {
                        if (i == tileNum1 || i == tileNum2) {
                            if (gp.tileM.tile[i].collision && gp.tileM.tile[i].object) {
                                gp.failGame();
                            }
                        }
                    }
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if ((tileNum1 == 2 || tileNum2 == 2) && gp.player.treasures > 3) {
                    gp.message = "Not Enough Chests!";
                    gp.messageTime = System.currentTimeMillis() + 2000; 
                    gp.finishReached();
                } else if ((tileNum1 == 2 || tileNum2 == 2) && gp.player.treasures <= 3) {
                    gp.message= "Enough Chests!";
                    gp.messageTime = System.currentTimeMillis() + 2000; 
                    gp.finishReached();
                    gp.messageColor = Color.GREEN;
                }
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;

                        for (int i = 3; i <= 10; i++) {
                            if (i == tileNum1 || i == tileNum2) {
                                if (gp.tileM.tile[i].collision && gp.tileM.tile[i].object) {

                                    gp.player.points =gp.player.points+ 10;
                                    gp.player.treasures =gp.player.treasures -1;
                                    gp.tileM.tile[i] = gp.tileM.tile[0];
                                    break;

                            }
                        }
                    }
                    for (int i = 3; i <= 20; i++) {
                        if (i == tileNum1 || i == tileNum2) {
                            if (gp.tileM.tile[i].collision && gp.tileM.tile[i].object) {
                                gp.failGame();
                            }
                        }
                    }
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                if ((tileNum1 == 2 || tileNum2 == 2) && gp.player.treasures > 3) {
                    gp.message = "Not Enough Chests!";
                    gp.messageTime = System.currentTimeMillis() + 2000; 
                    gp.finishReached();
                } else if ((tileNum1 == 2 || tileNum2 == 2) && gp.player.treasures <= 3) {
                    gp.message = "Enough Chests!";
                    gp.messageTime = System.currentTimeMillis() + 2000; 
                    gp.messageColor = Color.GREEN;
                    gp.finishReached();
                }
        
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ) {
                    entity.collisionOn = true;
                    for (int i = 3; i <= 10; i++) {
                        if (i == tileNum1 || i == tileNum2) {
                            if (gp.tileM.tile[i].collision && gp.tileM.tile[i].object) {

                                gp.player.points =gp.player.points+ 10;
                                gp.player.treasures =gp.player.treasures -1;
                                gp.tileM.tile[i] = gp.tileM.tile[0];
                                break;
                            }
                        }
                    }
                    for (int i = 3; i <= 20; i++) {
                        if (i == tileNum1 || i == tileNum2) {
                            if (gp.tileM.tile[i].collision && gp.tileM.tile[i].object) {
                                gp.failGame();
                            }
                        }
                    }
                    
                }

            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                                if ((tileNum1 == 2 || tileNum2 == 2) && gp.player.treasures > 3) {
                    gp.message = "Not Enough Chests!";
                    gp.messageTime = System.currentTimeMillis() + 2000;
                    gp.finishReached();
                } else if ((tileNum1 == 2 || tileNum2 == 2) && gp.player.treasures <= 3) {
                    gp.message = "Enough Chests!";
                    gp.messageTime = System.currentTimeMillis() + 2000; 
                    gp.messageColor = Color.GREEN;
                    gp.finishReached();
                }
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision ) {
                    entity.collisionOn = true;
                    for (int i = 3; i <= 10; i++) {
                        if (i == tileNum1 || i == tileNum2) {
                            if (gp.tileM.tile[i].collision && gp.tileM.tile[i].object) {

                                gp.player.points =gp.player.points+ 10;
                                gp.player.treasures =gp.player.treasures -1;
                                gp.tileM.tile[i] = gp.tileM.tile[0];
                                break;
                            }
                        }
                    }
                    for (int i = 3; i <= 20; i++) {
                        if (i == tileNum1 || i == tileNum2) {
                            if (gp.tileM.tile[i].collision && gp.tileM.tile[i].object) {
                                gp.failGame();
                            }
                        }
                    }
                }
            }
        }
    }}