package org.openjfx;

public class ProduktKategori {
    private String kategoriNavn, omKategori;

    public ProduktKategori(String kategoriNavn, String omKategori) {
        this.kategoriNavn = kategoriNavn;
        this.omKategori = omKategori;
    }

    public String getKategoriNavn() {
        return kategoriNavn;
    }

    public void setKategoriNavn(String kategoriNavn) {
        this.kategoriNavn = kategoriNavn;
    }

    public String getOmKategori() {
        return omKategori;
    }

    public void setOmKategori(String omKategori) {
        this.omKategori = omKategori;
    }

    @Override
    public String toString(){
        return kategoriNavn;
    }
}
