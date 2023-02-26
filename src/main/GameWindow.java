package main;

import javax.swing.*;

public class GameWindow {

    private JFrame jFrame;

    public GameWindow(GamePanel gamePanel) {
        jFrame = new JFrame();//Instancia del atributo

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Al presionar la X se cierre el programa
        jFrame.add(gamePanel);//Colocar el panel en la ventana
        jFrame.setLocationRelativeTo(null);//Abre la ventana al centro de la pantalla
        jFrame.setResizable(false);//Para que no se pueda redimensionar
        jFrame.pack();//Hace que la ventana se ajuste al tamaño que necesiten sus componentes
        jFrame.setVisible(true);//Mostrar la ventana
    }
}
