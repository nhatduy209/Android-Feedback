package com.example.androidfeedback.ui.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {

    private Context context;
    ArrayList<AssignmentModel> listAssignment;
    private int position;

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public AssignmentAdapter(Context context, ArrayList<AssignmentModel> listAssignment){
        this.context = context;
        this.listAssignment = listAssignment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_recyler_view_item, parent, false);
        return new AssignmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AssignmentModel assignment = listAssignment.get(position);
        holder.txtId.setText(assignment.getModuleName());
        holder.txtModuleName.setText(assignment.getModuleName());
        holder.txtClassName.setText(assignment.getClassName());
        holder.txtTrainerName.setText(assignment.getTrainerName());
        holder.txtRegistrationCode.setText(assignment.getRegistrationCode());


    }

    @Override
    public int getItemCount() {
        return listAssignment.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvId, tvModuleName, tvClassName, tvTrainerName, tvRegistrationCode;
        private TextView  txtId, txtModuleName, txtClassName, txtTrainerName, txtRegistrationCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             tvId = itemView.findViewById(R.id.tvId);
            tvModuleName = itemView.findViewById(R.id.tvModuleName);
            tvClassName = itemView.findViewById(R.id.tvClassName);
            tvTrainerName = itemView.findViewById(R.id.tvTrainerName);
            tvRegistrationCode = itemView.findViewById(R.id.tvRegistrationCode);



            txtId =itemView.findViewById(R.id.txtAssId);
            txtModuleName =itemView.findViewById(R.id.txtAssModuleName);
            txtClassName =itemView.findViewById(R.id.txtAssClassName);
            txtTrainerName =itemView.findViewById(R.id.txtAssTrainerName);
            txtRegistrationCode =itemView.findViewById(R.id.txtRegistrationCode);

        }
    }

}
