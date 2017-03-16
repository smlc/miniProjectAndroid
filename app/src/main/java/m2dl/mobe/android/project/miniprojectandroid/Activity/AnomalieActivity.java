package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Anomalie;
import m2dl.mobe.android.project.miniprojectandroid.Domain.Batiment;
import m2dl.mobe.android.project.miniprojectandroid.Domain.User;
import m2dl.mobe.android.project.miniprojectandroid.R;
import m2dl.mobe.android.project.miniprojectandroid.Services.ExpandableListAdapter;
import m2dl.mobe.android.project.miniprojectandroid.Services.FireBaseServices;

@SuppressWarnings("deprecation")
public class AnomalieActivity extends AppCompatActivity {

    private ListView mListeView;
    private  ArrayList<String> titreAnomalie = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_anomalie);

        //Get user from MaFacActivity
        Intent startIntent = getIntent();
        final User userAnomalie = startIntent.getParcelableExtra(FireBaseServices.USER_INTENT);

        mListeView = (ListView) findViewById(R.id.anomaliesListView);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.activity_liste_anomalie,titreAnomalie);

        mListeView.setAdapter(arrayAdapter);

        List<Anomalie> listeUserAnomalie = userAnomalie.getAnomalies();

        



    }
}
