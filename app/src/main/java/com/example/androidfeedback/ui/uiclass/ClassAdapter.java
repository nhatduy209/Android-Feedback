package com.example.androidfeedback.ui.uiclass;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.module.ModuleAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private int position;
    private Context context;
    ArrayList<ClassViewModel> listClass;
    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_recycler_view_item, parent, false);
        return new ClassAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClassAdapter.ViewHolder holder,final int position) {
        ClassViewModel classes = listClass.get(position);
        holder.classId.setText(classes.getClassId());
        holder.className.setText(classes.getClassName());
        holder.startDate.setText(classes.getStartDate());
        holder.endDate.setText(classes.getEndDate());
    }

    public ClassAdapter(Context context, ArrayList<ClassViewModel> listClass){
        this.context = context;
        this.listClass = listClass;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements DatePickerDialog.OnDateSetListener {
        private TextView classId, className,startDate, endDate ;

        public ViewHolder(@NonNull View itemView){
        super(itemView);
            classId = itemView.findViewById(R.id.classIDView);
            className = itemView.findViewById(R.id.className);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    }

    @Override
    public void onViewRecycled(ClassAdapter.ViewHolder holder) {
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
