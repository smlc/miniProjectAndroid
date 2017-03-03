package m2dl.mobe.android.project.miniprojectandroid.Domain;

/**
 * Created by Jaafar Diami on 03/03/2017.
 */

public class User {

    private String nomUser;
    private String prenomUser;
    private String photoUser;
    private String emailUser;
    private String pswUser;

    //Constructor
    public User(){
    }

    public User(String pswUser, String emailUser, String prenomUser, String nomUser, String photoUser) {
        this.pswUser = pswUser;
        this.emailUser = emailUser;
        this.prenomUser = prenomUser;
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
    public String toString() {
        return "User{" +
                "nomUser='" + nomUser + '\'' +
                ", prenomUser='" + prenomUser + '\'' +
                ", photoUser='" + photoUser + '\'' +
                ", emailUser='" + emailUser + '\'' +
                ", pswUser='" + pswUser + '\'' +
                '}';
    }
}
