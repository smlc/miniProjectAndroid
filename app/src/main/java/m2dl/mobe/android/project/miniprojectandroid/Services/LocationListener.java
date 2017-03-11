package m2dl.mobe.android.project.miniprojectandroid.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Batiment;

/**
 * Created by MarS on 11/03/2017.
 */

public class LocationListener implements android.location.LocationListener {

    // A 100 m du batiment
    private static final float DISTANCE_DU_BATIMENT = 100;
    Location mLastLocation;
    private FirebaseDatabase database;

    public LocationListener(String provider)
    {
        mLastLocation = new Location(provider);
    }


    @Override
    public void onLocationChanged(Location location) {
        //Vérifier si location égal RU


        mLastLocation.set(location);

        //Mise a jour nombre d'occupant du batiement situé à cette location
        fireBatimentBaseListener(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void fireBatimentBaseListener(final Location location) {
        database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("batiments");


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Batiment batiment = dataSnapshot.getValue(Batiment.class);
                System.out.println("Changement location");
                Location batiementLocation = new Location("ME");
                batiementLocation.setLatitude(batiment.getLat());
                batiementLocation.setLongitude(batiment.getLongi());
                System.out.println(location.distanceTo(batiementLocation));
                if(location.distanceTo(batiementLocation) < DISTANCE_DU_BATIMENT){
                    int newNbOccupant = batiment.getNbrOccupant().getNbrOccupJour() + 1;
                    batiment.getNbrOccupant().setNbrOccupJour(newNbOccupant);
                    myRef.child(dataSnapshot.getKey()).setValue(batiment);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
