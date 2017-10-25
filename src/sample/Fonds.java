package sample;


import java.io.Serializable;

public class Fonds implements IFonds, Serializable {
    private String Naam;
    private double Koers;
    public Fonds(String Naam, double Koers){
        this.Naam = Naam;
        this.Koers = Koers;
    }
    @Override
    public String getNaam() {
        return Naam;
    }

    public void setKoers(double koers) {
        Koers = koers;
    }

    @Override
    public double getKoers() {
        return Koers;
    }
}
