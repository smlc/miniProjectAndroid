package m2dl.mobe.android.project.miniprojectandroid.Domain;

/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class Occupation {

    private int nbrOccupJour;
    private int nbrOccupHeure;


    public Occupation(){

    }
    //Constructor
    public Occupation(int nbrOccupJour, int nbrOccupHeure) {
        this.nbrOccupJour = nbrOccupJour;
        this.nbrOccupHeure = nbrOccupHeure;
    }

    //Accessors

    public int getNbrOccupJour() {
        return nbrOccupJour;
    }

    public void setNbrOccupJour(Integer nbrOccupJour) {
        this.nbrOccupJour = nbrOccupJour;
    }

    public int getNbrOccupHeure() {
        return nbrOccupHeure;
    }

    public void setNbrOccupHeure(Integer nbrOccupHeure) {
        this.nbrOccupHeure = nbrOccupHeure;
    }
}
