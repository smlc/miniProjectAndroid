package m2dl.mobe.android.project.miniprojectandroid.Services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
       /* userChild.child("nom").setValue(user.get);
        userChild.child("prenom").setValue(user);
        userChild.child("photo").setValue(user);
        userChild.child("email").setValue();
        userChild.child("mdp").setValue();*/
    }
}
