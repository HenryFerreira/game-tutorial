package main;

public class Game implements Runnable {
    //Atributos
    private GameWindow gameWindow;//Ventana en donde se muestra el panel
    private GamePanel gamePanel;//Panel donde se pinta la imagen
    private Thread gameThread;//Hilo de ejecución para el juego
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private final double NANOSECOND = 1000000000.0;

    //Constructor
    public Game() {//Instancia de los atributos
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);//Colocar el Panel en la Ventana
        gamePanel.setFocusable(true);//Permite hacer focus sobre el panel
        gamePanel.requestFocus();//Obtener el enfoque de entrada de datos del teclado, para poder utilizarlo en el panel
        stardGameLoop(); //Inicia el loop de juego
    }


    private void stardGameLoop() {
        gameThread = new Thread(this);//Instancia el hilo de ejecución sobre la clase Game
        gameThread.start();//Inicia el hilo
    }

    public void update(){
        gamePanel.updateGame();
    }

    @Override //Método que viene al implementar la interfaz Runnable, para los Threads
    public void run() {
        double timePerFrame = NANOSECOND / FPS_SET;//Obtiene el tiempo en Nanosegundos para 120 FPS
        double timePerUpdate = NANOSECOND / UPS_SET;//Obtiene el tiempo en Nanosegundos para 200 UPS

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }
            if (deltaF >= 1) {
                gamePanel.repaint(); //Limpia el panel
                frames++;
                deltaF--;
            }

            //FPS CHECKER
            //Si el tiempo transcurrido menos la última verificación del mismo es mayor o igual a 1000, 1S --> 1000MS
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();//Obtiene la última instancia después de entrar al IF
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                frames = 0;//Reinicia el conteo de FPS
                updates = 0;//Reinicia el conteo de FPS

            }


        }
    }
}
