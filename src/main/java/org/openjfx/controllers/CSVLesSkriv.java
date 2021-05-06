package org.openjfx.controllers;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.openjfx.Produkt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CSVLesSkriv {
    //Leser CSV, konverterer til Liste<produkt>
    public List<Produkt> lesCSV() {
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

    public void skrivTilCSV(List<Produkt> produkts) {
        try (

                Writer writer = Files.newBufferedWriter(Paths.get("csv.csv"));
        ) {
            StatefulBeanToCsv<Produkt> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(produkts);

        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
