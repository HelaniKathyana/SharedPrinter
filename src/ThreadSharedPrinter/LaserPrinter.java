package ThreadSharedPrinter;

public class LaserPrinter implements ServicePrinter {

    private String printerId;
    private int paperLevel;
    private int tonerLevel;
    private int documentPrinted;

    public  LaserPrinter(){}

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
    public void replaceTonerCartridge() {

    }

    @Override
    public void refillPaper() {

    }

    @Override
    public void printDocument(Document document) {

    }
}
