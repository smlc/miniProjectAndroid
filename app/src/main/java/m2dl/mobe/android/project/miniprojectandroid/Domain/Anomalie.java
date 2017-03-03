package m2dl.mobe.android.project.miniprojectandroid.Domain;

import android.location.Location;

import java.util.Date;

/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class Anomalie {

    private String titreAnomalie;
    private Date dateAnomalie;
    private User proprio;
    private String photoAnomalie;
    private Criticité typeCritic;
    private String orientation;
    private Location positionAnomalie;

    //Constructor
    public Anomalie(String titreAnomalie, Date dateAnomalie, User proprio, String photoAnomalie, Criticité typeCritic, String orientation, Location positionAnomalie) {
        this.titreAnomalie = titreAnomalie;
        this.dateAnomalie = dateAnomalie;
        this.proprio = proprio;
        this.photoAnomalie = photoAnomalie;
        this.typeCritic = typeCritic;
        this.orientation = orientation;
        this.positionAnomalie = positionAnomalie;
    }

    //Accessors

    public String getTitreAnomalie() {
        return titreAnomalie;
    }

    public void setTitreAnomalie(String titreAnomalie) {
        this.titreAnomalie = titreAnomalie;
    }

    public Date getDateAnomalie() {
        return dateAnomalie;
    }

    public void setDateAnomalie(Date dateAnomalie) {
        this.dateAnomalie = dateAnomalie;
    }

    public User getProprio() {
        return proprio;
    }

    public void setProprio(User proprio) {
        this.proprio = proprio;
    }

    public String getPhotoAnomalie() {
        return photoAnomalie;
    }

    public void setPhotoAnomalie(String photoAnomalie) {
        this.photoAnomalie = photoAnomalie;
    }

    public Criticité getTypeCritic() {
        return typeCritic;
    }

    public void setTypeCritic(Criticité typeCritic) {
        this.typeCritic = typeCritic;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Location getPositionAnomalie() {
        return positionAnomalie;
    }

    public void setPositionAnomalie(Location positionAnomalie) {
        this.positionAnomalie = positionAnomalie;
    }
}
