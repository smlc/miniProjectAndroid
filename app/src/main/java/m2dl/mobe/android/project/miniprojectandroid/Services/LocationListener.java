package m2dl.mobe.android.project.miniprojectandroid.Services;


import android.location.Location;
import android.os.Bundle;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Batiment;
import m2dl.mobe.android.project.miniprojectandroid.Domain.OccupationJour;

/**
 * Created by MarS on 11/03/2017.
 */

public class LocationListener implements android.location.LocationListener {

    // A 100 m du batiment
    private static final float DISTANCE_DU_BATIMENT = 100;
    private static final int TWO_MINUTES = 1000 * 60 * 30;
    Location mLastLocation;
    private FirebaseDatabase database;

    public LocationListener(String provider)
    {
        mLastLocation = new Location(provider);
    }


    @Override
    public void onLocationChanged(Location location) {


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
        final DatabaseReference myRefBatiment = database.getReference("batimentOccuper");


        myRefBatiment.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Batiment batiment = dataSnapshot.getValue(Batiment.class);

                Location batiementLocation = new Location("ME");
                batiementLocation.setLatitude(batiment.getLat());
                batiementLocation.setLongitude(batiment.getLongi());



                if(location.distanceTo(batiementLocation) < DISTANCE_DU_BATIMENT){

                    boolean miseAJourFaite = true;
                    miseAJourFaite = miseAJourOccupantBatimentPourHeureActuel(batiment);

                    if(!miseAJourFaite){
                        //Creer nouvelle element dans la liste
                    }

                   // batiment.getNbrOccupant().setNbrOccupJour(newNbOccupant);
                    myRefBatiment.child(dataSnapshot.getKey()).setValue(batiment);
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

    private boolean miseAJourOccupantBatimentPourHeureActuel(Batiment batiment) {
        boolean trancheHoraireExist = false;
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(new Date());
        int jourDeLaSemaine  = currentDate.get(Calendar.DAY_OF_WEEK);
        int trancheHoraire = (currentDate.get(Calendar.HOUR) / 6) - 1;

        Iterator<OccupationJour> it = batiment.getOccupations().iterator();

        while(it.hasNext()){

            OccupationJour occupationBatiment = it.next();
            if (occupationBatiment.getJourSemaine() == jourDeLaSemaine && occupationBatiment.getTrancheHoraire() == trancheHoraire ) {
                int newNbOccupant = occupationBatiment.getNbrOccupHour() + 1;
                int index = batiment.getOccupations().indexOf(occupationBatiment);
                batiment.getOccupations().get(index).setNbrOccupHour(newNbOccupant);
                trancheHoraireExist = true;
            }

        }
        //retourne false si la mise a jour n'a pas été faite
        return  trancheHoraireExist;

    }


}
