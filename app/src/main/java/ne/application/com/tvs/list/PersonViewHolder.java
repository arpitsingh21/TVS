package ne.application.com.tvs.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ne.application.com.tvs.R;

/**
 * Created by Harikesh on 29/03/2019.
 */
public class PersonViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.location)
    TextView mLocation;
    @BindView(R.id.specialization)
    TextView mSpecialization;
    @BindView(R.id.account)
    TextView mAccount;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.number)
    TextView mNumber;

    public PersonViewHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false));
        ButterKnife.bind(this, itemView);
    }
}
