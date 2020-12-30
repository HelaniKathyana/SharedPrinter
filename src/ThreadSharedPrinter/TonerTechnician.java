package ThreadSharedPrinter;

import java.util.Random;

public class TonerTechnician extends Technician {

    public TonerTechnician(String thread_name, ThreadGroup group, LaserPrinter laserPrinter) {
        /*access the parent constructor*/
        super(thread_name, group, laserPrinter);
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            // call the method that tries to replace the toner cartridge
            this.laserPrinter.replaceTonerCartridge();
            try {
                /* sleep the current thread for a random amount of time*/
                sleep(GetRandomSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*generate random sleep time in between 2001 to 1000 milli seconds**/
    private int GetRandomSleepTime() {
        Random random = new Random();

        int randomSleepTime = random.nextInt(2000 - 1000 + 1) + 1000;

        return randomSleepTime;
    }

}
