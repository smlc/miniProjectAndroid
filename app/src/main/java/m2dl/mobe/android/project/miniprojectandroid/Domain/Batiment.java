package m2dl.mobe.android.project.miniprojectandroid.Domain;

import android.view.MotionEvent;
import android.location.*;


/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class Batiment {

    private String nom;
    private Location positionBatiment;
    private Occupation nbrOccupant;
    private String typeBatiment;

    //Constructor
    public Batiment(String nom, Occupation nbrOccupant, String typeBatiment, Location position) {
        this.nom = nom;
        this.nbrOccupant = nbrOccupant;
        this.typeBatiment = typeBatiment;
        this.positionBatiment = position;
    }

    //Accessors
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Location getPosition() {
        return positionBatiment;
    }

    public void setPosition(Location position) {
        this.positionBatiment = position;
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


}
