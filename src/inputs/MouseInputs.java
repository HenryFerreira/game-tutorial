package inputs;

import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clic del mause");
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {//Arrastrar
        //Obtiene la posición X e I del Mouse y se la pasa al Panel
        gamePanel.setRectPos(e.getX(), e.getY());

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //Obtiene la posición X e I del Mouse y se la pasa al Panel
    }
}
