package m2dl.mobe.android.project.miniprojectandroid.Domain;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class Anomalie implements Parcelable {

    private String titre;
    private Date dateAnomalie;
    private String photoAnomalie;
    private Criticite criticiter;
    private String orientation;
    private double lat;
    private double longi;
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

    public Anomalie(){

    }
    //Constructor
    public Anomalie(String titre, Date dateAnomalie, String photoAnomalie, Criticite criticiter, String orientation, double lat, double longi) {
        this.titre = titre;
        this.dateAnomalie = dateAnomalie;
        this.photoAnomalie = photoAnomalie;
        this.criticiter = criticiter;
        this.orientation = orientation;
        this.lat = lat;
        this.longi = longi;
    }

    public Anomalie(Parcel in) {
        this.titre = in.readString();
        this.dateAnomalie = (Date) in.readSerializable();
        this.photoAnomalie = in.readString();
        this.criticiter = Criticite.valueOf(in.readString());
        this.orientation = in.readString();
        this.lat = in.readDouble();
        this.longi = in.readDouble();
    }

    //Accessors



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titre);
        dest.writeSerializable(dateAnomalie);
        dest.writeString(photoAnomalie);
        dest.writeString(criticiter.name());
        dest.writeString(orientation);
        dest.writeDouble(lat);
        dest.writeDouble(longi);
    }

    @Override
    public String toString() {
        return titre + "  " + criticiter;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public Criticite getCriticiter() {
        return criticiter;
    }

    public void setCriticiter(Criticite criticiter) {
        this.criticiter = criticiter;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

}
