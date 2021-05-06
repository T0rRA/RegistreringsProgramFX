package org.openjfx;
import com.opencsv.bean.CsvBindByPosition;

public class Produkt {
    public Produkt(String produktNavn, String omProdukt, String produktKategori) {
        this.produktNavn = produktNavn;
        this.omProdukt = omProdukt;
        this.produktKategori = produktKategori;
    }
    @CsvBindByPosition(position = 0)
    private String produktNavn;
    @CsvBindByPosition(position = 1)
    private String omProdukt;
    @CsvBindByPosition(position = 2)
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
