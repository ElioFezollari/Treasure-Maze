package launcher;

import entity.Player;
import tile.SolvableMazeGenerator;
import tile.TileManager;
import Menu.PopMenu;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GamePanel extends JPanel implements Runnable {


    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 30;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;



    public String message = "";
    public long messageTime = 0;
    public Color messageColor = Color.RED;

    int FPS = 60;
    
    public void failGame() {
        player.worldX = tileSize * 25 - (tileSize / 2);
        player.worldY = tileSize * 15 - (tileSize / 2);
        message = "You touched a purple wall!";
        messageTime = System.currentTimeMillis() + 2000; 
    }
    public void restartGame(){
        player.worldX = tileSize * 25 - (tileSize / 2);
        player.worldY = tileSize * 15 - (tileSize / 2);
        tileM.mazeGenerator = new SolvableMazeGenerator(maxWorldCol,maxWorldRow);
        tileM.generateSolvableMaze();
        player.points = 0;
        player.treasures = 7;
        message = "The game restarted";
        messageTime = System.currentTimeMillis() + 2000; 
  
    }
    public void saveGame(){
        writeMapToFile(tileM.mapTileNum);
    }
    public void finishReached(){
        player.worldX = tileSize * 25 - (tileSize / 2);
        player.worldY = tileSize * 15 - (tileSize / 2);
        tileM.mazeGenerator = new SolvableMazeGenerator(maxWorldCol,maxWorldRow);
        tileM.generateSolvableMaze();
        player.treasures = 7;
    }
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;

    public CollisionDetector cDetector = new CollisionDetector(this);
    public Player player = new Player(this,keyH);

    public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth,screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.addKeyListener(keyH);
            this.setFocusable(true);
        
    }


    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta=0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >=1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Points: " + player.points, 10, 20);
        g2.drawString("Treasures left: " + player.treasures, 100, 20);
        g2.drawString("Your Position " + "X: " +player.worldX +" Y: " + player.worldY,10,50);

        if (!message.isEmpty() && System.currentTimeMillis() < messageTime) {
            g2.setColor(messageColor); 
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            FontMetrics fm = g2.getFontMetrics();
            int messageWidth = fm.stringWidth(message);
            int messageHeight = fm.getHeight();
            int x = (screenWidth - messageWidth) / 2;
            int y = (screenHeight - messageHeight) / 2;
            g2.drawString(message, x, y);
        }

        g2.dispose();
    }
    public void writeMapToFile(int[][] mapTileNum) {
        try (
            PrintWriter writer = new PrintWriter("src/Assets/Maps/Map01.txt", "UTF-8");
            PrintWriter saveWriter = new PrintWriter("src/launcher/save.txt", "UTF-8")
        ) {
            for (int x = 0; x < mapTileNum[0].length; x++) {
                for (int y = 0; y < mapTileNum.length; y++) {
                    writer.print(mapTileNum[y][x] + " ");
                }
                writer.println();
            }
            
            saveWriter.println("Player Points: " + player.points);
            saveWriter.println("Player Treasures: " + player.treasures);
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadGame(){
        tileM.loadMap("../Assets/Maps/Map01.txt");
        player.worldX = tileSize * 25 - (tileSize /2);
        player.worldY = tileSize * 15 - (tileSize /2);
            try (BufferedReader saveReader = new BufferedReader(new FileReader("src/launcher/save.txt"))) {
        String line;
        while ((line = saveReader.readLine()) != null) {
            if (line.startsWith("Player Points: ")) {
                player.points = Integer.parseInt(line.substring("Player Points: ".length()));
            } else if (line.startsWith("Player Treasures: ")) {
                player.treasures = Integer.parseInt(line.substring("Player Treasures: ".length()));
            }
        }
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }
}
    }
    
