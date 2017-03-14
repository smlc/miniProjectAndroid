package m2dl.mobe.android.project.miniprojectandroid.Domain;

import android.view.MotionEvent;
import android.location.*;


/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class Batiment {

    private String nom;

    private Occupation nbrOccupant;
    private String typeBatiment;
    private double lat;
    private double longi;

    public Batiment(){

    }
    //Constructor
    public Batiment(String nom, Occupation nbrOccupant, String typeBatiment, double lat, double longi) {
        this.nom = nom;
        this.nbrOccupant = nbrOccupant;
        this.typeBatiment = typeBatiment;
       this.lat = lat;
        this.longi = longi;
    }

    //Accessors


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Occupation getNbrOccupant() {
        return nbrOccupant;
    }

    public void setNbrOccupant(Occupation nbrOccupant) {
        this.nbrOccupant = nbrOccupant;
    }

    public String getTypeBatiment() {
        return typeBatiment;
    }

    public void setTypeBatiment(String typeBatiment) {
        this.typeBatiment = typeBatiment;
    }

    @Override
    public String toString() {
        return nom;
    }
}
