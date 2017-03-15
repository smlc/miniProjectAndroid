package m2dl.mobe.android.project.miniprojectandroid.Domain;

/**
 * Created by MarS on 10/03/2017.
 */

public class OccupationJour {

    //Tranche horaire
    /*
    * 0 -> 0h à 6h
    * 1 -> 6h à 12h
    * 2 -> 12H à 18h
    * 3 -> 18h à 0H
     */
    private int trancheHoraire;
    private int nbrOccupHour;
    private int jourSemaine;

    public OccupationJour(){

    }

    public OccupationJour(int trancheHoraire, int nbrOccupHour,  int jourSemaine) {
        this.trancheHoraire = trancheHoraire;
        this.nbrOccupHour = nbrOccupHour;
        this.jourSemaine = jourSemaine;
    }

    public int getTrancheHoraire() {
        return trancheHoraire;
    }

    public void setTrancheHoraire(int trancheHoraire) {
        this.trancheHoraire = trancheHoraire;
    }

    public int getNbrOccupHour() {
        return nbrOccupHour;
    }

    public void setNbrOccupHour(int nbrOccupHour) {
        this.nbrOccupHour = nbrOccupHour;
    }

    public int getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(int jourSemaine) {
        this.jourSemaine = jourSemaine;
    }
}
