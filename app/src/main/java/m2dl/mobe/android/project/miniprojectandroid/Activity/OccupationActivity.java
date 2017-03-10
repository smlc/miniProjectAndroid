package m2dl.mobe.android.project.miniprojectandroid.Activity;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import m2dl.mobe.android.project.miniprojectandroid.R;

/**
 * Created by rottanaly on 3/9/17.
 */

public class OccupationActivity extends AppCompatActivity {

    private Spinner spinner;
    private String selectedItem;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occupation);

        graph = (GraphView) findViewById(R.id.graph);
        final LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 4),
                new DataPoint(6, 3),
                new DataPoint(7, 0),
        });
        final LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 8),
                new DataPoint(6, 7),
                new DataPoint(7, 0)
        });
        graph.addSeries(series1);

        graph.getViewport().setMinX(1);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxX(7);
        graph.getViewport().setMaxY(10);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    int val = (int) value;
                    String jour;
                    switch(val) {
                        case 0: jour = "Dimanche";
                            break;
                        case 1: jour = "Lundi";
                            break;
                        case 2: jour = "Mardi";
                            break;
                        case 3: jour = "Mecredi";
                            break;
                        case 4: jour = "Jeudi";
                            break;
                        case 5: jour = "Vendredi";
                            break;
                        case 6: jour = "Samedi";
                            break;
                        default: jour = "Dimanche";
                            break;
                    }
                    return jour;
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });
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
                        graph.addSeries(series1);
                } else {
                        graph.removeAllSeries();
                        graph.addSeries(series2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}
