package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utilz.Constants.Directions.*;

public class KeyBoardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO document why this method is empty
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {//Obtener el valor del Botón
            case KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D -> gamePanel.setMoving(false);
            default -> {
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Dependiendo del Botón presionado hace algo
        switch (e.getKeyCode()) {//Obtener el valor del Botón
            case KeyEvent.VK_W -> gamePanel.setDirection(UP);
            case KeyEvent.VK_A -> gamePanel.setDirection(LEFT);
            case KeyEvent.VK_S -> gamePanel.setDirection(DOWN);
            case KeyEvent.VK_D -> gamePanel.setDirection(RIGHT);
            default -> {
            }
        }
    }
}
