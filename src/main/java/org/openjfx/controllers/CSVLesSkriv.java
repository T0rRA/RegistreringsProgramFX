package org.openjfx.controllers;
import com.opencsv.bean.CsvToBeanBuilder;
import org.openjfx.Produkt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;


public class CSVLesSkriv {
    //returnerer liste av produkter
    public  List<Produkt> lesCSV(){
    String filNavn = "CSV.csv";

        List<Produkt> produktListe = null;
        try {
            produktListe = new CsvToBeanBuilder(new FileReader(filNavn)).withType(Produkt.class).build().parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        produktListe.forEach(System.out::println); //fjern senere
        return produktListe;
    }
}
