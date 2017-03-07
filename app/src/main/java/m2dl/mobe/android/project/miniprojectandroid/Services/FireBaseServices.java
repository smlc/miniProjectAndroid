package m2dl.mobe.android.project.miniprojectandroid.Services;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import m2dl.mobe.android.project.miniprojectandroid.Activity.AccueilActivity;
import m2dl.mobe.android.project.miniprojectandroid.Domain.User;

/**
 * Created by MarS on 03/03/2017.
 */

public class FireBaseServices {

    private FirebaseDatabase database;
    private  boolean isAUserInBase;

    public FireBaseServices(){
        database = FirebaseDatabase.getInstance();
        isAUserInBase = false;
    }

    public void storeUser(User user){

        DatabaseReference myRef = database.getReference("users");

        DatabaseReference userChild = myRef.push();
        userChild.setValue(user);

    }

    public void connectionAndStartAcceuilActivity(final String userName, final String password, final Context context){

        DatabaseReference mUserReference = FirebaseDatabase.getInstance().getReference()
                .child("users");



        ValueEventListener postListener = new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();

                while(it.hasNext()){
                    User userInBase = it.next().getValue(User.class);
                    if(isAUser(userInBase, userName,password)){

                        Intent activiteAcceuil = new Intent(context, AccueilActivity.class);


                        activiteAcceuil.putExtra("m2dl.mobe.android.project.miniprojectandroid.Services", userInBase);


                        // Puis on lance l'intent !
                        context.startActivity(activiteAcceuil);


                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        };

        mUserReference.addListenerForSingleValueEvent(postListener);

    }


    private boolean isAUser(User userFind, String userName, String password) {
        return userName.equals(userFind.getEmailUser()) &&
                password.equals(userFind.getPswUser());
    }


}
