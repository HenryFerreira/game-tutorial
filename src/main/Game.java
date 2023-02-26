package main;

public class Game implements Runnable {
    //Atributos
    private GameWindow gameWindow;//Ventana en donde se muestra el panel
    private GamePanel gamePanel;//Panel donde se pinta la imagen
    private Thread gameThread;//Hilo de ejecución para el juego
    private final int FPS_SET = 120;
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


    @Override //Método que viene al implementar la interfaz Runnable, para los Threads
    public void run() {

        double timePerFrame = NANOSECOND / FPS_SET;//Obtiene el tiempo en Nanosegundos para 120 FPS
        long lastFrame = System.nanoTime();//El último tiempo registrado al iniciar
        long now = System.nanoTime(); //el tiempo actual
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while (true) {
            now = System.nanoTime();//el tiempo actual
            //Si la diferencia del tiempo actual con el último registro es mayor igual al tiempo de los 120 FPS
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint(); //Limpia el panel
                lastFrame = now;//Y el último registro pasa a ser el tiempo actual
                frames++;
            }

            //FPS CHECKER
            //Si el tiempo transcurrido menos la última verificación del mismo es mayor o igual a 1000, 1S --> 1000MS
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();//Obtiene la última instancia después de entrar al IF
                System.out.println("FPS: " + frames);
                frames = 0;//Reinicia el conteo de FPS
            }


        }
    }
}
