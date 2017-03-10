package m2dl.mobe.android.project.miniprojectandroid.Domain;

/**
 * Created by MarS on 10/03/2017.
 */

public class OccupationJour {

    private int trancheHoraire;

    private int nbrOccupHour;
    public String nomBatiment;
    private int jourSemaine;
    public OccupationJour(){

    }

    public OccupationJour(int trancheHoraire, int nbrOccupHour, String nomBatiment, int jourSemaine) {
        this.trancheHoraire = trancheHoraire;
        this.nbrOccupHour = nbrOccupHour;
        this.nomBatiment = nomBatiment;
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

    public String getNomBatiment() {
        return nomBatiment;
    }

    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }

    public int getJourSemaine() {
        return jourSemaine;
    }

    public void setJourSemaine(int jourSemaine) {
        this.jourSemaine = jourSemaine;
    }
}
