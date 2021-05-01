package org.openjfx;

//Les tilfil
//Strategy Patter'n brukt

interface FilLeseOppforsel {
    public void skriv();
}

//Produkter er lagret som csv
class CSVStrategy implements FilLeseOppforsel {
    public void skriv() {
        //implementer her: metode for å lese csv

        System.out.println("Skriver til CSV");

    }
}

//Produktkategori er lagret binært
class BinaryStrategy implements FilLeseOppforsel {
    public void skriv() {
        //implementer her metode for å lese binærfil

        System.out.println("Skriver til Binær");
    }
}
abstract class strategiVelger {
    private FilLeseOppforsel filLeseOppforsel;

    public strategiVelger(FilLeseOppforsel filLeseOppforsel) {
        this.filLeseOppforsel = filLeseOppforsel;
    }

    public void skrivTilFil() {
        filLeseOppforsel.skriv();
    }

    public void setFilLeseOppforsel(FilLeseOppforsel filType) {
        this.filLeseOppforsel = filType;
    }
}

