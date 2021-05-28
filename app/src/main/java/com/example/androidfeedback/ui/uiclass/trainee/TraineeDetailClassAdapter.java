package com.example.androidfeedback.ui.uiclass.trainee;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;

public class TraineeDetailClassAdapter extends RecyclerView.Adapter<TraineeDetailClassAdapter.ViewHolder> {

    private int position;
    private Context context;
    ArrayList<ClassViewModel> listClass;
    @NonNull
    @Override
    public TraineeDetailClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_detail_item_trainee, parent, false);
        return new TraineeDetailClassAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TraineeDetailClassAdapter.ViewHolder holder,final int position) {
        final ClassViewModel classes = listClass.get(position);
        holder.traineeNumber.setText(String.valueOf(position));
        holder.traineeID.setText("TraineeID");
        holder.traineeName.setText("TraineeName");
    }

    public TraineeDetailClassAdapter(Context context, ArrayList<ClassViewModel> listClass){
        this.context = context;
        this.listClass = listClass;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView traineeNumber, traineeID,traineeName ;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            traineeNumber= itemView.findViewById(R.id.txtTraineeNumber);
            traineeID = itemView.findViewById(R.id.txtDetailClassTraineeID);
            traineeName = itemView.findViewById(R.id.txtDetailClassTraineeName);
        }
    }

    @Override
    public void onViewRecycled(TraineeDetailClassAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listClass.size();
    }

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
