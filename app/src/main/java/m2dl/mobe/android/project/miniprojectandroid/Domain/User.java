package m2dl.mobe.android.project.miniprojectandroid.Domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class User implements Parcelable{



    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private List<Anomalie> anomalies;
    private String nomUser;
    private String prenomUser;
    private String photoUser;
    private String emailUser;
    private String pswUser;
    private String lienEmploisTemps;
    //Constructor
    public User(){
    }

    public User(String pswUser, String emailUser, String prenomUser, String nomUser, String photoUser) {
        this.pswUser = pswUser;
        this.emailUser = emailUser;
        this.prenomUser = prenomUser;
        this.nomUser = nomUser;
        this.photoUser = photoUser;
        this.anomalies = new ArrayList<>();
    }

    public  User(Parcel in) {
        nomUser = in.readString();
        prenomUser = in.readString();
        photoUser = in.readString();
        emailUser = in.readString();
        pswUser = in.readString();
        List<Anomalie> list = null;
        in.readList(list, Anomalie.class.getClassLoader());
        anomalies = list;
    }
    //Accessors

    public List<Anomalie> getAnomalies() {
        return anomalies;
    }

    public void setAnomalies(List<Anomalie> anomalies) {
        this.anomalies = anomalies;
    }

    public String getNomUser() {
        return nomUser;
    }


    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getPhotoUser() {
        return photoUser;
    }

    public void setPhotoUser(String photoUser) {
        this.photoUser = photoUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPswUser() {
        return pswUser;
    }

    public void setPswUser(String pswUser) {
        this.pswUser = pswUser;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nomUser);
        dest.writeString(prenomUser);
        dest.writeString(photoUser);
        dest.writeString(emailUser);
        dest.writeString(pswUser);
    }
}
