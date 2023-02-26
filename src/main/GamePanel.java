package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {
    //Atributos
    private MouseInputs mouseInputs;//Para que el Mouse y el movimiento vengan del mismo lugar
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage img;
    private BufferedImage subImagen;

    //Constructor
    public GamePanel() {
        mouseInputs = new MouseInputs(this);//instancia de la atributo
        importImg();
        setPanelSize();//Instancia el tamaño de la ventana
        //Lógica que interpreta los inputs del teclado
        addKeyListener(new KeyBoardInputs(this));//Se coloca el este panel en KeyBoardInput
        //Lógica que interpreta los inputs del mouse
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        //Importa la imagen que tenemos en el proyecto en la carpeta res
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            img = ImageIO.read(is);//Leer y cargar la imagen en el biffer de imagen
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
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
        //El observer es un objeto que controla el cambio de estado de una imagen, y no se va a utilizar en este tutorial
        /*
         * drawImage(imagen, posicion_x, posicion_y, observable) Solo la imagen, con tamaño original
         * drawImage(imagen, posicion_x, posicion_y, ancho, alto, observable)Imagen con otro tamaño
         * drawImage(imagen.getSubimage(posicion_x, posicion_y, ancho, alto), posicion_x, posicion_y, ancho, alto, observable) Sección de la imagen con otro tamaño
         * */
        subImagen = img.getSubimage(1 * 64, 8 * 40, 64, 40);
        g.drawImage(subImagen, (int) xDelta, (int) yDelta, 128, 80, null);

    }


}
