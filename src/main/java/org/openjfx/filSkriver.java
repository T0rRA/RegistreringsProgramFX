package org.openjfx;

//Les tilfil
//Strategy Patter'n brukt

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface FilLeseOppforsel {
    public void leggTil(ProduktKategori produktKategori, Produkt produkt);
    public void fjern(ProduktKategori produktKategori, Produkt produkt);
    public void lagre();
    public void lastInn();


}

// Må endre
//Produkter er lagret som csv
class CSVStrategy implements FilLeseOppforsel {
    //medode som skriver til CSV når CSVStrategy er valgt.
    public void leggTil(ProduktKategori produktKategori, Produkt produkt){//TODO:LEGG TIL
        }
    public void fjern(ProduktKategori produktKategori, Produkt produkt){//TODO: LEGG TIL
         }


    public void lagre(){
    /*    try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            StringBuffer enLinje = new StringBuffer();
            enLinje.append(produkt.getProduktNavn());
            enLinje.append(";");
            enLinje.append(produkt.getOmProdukt());
            bufferedWriter.write(enLinje.toString());
            bufferedWriter.flush();
            bufferedWriter.close();

            System.out.println("Skriver til CSV");
        } catch (FileNotFoundException e) {
            //gjør noe
            e.printStackTrace();
        } catch (IOException e) {
            //gjør noe
            e.printStackTrace();
        }
*/
    }


    public void lastInn()  {
        List<List<String>> utListe = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("csv.csv"))) {
            String linje;
            while ((linje = bufferedReader.readLine()) != null) {
                String[] values = linje.split(";");
                utListe.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//Produktkategori er lagret binært
class BinaryStrategy extends ArrayList<ProduktKategori> implements FilLeseOppforsel
{
    private static final long serialVersionUID = 1L;

    public BinaryStrategy(){}

    //legge til produktKategori
    public void leggTil(ProduktKategori produktKategori, Produkt produkt)
    {
        this.add(produktKategori);
        lagre();
    }
    //fjerne produktkategori
    public void fjern(ProduktKategori produktKategori, Produkt produkt)
    {
        this.remove(produktKategori);
        lagre();
    }
    //Skriver PK til fil
    public void lagre()
    {
        try
        {
            FileOutputStream filUt = new FileOutputStream("filnavn.ser");
            ObjectOutputStream objektUt = new ObjectOutputStream(filUt);

            objektUt.writeObject(this);

            objektUt.close();
            filUt.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }

    public void lastInn()
    {
        try
        {
            FileInputStream filInn = new FileInputStream("filnavn.ser");
            ObjectInputStream objektInn = new ObjectInputStream(filInn);
            BinaryStrategy tmp = (BinaryStrategy) objektInn.readObject();

            this.clear();
            this.addAll(tmp);

            objektInn.close();
            filInn.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
            return;
        }
        catch(ClassNotFoundException c)
        {
            c.printStackTrace();
            return;
        }
    }
}

abstract class strategiVelger {
    private FilLeseOppforsel filLeseOppforsel;
    public void leggTil(ProduktKategori produktKategori, Produkt produkt){filLeseOppforsel.leggTil( produktKategori, produkt);}

    public strategiVelger(FilLeseOppforsel filLeseOppforsel) {
        this.filLeseOppforsel = filLeseOppforsel;
    }

    public void lastInn() {
        filLeseOppforsel.lagre();
    }

    public void setFilLeseOppforsel(FilLeseOppforsel filType) {
        this.filLeseOppforsel = filType;
    }

    public void lesFraFil() {
         filLeseOppforsel.lastInn();
    }
}

