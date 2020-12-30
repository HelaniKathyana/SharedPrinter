package ThreadSharedPrinter;

import java.util.Random;

public class Student extends Thread {

    private LaserPrinter laserPrinter;

    /*constructor for student thread class**/
    public Student(String thread_name, ThreadGroup group, LaserPrinter laserPrinter) {
        super(group, thread_name);

        this.laserPrinter = laserPrinter;
    }

    @Override
    public void run() {
        /*random 5 instance of documents will create**/
        for (int i = 0; i < 5; i++) {

            /*declaring and initializing an instance of Document class**/
            Document doc = new Document(this.getName(), "cwk-" + i, GetRandomNumberOfPages());

            //Print the students ' documents
            this.laserPrinter.printDocument(doc);
            System.out.println(this);

            try {
                sleep(GetRandomSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /*generate random number of pages between 25 to 10**/
    private int GetRandomNumberOfPages() {
        Random random = new Random();

        int randomPageNum = random.nextInt(15) + 10;

        return randomPageNum;
    }

    /*generate random sleep time in between 3000 to 1000 milli seconds**/
    private int GetRandomSleepTime() {
        Random random = new Random();

        int randomSleepTime = random.nextInt(2000) + 1000;

        return randomSleepTime;
    }

    @Override
    public String toString() {
        return
                "student_name=" + this.getName() + '\'' + "Document print finished";
    }
}
