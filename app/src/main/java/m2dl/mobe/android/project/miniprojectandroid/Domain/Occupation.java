package m2dl.mobe.android.project.miniprojectandroid.Domain;

/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class Occupation {

    private Integer nbrOccupJour;
    private Integer nbrOccupHeure;

    //Constructor
    public Occupation(Integer nbrOccupJour, Integer nbrOccupHeure) {
        this.nbrOccupJour = nbrOccupJour;
        this.nbrOccupHeure = nbrOccupHeure;
    }

    //Accessors

    public Integer getNbrOccupJour() {
        return nbrOccupJour;
    }

    public void setNbrOccupJour(Integer nbrOccupJour) {
        this.nbrOccupJour = nbrOccupJour;
    }

    public Integer getNbrOccupHeure() {
        return nbrOccupHeure;
    }

    public void setNbrOccupHeure(Integer nbrOccupHeure) {
        this.nbrOccupHeure = nbrOccupHeure;
    }
}
