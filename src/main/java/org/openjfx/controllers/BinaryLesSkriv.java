package org.openjfx.controllers;

import org.openjfx.ProduktKategori;

import java.io.*;
import java.util.ArrayList;

public class BinaryLesSkriv extends ArrayList<ProduktKategori> {
    private static final long serialVersionUID = 1L;

    public BinaryLesSkriv() {
    }

    //legge til produktKategori
    public void leggTil(ProduktKategori produktKategori) {
        this.add(produktKategori);
        lagre();
    }

    //fjerne produktkategori
    public void fjern(ProduktKategori produktKategori) {
        this.remove(produktKategori);
        lagre();
    }

    //Skriver PK til fil
    public void lagre() {
        try {
            FileOutputStream filUt = new FileOutputStream("filnavn.ser");
            ObjectOutputStream objektUt = new ObjectOutputStream(filUt);

            objektUt.writeObject(this);

            objektUt.close();
            filUt.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void lastInn() {
        File binfil = new File("filnavn.ser");
        if (binfil.exists() && !binfil.isDirectory()) {
            try {
                FileInputStream filInn = new FileInputStream("filnavn.ser");
                ObjectInputStream objektInn = new ObjectInputStream(filInn);
                BinaryLesSkriv tmp = (BinaryLesSkriv) objektInn.readObject();

                this.clear();
                this.addAll(tmp);

                objektInn.close();
                filInn.close();
            } catch (IOException i) {
                i.printStackTrace();
                //return;
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
                //return;
            }
        }else {
            System.out.println("Ingen objekter er lagt til i filen enda");
            try {
                File file = new File("filnavn.ser");
                file.createNewFile();
                System.out.println("Tom fil er opprettet, med filelengde: " + file.length());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}