package com.project.m_ajira.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.project.m_ajira.Model.LabourModel;
import com.project.m_ajira.R;

public class LabourAdapter extends FirebaseRecyclerAdapter<LabourModel,LabourAdapter.LabourHolder> {
    Context context;
    public LabourAdapter(@NonNull FirebaseRecyclerOptions<LabourModel> options, Context context) {
        super(options);
        this.context= context;
    }

    @Override
    protected void onBindViewHolder(@NonNull LabourHolder holder, int i, @NonNull LabourModel model) {
        getRef(i).getKey();

        holder.dateC.setText(model.getDateCreated());
        holder.rates.setText(model.getRate());
        holder.title.setText(model.getTile());
        holder.location.setText(model.getPlace());

    }

    @NonNull
    @Override
    public LabourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LabourHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.labour_item, parent, false));

    }

    public class LabourHolder extends RecyclerView.ViewHolder {
        TextView title, rates, location,dateC;
        public LabourHolder(@NonNull View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.desc_txt);
            rates =itemView.findViewById(R.id.rates_txt);
            location =itemView.findViewById(R.id.textView14);
            dateC =itemView.findViewById(R.id.textView16);

        }
    }
}
