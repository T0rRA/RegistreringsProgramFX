package org.openjfx;

public class Produkt {
    public Produkt(String produktNavn, String omProdukt, String produktKategori) {
        this.produktNavn = produktNavn;
        this.omProdukt = omProdukt;
        this.produktKategori = produktKategori;
    }

    private String produktNavn, omProdukt;
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
