package ThreadSharedPrinter;

public interface ServicePrinter extends Printer
{
    // Printer constants
    /*maximum amount of papers in a paper tray**/
    public final int Full_Paper_Tray  = 250;

    /*number of papers which added in refill process**/
    public final int SheetsPerPack    = 50 ;

    /*maximum toner level of the printer**/
    public final int Full_Toner_Level        = 500;

    /*minimum toner level of the printer which can print papers**/
    public final int Minimum_Toner_Level     = 10;

    public final int PagesPerTonerCartridge  = 500;


    // Technician methods
    public void refillPaper( );

    public void replaceTonerCartridge( );
}
