package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        // TODO document why this method is empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Dependiendo del Botón presionado hace algo
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W://Obtener el valor del Botón
                gamePanel.changeYDelta(-5);
                break;
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(+5);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(+5);
                break;
            default:
                break;
        }
    }
}
