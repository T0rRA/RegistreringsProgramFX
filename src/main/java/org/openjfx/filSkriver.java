package org.openjfx;

//Les tilfil
//Strategy Patter'n brukt

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface FilLeseOppforsel {
    public void skriv(File file, Produkt produkt, ProduktKategori produktKategori);

    public <T extends ProduktKategori> T les(File file) throws IOException;
}

// Må endre
//Produkter er lagret som csv
class CSVStrategy implements FilLeseOppforsel {
    //medode som skriver til CSV når CSVStrategy er valgt.
    public void skriv(File file, Produkt produkt, ProduktKategori produktKategori) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuffer enLinje = new StringBuffer();
            enLinje.append(produkt.getProduktNavn());
            enLinje.append(";");
            enLinje.append(produkt.getOmProdukt());
            bufferedWriter.write(enLinje.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

            System.out.println("Skriver til CSV");
        } catch (FileNotFoundException e) {
            //gjør noe
            e.printStackTrace();
        } catch (IOException e) {
            //gjør noe
            e.printStackTrace();
        }

    }


    public <T extends ProduktKategori> T les(File file) throws IOException {
        List<List<String>> utListe = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("csv.csv"))) {
            String linje;
            while ((linje = bufferedReader.readLine()) != null) {
                String[] values = linje.split(";");
                utListe.add(Arrays.asList(values));
            }
        }
        return (T)utListe;
    }
}

//Produktkategori er lagret binært
class BinaryStrategy implements FilLeseOppforsel {
    public void skriv(File file, Produkt produkt, ProduktKategori produktKategori) {
        try (FileOutputStream fileUt = new FileOutputStream(file);
             ObjectOutputStream objektUt = new ObjectOutputStream(fileUt)) {
            objektUt.writeObject(produktKategori);
            objektUt.close();
            System.out.println("Skriver til Binær");
        } catch (IOException e) {
            //gjør noe
            e.printStackTrace();
        }
    }

    public <T extends ProduktKategori> T les(File file) throws IOException {
        try {
            FileInputStream filInn = new FileInputStream("bin");
            ObjectInputStream objIn = new ObjectInputStream(filInn);
            ProduktKategori produktKategori = (ProduktKategori) objIn.readObject();

            System.out.println("Bin lEST");
            objIn.close();
            return (T) produktKategori;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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

    public ProduktKategori lesFraFil(File file) throws IOException {
        return filLeseOppforsel.les(file);
    }
}

