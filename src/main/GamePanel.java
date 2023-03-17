package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {
    //Atributos
    private MouseInputs mouseInputs;//Para que el Mouse y el movimiento vengan del mismo lugar
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 30;//cuanto más bajo sea el animationSpeed más rápido se mueve la animación
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    //Constructor
    public GamePanel() {
        mouseInputs = new MouseInputs(this);//instancia de la atributo
        importImg();
        //Implementamos un método que carga las animaciones del personaje
        loadAnimations();
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
            img = ImageIO.read(is);//Leer y cargar la imagen en el buffer de imagen
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();//Al terminar de importar la imagen se cierra el flujo para evitar problemas y liberar recursos
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadAnimations() {
        //Se hace una matriz de imágenes para tener todas las animaciones guardadas en un solo lugar
        animations = new BufferedImage[9][6];


        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                //Se obtiene las subImágenes de la primera fila
                //el 64 hace referencia al ancho y el 40 al largo de la imagen
                //específicamente a la primera fila que ocupa 64 píxeles de ancho y 40 píxeles de alto
                //al multiplicar i*64 seleccionamos cada uno de las subImágenes de la fila que están a 64 píxeles de distancia
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);

            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePositions() {
        //Puede que este tipo de switch falle, cambiarlo al anterior si eso pasa
        if (moving) {
            switch (playerDirection) {
                case LEFT -> xDelta -= 5;
                case UP -> yDelta -= 5;
                case RIGHT -> xDelta += 5;
                case DOWN -> yDelta += 5;
            }
        }
    }

    public void updateGame(){
        updateAnimationTick();
        setAnimation();
        updatePositions();
    }

    //Método para dibujar un componente
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);//Implementar el método del JPanel

        //El observer es un objeto que controla el cambio de estado de una imagen, y no se va a utilizar en este tutorial
        /* drawImage(imagen, posición_x, posición_y, observable) Solo la imagen, con tamaño original
         * drawImage(imagen, posición_x, posición_y, ancho, alto, observable)Imagen con otro tamaño
         * drawImage(imagen.getSubImage(posición_x, posición_y, ancho, alto), posición_x, posición_y, ancho, alto, observable) Sección de la imagen con otro tamaño
         * */
        g.drawImage(animations[playerAction][animationIndex], (int) xDelta, (int) yDelta, 256, 160, null);

    }


}
