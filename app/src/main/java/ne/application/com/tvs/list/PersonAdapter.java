package ne.application.com.tvs.list;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import ne.application.com.tvs.login.PersonClickListner;

import static java.text.DateFormat.getDateInstance;

/**
 * Created by Harikesh on 29/03/2019.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private final PersonClickListner personClickListner;
    private List<PersonModel> persons;

    public PersonAdapter(PersonClickListner personClickListner) {
        this.personClickListner = personClickListner;
        persons = new ArrayList<>();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final PersonViewHolder personViewHolder = new PersonViewHolder(parent);
        personViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personClickListner.onPersonClick(persons.get(personViewHolder.getAdapterPosition()));
            }
        });
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.mAccount.setText(persons.get(position).amount);
        holder.mName.setText(persons.get(position).name);
        holder.mDate.setText(persons.get(position).date);
        holder.mLocation.setText(persons.get(position).location);
        holder.mSpecialization.setText(persons.get(position).profession);
        holder.mNumber.setText(persons.get(position).number);
        // holder.textViews.get(0).setText(deals.get(holder.getAdapterPosition()).dealTitle);
    }

   public void addPerson(List<PersonModel> personModels) {
        persons.addAll(personModels);
        notifyItemInserted(persons.size());
        Log.d("Size = ", "" + persons.size());
    }


    @Override
    public int getItemCount() {
        return persons != null ? persons.size() : 0;
    }
}