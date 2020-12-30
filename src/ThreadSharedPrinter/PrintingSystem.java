package ThreadSharedPrinter;

public class PrintingSystem {

    public static void main(String[] args) {

        /*declaring and initializing two thread groups for student and technician**/
        ThreadGroup studentGroup = new ThreadGroup("Student");
        System.out.println("Student Thread Group created");

        ThreadGroup technicianGroup = new ThreadGroup("Technician");
        System.out.println("Technician Thread Group created");

        /* the laser printer object **/
        LaserPrinter laserPrinter = new LaserPrinter("lp-CG.24", studentGroup);

        /*the student and technician threads**/
        Student student1 = new Student("Helani", studentGroup, laserPrinter);

        Student student2 = new Student("Sachintha", studentGroup, laserPrinter);

        Student student3 = new Student("Dinith", studentGroup, laserPrinter);

        Student student4 = new Student("Madu", studentGroup, laserPrinter);


        Technician papperTechnician = new PapperTechnician("paperTec", technicianGroup, laserPrinter);
        Technician tonerTechnician = new TonerTechnician("TonerTec", technicianGroup, laserPrinter);

        /*starting student threads**/
        student1.start();
        System.out.println("Student" + student1.getName() + " start printing");

        student2.start();
        System.out.println("Student" + student2.getName() + " start printing");

        student3.start();
        System.out.println("Student" + student3.getName() + " start printing");

        student4.start();
        System.out.println("Student" + student4.getName() + " start printing");

        /*starting technician threads**/
        papperTechnician.start();
        tonerTechnician.start();

        /* wait for all the threads to complete**/
        try {

            student1.join();
            System.out.println("Student " + student1.getName() + " finished printing");

            student2.join();
            System.out.println("Student " + student2.getName() + " finished printing");

            student3.join();
            System.out.println("Student " + student3.getName() + " finished printing");

            student4.join();
            System.out.println("Student " + student4.getName() + " finished printing");

            papperTechnician.join();
            System.out.println("Technician " + papperTechnician.getName() + " finished refilling");

            tonerTechnician.join();
            System.out.println("Technician " + tonerTechnician.getName() + " finished toner replacing");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
