package m2dl.mobe.android.project.miniprojectandroid.Services;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

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

    public void connection(final User user){

        DatabaseReference mUserReference = FirebaseDatabase.getInstance().getReference()
                .child("users");


        ValueEventListener postListener = new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> it = dataSnapshot.getChildren().iterator();

                while(it.hasNext()){
                    User userFind = it.next().getValue(User.class);
                    if(user.getEmailUser().equals(userFind.getEmailUser()) &&
                            user.getPswUser().equals(userFind.getPswUser())){

                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        };

        mUserReference.addValueEventListener(postListener);

    }

}
