package m2dl.mobe.android.project.miniprojectandroid.Activity;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;


import android.widget.ExpandableListView;

import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Batiment;
import m2dl.mobe.android.project.miniprojectandroid.Domain.OccupationJour;
import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.ExpandableListAdapter;
import m2dl.mobe.android.project.miniprojectandroid.Services.LocationListenerServices;

public class PositionActivity extends FragmentActivity {

    private ListView batimentListView;

    private ArrayList<Batiment> batimentsList  = new ArrayList<>();
    private List<String> listDataHeader;
    //List<Batiment> listDataChild;
    private HashMap<String, List<Batiment>> listDataChild;
    private FirebaseDatabase database;
    private ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);


        //Lancement du service pour mettre à jour la position de l'utilisateur
        startService(new Intent(PositionActivity.this, LocationListenerServices.class));

        expListView = (ExpandableListView) findViewById(R.id.expandableList);

      //  batimentListView = (ListView) findViewById(R.id.batimentListView);
        prepareListData();

        final ArrayAdapter<Batiment> arrayAdapter = new ArrayAdapter<Batiment>(this,android.R.layout.simple_list_item_1,batimentsList);

        final ExpandableListAdapter listAdapter = new ExpandableListAdapter(this,listDataHeader,listDataChild);

        expListView.setAdapter(listAdapter);

        fireBaseListener(listAdapter);

        expListListener(listAdapter);


    }

    private void expListListener(final ExpandableListAdapter listAdapter) {
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Batiment batiment = (Batiment) listAdapter.getChild(groupPosition,childPosition);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("google.navigation:q=" + batiment.getLat()+","+batiment.getLongi()+"&mode=w"));

                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

                return true;
            }
        });
    }

    private void fireBaseListener(final ExpandableListAdapter listAdapter) {
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("batiments");


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Batiment batiment = dataSnapshot.getValue(Batiment.class);

                listDataChild.get(batiment.getTypeBatiment()).add(batiment);
                listAdapter.notifyDataSetChanged();

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

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();


        listDataHeader.add("Services");
        listDataHeader.add("Administration");

        listDataChild.put(listDataHeader.get(0), new ArrayList<Batiment>()); // Header, Child data
        listDataChild.put(listDataHeader.get(1), new ArrayList<Batiment>());


    }

}
