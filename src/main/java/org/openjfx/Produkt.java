package org.openjfx;

public class Produkt {
    private String produktNavn, omProdukt;
    private ProduktKategori produktKategori;

    public ProduktKategori getProduktKategori() { return produktKategori; }

    public void setProduktKategori(ProduktKategori produktKategori) { this.produktKategori = produktKategori; }

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
