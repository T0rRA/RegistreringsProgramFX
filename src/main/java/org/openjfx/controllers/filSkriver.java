package org.openjfx.controllers;

//Les tilfil
//Strategy Patter'n brukt

import org.openjfx.Produkt;
import org.openjfx.ProduktKategori;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



// Må endre
//Produkter er lagret som csv
class CSVStrategy {
    //medode som skriver til CSV når CSVStrategy er valgt.
    public void leggTil(ProduktKategori produktKategori, Produkt produkt){//TODO:LEGG TIL
        }
    public void fjern(ProduktKategori produktKategori, Produkt produkt){//TODO: LEGG TIL
         }


    public void lagre(){
    /*    try {
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
*/
    }


    public void lastInn()  {
        List<List<String>> utListe = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("csv.csv"))) {
            String linje;
            while ((linje = bufferedReader.readLine()) != null) {
                String[] values = linje.split(";");
                utListe.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//Produktkategori er lagret binært


