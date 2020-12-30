package ThreadSharedPrinter;

public class Technician extends Thread {

    protected LaserPrinter laserPrinter;

    /* the default constructor for the technician class**/
    public Technician(String name, ThreadGroup group, LaserPrinter laserPrinter) {
        super(group, name);
        this.laserPrinter = laserPrinter;
    }

}
