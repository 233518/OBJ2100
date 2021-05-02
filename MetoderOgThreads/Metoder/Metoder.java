package Metoder;

import java.util.TimerTask;

public class Metoder {


    /**
     * Oppdatere noe 60 ganger i sekundet
     *
     * //Må extende updateable:
     * public interface Updateable {
     *     void update();
     * }
     */
    protected java.util.Timer timer;
    private void startTimer() {
        this.timer = new java.util.Timer();
        long frameTimeInMilliseconds = (long)(1000 / 60f);
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        //Det du skal oppdatere her
                    }
                });
            }
        };
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);
    }
    /**
     * Fikser floatpoint feil
     */


    private int antKolonner;

    public void floatpointfeil(){
        double vindubredde = 812.0;
        double celleBredde = vindubredde /antKolonner;

        celleBredde = vindubredde/10;

        if (celleBredde%1 != 0) {
            do {
                vindubredde++;
                celleBredde = vindubredde / antKolonner;
            } while (celleBredde % 1 != 0);
        }
    }

}
