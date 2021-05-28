package com.example.androidfeedback.ui.uiclass.trainee;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.uiclass.AddClass;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;

public class TraineeClassAdapter extends RecyclerView.Adapter<TraineeClassAdapter.ViewHolder> {

    private int position;
    private Context context;
    ArrayList<ClassViewModel> listClass;
    @NonNull
    @Override
    public TraineeClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item_trainee, parent, false);
        return new TraineeClassAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TraineeClassAdapter.ViewHolder holder,final int position) {
        final ClassViewModel classes = listClass.get(position);
        holder.classId.setText(classes.getClassId());
        holder.className.setText(classes.getClassName());
        holder.numberTrainee.setText("TraineeNumber");
        holder.btnDetail.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), DetailClassTrainee.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    // if not , error throw
//                intent.putExtra("className",classes.getClassName());
//                intent.putExtra("classId",classes.getClassId());
                context.startActivity(intent);
            }
        });
    }

    public TraineeClassAdapter(Context context, ArrayList<ClassViewModel> listClass){
        this.context = context;
        this.listClass = listClass;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView classId, className,numberTrainee ;
        private Button btnDetail;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            classId = itemView.findViewById(R.id.txtClassIDTrainee);
            className = itemView.findViewById(R.id.txtClassNameTrainee);
            numberTrainee = itemView.findViewById(R.id.txtClassNumberTrainee);
            btnDetail = itemView.findViewById(R.id.btn_classDetailTrainee);
        }
    }

    @Override
    public void onViewRecycled(TraineeClassAdapter.ViewHolder holder) {
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
