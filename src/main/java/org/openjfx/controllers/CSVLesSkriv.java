package org.openjfx.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVLesSkriv {
    //
    public List<List<String>> lesCSV() throws FileNotFoundException {
        List<List<String>> innholdFraCSV = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("csv.csv"));) {
            while (scanner.hasNextLine()) {
                innholdFraCSV.add(parseLinjene(scanner.nextLine()));
            }
        }return innholdFraCSV;
    }
    //Hver linje er et "produkt" som inneholder produktets navn [0] om[1] og produktKategori [2]
    private List<String> parseLinjene(String line) {
        List<String> etProdukt = new ArrayList<String>();
        try (Scanner scannerHverRad = new Scanner(line)) {
            scannerHverRad.useDelimiter(";");
            while (scannerHverRad.hasNext()) {
                etProdukt.add(scannerHverRad.next());
            }
        }
        return etProdukt;
    }
}
