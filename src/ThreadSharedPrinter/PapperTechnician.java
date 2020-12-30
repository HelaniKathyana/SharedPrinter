package ThreadSharedPrinter;

import java.util.Random;

public class PapperTechnician extends Technician {

    /*constructor for the paper technician class*/
    public PapperTechnician(String thread_name, ThreadGroup group, LaserPrinter laserPrinter) {
        /*access the parent constructor*/
        super(thread_name, group, laserPrinter);
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            /*call the refill method*/
            this.laserPrinter.refillPaper();
            try {
                sleep(GetRandomSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*generate random sleep time in between 1500 t0 500 **/
    private int GetRandomSleepTime() {
        Random random = new Random();

        int randomSleepTime = random.nextInt(1000) + 500;

        return randomSleepTime;
    }

}
