package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import m2dl.mobe.android.project.miniprojectandroid.Domain.Batiment;
import m2dl.mobe.android.project.miniprojectandroid.Domain.OccupationJour;
import m2dl.mobe.android.project.miniprojectandroid.R;

/**
 * Created by rottanaly on 3/9/17.
 */

public class OccupationActivity extends AppCompatActivity {


    private Spinner spinner;
    private String selectedItem;
    private GraphView graph;

    private RadioGroup radioGroup;
    private RadioButton radioBtn1, radioBtn2;
    private TextView textView;
    private String RU;
    private DefaultLabelFormatter labelJour, labelHeure;

    private static int jour = 7;
    private static int heure = 6;
    private List<Batiment> batimentList = MainActivity.batimentList;
    private int[] jourCountRU1 = new int[jour];
    private int[] jourCountRU2 = new int[jour];
    private int[] heureCountRU1 = new int[heure];
    private int[] heureCountRU2 = new int[heure];
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occupation);
        //getDataFromFireBase();
        // Nb occupants RU1 & RU2 par jour
        for (int i=0; i<jour; i++) {
            jourCountRU1[i] = jourCountRU("RU1", i);
            jourCountRU2[i] = jourCountRU("RU2", i);
        }

        // Nb occupants RU1 & RU2 par heure
        for (int i=0; i<heure; i++) {
            heureCountRU1[i] = heureCountRU("RU1", i);
            heureCountRU2[i] = heureCountRU("RU2", i);
        }

        graph = (GraphView) findViewById(R.id.graph);

        final LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, jourCountRU1[0]),
                new DataPoint(2, jourCountRU1[1]),
                new DataPoint(3, jourCountRU1[2]),
                new DataPoint(4, jourCountRU1[3]),
                new DataPoint(5, jourCountRU1[4]),
                new DataPoint(6, jourCountRU1[5]),
                new DataPoint(7, jourCountRU1[6]),
        });
        final LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, jourCountRU2[0]),
                new DataPoint(2, jourCountRU2[1]),
                new DataPoint(3, jourCountRU2[2]),
                new DataPoint(4, jourCountRU2[3]),
                new DataPoint(5, jourCountRU2[4]),
                new DataPoint(6, jourCountRU2[5]),
                new DataPoint(7, jourCountRU2[6]),
        });
        final LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, heureCountRU1[0]),
                new DataPoint(1, heureCountRU1[1]),
                new DataPoint(2, heureCountRU1[2]),
                new DataPoint(3, heureCountRU1[3]),
                new DataPoint(4, heureCountRU1[4]),
                new DataPoint(5, heureCountRU1[5]),
        });
        final LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, heureCountRU2[0]),
                new DataPoint(1, heureCountRU2[1]),
                new DataPoint(2, heureCountRU2[2]),
                new DataPoint(3, heureCountRU2[3]),
                new DataPoint(4, heureCountRU2[4]),
                new DataPoint(5, heureCountRU2[5]),
        });
        graph.addSeries(series1);

        graph.getViewport().setMinX(0);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxX(7);
        graph.getViewport().setMaxY(100);
        graph.getViewport().setScalable(true);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);

        labelHeure = new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    int val = (int) value;
                    String heure;
                    switch(val) {
                        case 0: heure = "0h";
                            break;
                        case 1: heure = "4h";
                            break;
                        case 2: heure = "8h";
                            break;
                        case 3: heure = "12h";
                            break;
                        case 4: heure = "16h";
                            break;
                        case 5: heure = "20h";
                            break;
                        default: heure = "0h";
                            break;
                    }
                    return heure;
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        };

        labelJour = new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    int val = (int) value;
                    String jour;
                    switch(val) {
                        case 0: jour = "Dim";
                            break;
                        case 1: jour = "Lun";
                            break;
                        case 2: jour = "Mar";
                            break;
                        case 3: jour = "Mer";
                            break;
                        case 4: jour = "Jeu";
                            break;
                        case 5: jour = "Ven";
                            break;
                        case 6: jour = "Sam";
                            break;
                        default: jour = "Dim";
                            break;
                    }
                    return jour;
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        };

        graph.getGridLabelRenderer().setLabelFormatter(labelJour);

        RU = "RU1";
        selectedItem = "Jour";

        textView = (TextView) findViewById(R.id.tvRU);
        textView.setText("Occupation de " + RU);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),
                        "OnItemSelectedListener : " + selectedItem,
                        Toast.LENGTH_SHORT).show();
                if (selectedItem.equals("Jour")) {
                    graph.removeAllSeries();
                    if (RU.equals("RU1")) {
                        graph.addSeries(series1);
                    } else if (RU.equals("RU2")) {
                        graph.addSeries(series2);
                    }
                    graph.getGridLabelRenderer().setLabelFormatter(labelJour);

                } else {
                    graph.removeAllSeries();
                    if (RU.equals("RU1")) {
                        graph.addSeries(series3);
                    } else if (RU.equals("RU2")) {
                        graph.addSeries(series4);
                    }
                    graph.getGridLabelRenderer().setLabelFormatter(labelHeure);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.radioRU);
        radioBtn1 = (RadioButton) findViewById(R.id.radioBtn1);
        radioBtn2 = (RadioButton) findViewById(R.id.radioBtn2);
        radioBtn1.setChecked(true);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                graph.removeAllSeries();
                if (checkedId == R.id.radioBtn1) {
                    RU = "RU1";
                    textView.setText("Occupation de " + RU);
                    if (selectedItem.equals("Jour")) {
                        graph.addSeries(series1);
                        graph.getGridLabelRenderer().setLabelFormatter(labelJour);
                    } else if (selectedItem.equals("Heure")) {
                        graph.addSeries(series3);
                        graph.getGridLabelRenderer().setLabelFormatter(labelHeure);
                    }

                } else if (checkedId == R.id.radioBtn2) {
                    RU = "RU2";
                    textView.setText("Occupation de " + RU);
                    graph.removeAllSeries();
                    if (selectedItem.equals("Jour")) {
                        graph.addSeries(series2);
                        graph.getGridLabelRenderer().setLabelFormatter(labelJour);
                    } else if (selectedItem.equals("Heure")) {
                        graph.addSeries(series4);
                        graph.getGridLabelRenderer().setLabelFormatter(labelHeure);
                    }
                }
            }
        });

    }

    private int jourCountRU(String RU, int jour) {
        int count = 0;
        for (int i = 0; i< batimentList.size(); i++) {
            if (batimentList.get(i).getNom().equals(RU)) {

                for(OccupationJour occupationBatiment : batimentList.get(i).getOccupations()){
                    if (occupationBatiment.getJourSemaine() == jour) {
                        count+= occupationBatiment.getJourSemaine();
                    }
                }


            }
        }
        System.out.println("Count : " + count);
        return count;
    }

    private int heureCountRU(String RU, int hour) {
        int count = 0;
        for (int i = 0; i< batimentList.size(); i++) {
            if (batimentList.get(i).getNom().equals(RU)) {
                for(OccupationJour occupationBatiment : batimentList.get(i).getOccupations()){
                    if (occupationBatiment.getNbrOccupHour() == hour) {
                        count+= occupationBatiment.getNbrOccupHour();
                    }
                }
            }
        }
        System.out.println("Count : " + count);
        return count;
    }


    private void getDataFromFireBase() {
        database = FirebaseDatabase.getInstance();

        //TODO remove
       /* DatabaseReference myRef2 = database.getReference("batimentOccuper");

        DatabaseReference userChild = myRef2.push();
        userChild.setValue(new Batiment("RU1",  "Service", 43.562252, 1.463187));*/
        DatabaseReference myRef = database.getReference("batimentOccuper");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Batiment occupationJourBatiment = dataSnapshot.getValue(Batiment.class);
                batimentList.add(occupationJourBatiment);
                for (int i=0; i<jour; i++) {
                    jourCountRU1[i] = jourCountRU("RU1", i);
                    jourCountRU2[i] = jourCountRU("RU2", i);
                }

                // Nb occupants RU1 & RU2 par heure
                for (int i=0; i<heure; i++) {
                    heureCountRU1[i] = heureCountRU("RU1", i);
                    heureCountRU2[i] = heureCountRU("RU2", i);
                }

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
