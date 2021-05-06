package org.openjfx;
import com.opencsv.bean.CsvBindByPosition;

import java.io.Serializable;

public class Produkt implements Serializable {

    private static final long serialVersionUID = 1L;

    public Produkt(String produktNavn, String omProdukt, String produktKategori) {
        this.produktNavn = produktNavn;
        this.omProdukt = omProdukt;
        this.produktKategori = produktKategori;
    }

    private String produktNavn;

    private String omProdukt;

    private String produktKategori;

    public String getProduktKategori() { return produktKategori; }

    public void setProduktKategori(String produktKategori) { this.produktKategori = produktKategori; }

    public String getProduktNavn() {
        return produktNavn;
    }

    public void setProduktNavn(String produktNavn) {
        this.produktNavn = produktNavn;
    }

    public String getOmProdukt() {
        return omProdukt;
    }

    public void setOmProdukt(String omProdukt) {
        this.omProdukt = omProdukt;
    }
}
