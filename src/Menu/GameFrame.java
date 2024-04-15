package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class GameFrame extends JFrame {
    private PopMenu popMenu;

    public GameFrame() {
        popMenu = new PopMenu();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                    SwingUtilities.convertPointFromScreen(mouseLocation, GameFrame.this);
                    showPopupMenu((int) mouseLocation.getX(), (int) mouseLocation.getY());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                maybeShowPopup(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                maybeShowPopup(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            private void maybeShowPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopupMenu(e.getX(), e.getY());
                }
            }
        });
    }

    private void showPopupMenu(int x, int y) {
        popMenu.show(this, x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame());
    }
}

