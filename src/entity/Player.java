package entity;
import launcher.GamePanel;
import launcher.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int points;
    public int treasures;

    public Player(GamePanel gp,KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=16;
        solidArea.height=16;
        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;
        points = 0;
        treasures = 7;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 25 - (gp.tileSize /2);
        worldY = gp.tileSize * 15 - (gp.tileSize /2);
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("../Assets/Player/Back_Walk.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../Assets/Player/Back_Walk2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../Assets/Player/Walk1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../Assets/Player/Walk2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../Assets/Player/Right_Stay.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../Assets/Player/Right_Run.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../Assets/Player/Side_Stay.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../Assets/Player/Side_Walk.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true){
            if (keyH.upPressed == true){
                direction = "up";


            } else if (keyH.downPressed == true) {
                direction = "down";

            }
            else if (keyH.leftPressed == true) {
                direction = "left";

            }
            else if (keyH.rightPressed) {
                direction = "right";
            }
            collisionOn = false;
            gp.cDetector.checkTile(this);

            if (collisionOn == false){
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 1;
            }
        }

    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
