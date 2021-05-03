package Threads;

public class ThreadDemo {
    public static void show() {
        Thread thread = new Thread(new DownloadFileTask());
        thread.start();

        //Metode for å vente til thread er ferdig med å gjøre noe.
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("File is ready to be scanned");

        //Starter thread som interrupter
        System.out.println("Interrupt demo starter");
        Thread thread2 = new Thread(new DownloadFileTaskInterrupt());
        //thread2.start();


        //Threaden interrupter etter 1 sek
        try {
            thread2.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }
}
