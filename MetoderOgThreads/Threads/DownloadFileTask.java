package Threads;

public class DownloadFileTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Downloading a file " + Thread.currentThread().getName());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Download Complete: " + Thread.currentThread().getName());
    }
}
