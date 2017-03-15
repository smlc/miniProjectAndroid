package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Anomalie;
import m2dl.mobe.android.project.miniprojectandroid.Domain.Batiment;
import m2dl.mobe.android.project.miniprojectandroid.R;

public class AnomalieActivity extends AppCompatActivity {

    private ListView anomaliesListView;
    private ArrayList<Anomalie> anomalieList  = new ArrayList<>();
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_anomalie);

        /*anomaliesListView = (ListView) findViewById(R.id.anomaliesListView);
        final ArrayAdapter<Anomalie> arrayAdapter = new ArrayAdapter<Anomalie>(this,android.R.layout.simple_list_item_1,anomalieList);
        DatabaseReference myRef = database.getReference("anomalies");*/


    }
}
