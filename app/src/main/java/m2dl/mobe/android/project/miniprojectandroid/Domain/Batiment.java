package m2dl.mobe.android.project.miniprojectandroid.Domain;

import android.view.MotionEvent;
import android.location.*;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class Batiment {

    private String nom;


    private String typeBatiment;
    private double lat;
    private double longi;
    private List<OccupationJour> occupations;
    public Batiment(){

    }
    //Constructor
    public Batiment(String nom,  String typeBatiment, double lat, double longi) {
        this.nom = nom;
        this.typeBatiment = typeBatiment;
        this.lat = lat;
        this.longi = longi;
        occupations = new ArrayList<>();
        occupations.add(new OccupationJour(0,2,5));
        occupations.add(new OccupationJour(2,7,5));
        occupations.add(new OccupationJour(1,4,5));
        occupations.add(new OccupationJour(3,6,7));
    }

    //Accessors


    public List<OccupationJour> getOccupations() {
        return occupations;
    }

    public void setOccupations(List<OccupationJour> occupations) {
        this.occupations = occupations;
    }

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
