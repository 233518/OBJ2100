package Threads;

public class Main {
    public static void main(String[] args) {
        System.out.println(Thread.activeCount()); // Hvor mange threads du bruker atm.
        System.out.println(Runtime.getRuntime().availableProcessors()); // Hvor mange threads du har tilgjengelig

        System.out.println(Thread.currentThread().getName()); //Printer navn på thread.
        System.out.println(Thread.currentThread().getId()); //Printer ID på thread

        //Laster ned 10 ting samtidig.
        for (int i = 0; i<50000; i++) {
            Thread thread = new Thread(new DownloadFileTask()); //Oppretter thread
            thread.start();
        }




        ThreadDemo.show();


    }
}
