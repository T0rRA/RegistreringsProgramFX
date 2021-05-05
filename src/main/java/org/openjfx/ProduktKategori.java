package org.openjfx;
import java.io.Serializable;

public class ProduktKategori implements Serializable
{
    /**
     * ProduktKategori-klassen inneholder
     */
    private static final long serialVersionUID = 1L;

    private String kategoriNavn;
    private String omKategori;

    public ProduktKategori(String kategoriNavn, String omKategori) {
        super();
        this.kategoriNavn = kategoriNavn;
        this.omKategori = omKategori;
    }
    //Getters og setters
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
}