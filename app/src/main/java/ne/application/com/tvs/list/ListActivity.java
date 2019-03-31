package ne.application.com.tvs.list;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ne.application.com.tvs.DataPojo;
import ne.application.com.tvs.R;
import ne.application.com.tvs.barChart.BarActivity;
import ne.application.com.tvs.detail.DetailActivity;
import ne.application.com.tvs.login.PersonClickListner;

public class ListActivity extends AppCompatActivity implements PersonClickListner {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    DataPojo dataGson;
    PersonAdapter personAdapter;
    @BindView(R.id.barBtn)
    Button mBarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personAdapter = new PersonAdapter(this);
        mRecyclerView.setAdapter(personAdapter);

        setlist();

    }

    private void setlist() {

        Gson g = new Gson();
        dataGson = g.fromJson(getIntent().getStringExtra("data"), DataPojo.class);
        List<PersonModel> personModels = new ArrayList<>();
        for (int i = 0; i < dataGson.getData().size(); i++) {
            PersonModel personModel = new PersonModel();
            personModel.setName(dataGson.getData().get(i).get(0));
            personModel.setAmount(dataGson.getData().get(i).get(5));
            personModel.setDate(dataGson.getData().get(i).get(4));
            personModel.setLocation(dataGson.getData().get(i).get(2));
            personModel.setNumber(dataGson.getData().get(i).get(3));
            personModel.setProfession(dataGson.getData().get(i).get(1));
            personModels.add(personModel);
        }

        personAdapter.addPerson(personModels);
    }

    @Override
    public void onPersonClick(PersonModel personModel) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("name", personModel.name);
        i.putExtra("place", personModel.location);
        i.putExtra("amount", personModel.amount);
        i.putExtra("date", personModel.date);
        i.putExtra("number", personModel.number);
        i.putExtra("role", personModel.profession);
        startActivity(i);
    }

    @OnClick(R.id.barBtn)
    public void onViewClicked() {

        Intent i = new Intent(this, BarActivity.class);
        i.putExtra("data",getIntent().getStringExtra("data"));
        startActivity(i);
    }
}
