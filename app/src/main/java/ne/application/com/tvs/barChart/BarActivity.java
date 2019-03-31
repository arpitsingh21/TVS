package ne.application.com.tvs.barChart;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import ne.application.com.tvs.DataPojo;
import ne.application.com.tvs.R;
import ne.application.com.tvs.Utilities;
import okhttp3.internal.Util;

public class BarActivity extends AppCompatActivity {

    @BindView(R.id.chart_bar)
    BarChart barChart;
    private DataPojo dataGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        ButterKnife.bind(this);
        initializebarGraph();
    }

    private void initializebarGraph() {
        barChart.setTouchEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.setHighlightPerTapEnabled(false);
        barChart.getXAxis().setEnabled(false);
        //barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getXAxis().setDrawGridLines(true);
        barChart.getAxisLeft().setEnabled(true);
        //barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.setDrawGridBackground(true);
        setdata();
    }

    public void setdata(){
        Gson g = new Gson();
        dataGson = g.fromJson(getIntent().getStringExtra("data"), DataPojo.class);
        List<BarEntry> entries = new ArrayList<>();

        for (int i=0 ; i <10;i++){
            entries.add(new BarEntry((float)i, Float.parseFloat(Utilities.getMoney(dataGson.getData().get(i).get(5))), dataGson.getData().get(i).get(0)));
        }
//        entries.add(new BarEntry(1f, Float.parseFloat(dataGson.getData().get(0).get(2)), "Total"));
//        entries.add(new BarEntry(2f, Float.parseFloat(dataGson.getData().get(0).get(2)), "Total"));
//        entries.add(new BarEntry(2f, Float.parseFloat(dataGson.getData().get(0).get(2)), "Total"));
//        entries.add(new BarEntry(2f, Float.parseFloat(dataGson.getData().get(0).get(2)), "Total"));
//        entries.add(new BarEntry(2f, Float.parseFloat(dataGson.getData().get(0).get(2)), "Total"));
//        entries.add(new BarEntry(2f, Float.parseFloat(dataGson.getData().get(0).get(2)), "Total"));
//        entries.add(new BarEntry(2f, Float.parseFloat(dataGson.getData().get(0).get(2)), "Total"));
//        entries.add(new BarEntry(2f, Float.parseFloat(dataGson.getData().get(0).get(2)), "Total"));
//        entries.add(new BarEntry(2f, Float.parseFloat(dataGson.getData().get(0).get(2)), "Total"));


        BarDataSet set = new BarDataSet(entries, "Salaries in thousand dollars");
        set.setColors(new int[]{R.color.colorPrimary, R.color.black, R.color.colorAccent},this);
        set.setValueTextSize(14f);
        set.setFormSize(10f);
        BarData data = new BarData(set);
        data.setDrawValues(true);
        //data.setBarWidth(0.9f); // set custom bar width
        barChart.setData(data);
    }
}
