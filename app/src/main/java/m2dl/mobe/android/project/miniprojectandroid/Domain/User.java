package m2dl.mobe.android.project.miniprojectandroid.Domain;

import android.provider.ContactsContract;

/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class User {

    private String nomUser;
    private String prénomUser;
    private String photoUser;
    private String emailUser;
    private String pswUser;

    //Constructor
    public User(String pswUser, String emailUser, String prénomUser, String nomUser, String photoUser) {
        this.pswUser = pswUser;
        this.emailUser = emailUser;
        this.prénomUser = prénomUser;
        this.nomUser = nomUser;
        this.photoUser = photoUser;
    }

    //Accessors

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrénomUser() {
        return prénomUser;
    }

    public void setPrénomUser(String prénomUser) {
        this.prénomUser = prénomUser;
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
}
