package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    //Atributos
    private MouseInputs mouseInputs;//Para que el Mouse y el movimiento vengan del mismo lugar
    private float xDelta = 100;
    private float xDir = 1f;
    private float yDir = 1f;
    private float yDelta = 100;
    private int frames = 0;
    private long lastCheck = 0;
    private Color color = new Color(150, 20, 90);
    private Random random;

    //Constructor
    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);//instancia de la atributo
        //Lógica que interpreta los inputs del teclado
        addKeyListener(new KeyBoardInputs(this));//Se coloca el este panel en KeyBoardInput
        //Lógica que interpreta los inputs del mouse
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }

    //Método para dibujar un componente
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//Implementar el método del JPanel

        updateRectangle();

        g.setColor(color);
        g.fillRect((int) xDelta, (int) yDelta, 200, 50);


    }

    public void updateRectangle() {
        xDelta += xDir;
        if (xDelta > 400 || xDelta < 0) {
            xDir *= -1;
            color = getRndColor();
        }

        yDelta += yDir;
        if (yDelta > 400 || yDelta < 0) {
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int b = random.nextInt(255);
        int g = random.nextInt(255);

        return new Color(r, g, b);
    }

}
