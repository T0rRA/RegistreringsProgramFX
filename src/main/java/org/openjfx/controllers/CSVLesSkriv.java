package org.openjfx.controllers;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.openjfx.Produkt;
import org.openjfx.ProduktKategori;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CSVLesSkriv extends ArrayList<Produkt> {
    public CSVLesSkriv() {
    }
    //legge til produktKategori
    public void leggTil(Produkt produkt)
    {
        this.add(produkt);
        skrivTilCSV();
    }
    //fjerne produktkategori
    public void fjern(Produkt produkt)
    {
        this.remove(produkt);
        skrivTilCSV();
    }

    //Leser CSV, konverterer til Liste<produkt>
    //Leser CSV, konverterer til Liste<produkt>
    public void lesCSV() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("CSV.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Produkt> produktList = new ArrayList<>();
        String line = null;
        while (true) {

            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            // splitt på(',')
            String[] csvProdukt = line.split(",");

            // Lager produktobjekt
            Produkt produktObj = new Produkt(null, null, null);

            // legger til produkt verdier
            produktObj.setProduktNavn(csvProdukt[0]);
            produktObj.setOmProdukt(csvProdukt[1]);
            produktObj.setProduktKategori(csvProdukt[2]);


            // legger objektene i liste
            produktList.add(produktObj);
            //leser inn
            this.clear();
            this.addAll(produktList);
        }
    }

    public void skrivTilCSV() {
        try (
                Writer writer = Files.newBufferedWriter(Paths.get("csv.csv"));
        ) {
            StatefulBeanToCsv<Produkt> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();

            beanToCsv.write(this);

        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
