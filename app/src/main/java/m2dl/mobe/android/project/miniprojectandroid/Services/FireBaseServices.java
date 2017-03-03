package m2dl.mobe.android.project.miniprojectandroid.Services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import m2dl.mobe.android.project.miniprojectandroid.Domain.User;

/**
 * Created by MarS on 03/03/2017.
 */

public class FireBaseServices {

    FirebaseDatabase database;

    public FireBaseServices(){
        database = FirebaseDatabase.getInstance();
    }

    public void storeUser(User user){

        DatabaseReference myRef = database.getReference("users");

        DatabaseReference userChild = myRef.push();
        userChild.child("nom").setValue(user.getNomUser());
        userChild.child("prenom").setValue(user.getPrenomUser());
        userChild.child("photo").setValue(user.getPhotoUser());
        userChild.child("email").setValue(user.getEmailUser());
        userChild.child("mdp").setValue(user.getPswUser());
    }
}
