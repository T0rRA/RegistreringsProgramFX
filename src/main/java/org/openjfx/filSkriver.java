package org.openjfx;

//Les tilfil
//Strategy Patter'n brukt

import java.io.*;

interface FilLeseOppforsel {
    public void skriv(File file, Produkt produkt, ProduktKategori produktKategori) ;
}

// Må endre
//Produkter er lagret som csv
class CSVStrategy implements FilLeseOppforsel {
    //medode som skriver til CSV når CSVStrategy er valgt.
    public void skriv(File file, Produkt produkt, ProduktKategori produktKategori) {
        //filenotfound, ioexeption
        /*eventuelt:
        ArrayList<Produkt> produktListe
        for (Produkt : produktListe){}*/
        try{
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        StringBuffer enLinje = new StringBuffer();
        enLinje.append(produkt.getProduktNavn());
        enLinje.append(";");
        enLinje.append(produkt.getOmProdukt());
        bufferedWriter.write(enLinje.toString());
        bufferedWriter.flush();
        bufferedWriter.close();

        System.out.println("Skriver til CSV");
        }catch (FileNotFoundException e){
            //gjør noe
            e.printStackTrace();
        }
        catch (IOException e){
            //gjør noe
            e.printStackTrace();
        }

    }
}

//Produktkategori er lagret binært
class BinaryStrategy implements FilLeseOppforsel {
    public void skriv(File file, Produkt produkt, ProduktKategori produktKategori) {
        try (FileOutputStream fileUt = new FileOutputStream(file);
             ObjectOutputStream objektUt = new ObjectOutputStream(fileUt)) {
            objektUt.writeObject(file);
            objektUt.close();
            System.out.println("Skriver til Binær");
        } catch (IOException e){
            //gjør noe
            e.printStackTrace();
        }
    }
}

abstract class strategiVelger {
    private FilLeseOppforsel filLeseOppforsel;

    public strategiVelger(FilLeseOppforsel filLeseOppforsel) {
        this.filLeseOppforsel = filLeseOppforsel;
    }

    public void skrivTilFil(File file, Produkt produkt, ProduktKategori produktKategori) throws IOException {
        filLeseOppforsel.skriv(file, produkt, produktKategori);
    }

    public void setFilLeseOppforsel(FilLeseOppforsel filType) {
        this.filLeseOppforsel = filType;
    }
}

