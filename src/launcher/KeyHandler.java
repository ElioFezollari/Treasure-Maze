package launcher;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed,downPressed,leftPressed,rightPressed;
    private GamePanel gp;
    public KeyHandler(GamePanel gamePanel) {
        this.gp = gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
        if (e.isControlDown() && code == KeyEvent.VK_R) {
            gp.restartGame();
        }
        if (e.isControlDown() && code == KeyEvent.VK_C) {
            gp.saveGame();
            gp.message = "Saved Game";
            gp.messageTime = System.currentTimeMillis() + 2000; 
            gp.messageColor = Color.decode("#00d2ff");
        }
        if (e.isControlDown() && code == KeyEvent.VK_L) {
            gp.loadGame();
            gp.message = "Loaded Game";
            gp.messageTime = System.currentTimeMillis() + 2000; 
            gp.messageColor = Color.decode("#00d2ff");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){

            upPressed = false;
        }
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
}
