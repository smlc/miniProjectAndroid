package m2dl.mobe.android.project.miniprojectandroid.Domain;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class Anomalie implements Parcelable {

    private String titreAnomalie;
    private Date dateAnomalie;
    private String photoAnomalie;
    private Criticité typeCritic;
    private String orientation;
    private Location positionAnomalie;
    public static final Parcelable.Creator<Anomalie> CREATOR = new Parcelable.Creator<Anomalie>() {
        @Override
        public Anomalie createFromParcel(Parcel source) {
            return new Anomalie(source);
        }

        @Override
        public Anomalie[] newArray(int size) {
            return new Anomalie[size];
        }
    };
    //Constructor
    public Anomalie(String titreAnomalie, Date dateAnomalie, String photoAnomalie, Criticité typeCritic, String orientation, Location positionAnomalie) {
        this.titreAnomalie = titreAnomalie;
        this.dateAnomalie = dateAnomalie;
        this.photoAnomalie = photoAnomalie;
        this.typeCritic = typeCritic;
        this.orientation = orientation;
        this.positionAnomalie = positionAnomalie;
    }

    public Anomalie(Parcel in) {
        this.titreAnomalie = in.readString();
        this.dateAnomalie = (Date) in.readSerializable();
        this.photoAnomalie = in.readString();
        this.typeCritic = Criticité.valueOf(in.readString());
        this.orientation = in.readString();
        this.positionAnomalie = Location.CREATOR.createFromParcel(in);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titreAnomalie);
        dest.writeSerializable(dateAnomalie);
        dest.writeString(photoAnomalie);
        dest.writeString(typeCritic.name());
        dest.writeString(orientation);
        positionAnomalie.writeToParcel(dest,flags);
    }
}
