package ThreadSharedPrinter;

public class LaserPrinter implements ServicePrinter {

    private String printerId;
    private int paperLevel;
    private int tonerLevel;
    private int documentPrinted;
    private ThreadGroup students;

    private Object lock = new Object();

    /*initialize the printer resources**/
    public LaserPrinter(String printerId, ThreadGroup students) {
        this.printerId = printerId;
        this.paperLevel = ServicePrinter.Full_Paper_Tray;
        this.tonerLevel = ServicePrinter.Full_Toner_Level;
        this.documentPrinted = 0;
        this.students = students;
    }

    @Override
    public String toString() {
        return "LaserPrinter{" +
                "printerId='" + printerId + '\'' +
                ", paperLevel=" + paperLevel +
                ", tonerLevel=" + tonerLevel +
                ", documentPrinted=" + documentPrinted +
                '}';
    }

    @Override
    public void printDocument(Document document) {
        synchronized (lock) {
            System.out.println(this.toString());

            while (this.paperLevel < document.getNumberOfPages() || this.tonerLevel < document.getNumberOfPages()) {
                try {
                    System.out.println("waiting for print documents,Currently printer doesn't fulfil the printing requirements");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                /*Check printing requirements for the printer**/
                if (this.paperLevel > document.getNumberOfPages() && this.tonerLevel > document.getNumberOfPages()) {

                    /*reduce the random generated paper**/
                    paperLevel = paperLevel - document.getNumberOfPages();

                    /*One toner unit was used to print each page**/
                    tonerLevel = tonerLevel - document.getNumberOfPages();

                    /*increase the number of documents print**/
                    documentPrinted = documentPrinted + 1;
                    System.out.println("Document print Successfully");
                }
                /*printed details**/
                System.out.println(this.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.notifyAll();
            }
        }
    }

    @Override
    public void refillPaper() {
        synchronized (lock) {
            /*wait until printer has sufficient number of papers to print documents**/
            while (this.paperLevel + LaserPrinter.SheetsPerPack > LaserPrinter.Full_Paper_Tray) {
                try {
                    /*check available number of threads in student thread group**/
                    if (this.checkStudentAvailability()) {
                        System.out.println("student active count:" + students.activeCount());

                        System.out.println("Waiting for additional papers, printer has sufficient pappers to print");

                        lock.wait(5000);
                    } else {
                        System.out.println("Printing process has been finished ");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                /*refill papers if the paper count less than 250**/
                if (this.paperLevel + LaserPrinter.SheetsPerPack < LaserPrinter.Full_Paper_Tray) {
                    paperLevel = paperLevel + LaserPrinter.SheetsPerPack;
                    System.out.println("Papers successfully refilled");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.notifyAll();
            }
        }
    }

    /* method check the available live count threads in student thread group**/
    private boolean checkStudentAvailability() {
        return students.activeCount() > 0;
    }

    @Override
    public void replaceTonerCartridge() {
        synchronized (lock) {

            /*wait until printer has sufficient level of toner to print documents**/
            while (this.tonerLevel > LaserPrinter.Minimum_Toner_Level) {
                try {
                    /*check available number of threads in student thread group**/
                    if (this.checkStudentAvailability()) {
                        System.out.println("student active count:" + students.activeCount());
                        System.out.println("Waiting to replace toner, printer has sufficient toner level to print");
                        lock.wait(5000);
                    } else {
                        System.out.println("Printing process has been finished");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                /*replace toner Cartridge if current toner level less than 10**/
                if (this.tonerLevel < LaserPrinter.Minimum_Toner_Level) {
                    this.tonerLevel = LaserPrinter.Full_Toner_Level;
                    System.out.println("Toner successfully replaced");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.notifyAll();
            }
        }
    }
}
