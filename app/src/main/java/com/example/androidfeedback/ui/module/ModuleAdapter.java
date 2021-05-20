package com.example.androidfeedback.ui.module;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder>{
    private Context context;
    ArrayList<ModuleViewModel> listModule;
    private int position;

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements DatePickerDialog.OnDateSetListener{
        private TextView txtModuleID, txtModuleName,txtModuleAdminID, txtModuleStartDate;
        private TextView txtModuleEndDate, txtModuleFBTitle,txtModuleFBStartDate, txtModuleFBEndDate;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtModuleID = itemView.findViewById(R.id.txtModuleID);
            txtModuleName = itemView.findViewById(R.id.txtModuleName);
            txtModuleAdminID = itemView.findViewById(R.id.txtModuleAdminID);
            txtModuleStartDate = itemView.findViewById(R.id.txtModuleStartDate);
            txtModuleEndDate = itemView.findViewById(R.id.txtModuleEndDate);
            txtModuleFBTitle = itemView.findViewById(R.id.txtModuleFBTitle);
            txtModuleFBStartDate = itemView.findViewById(R.id.txtModuleFBStartDate);
            txtModuleFBEndDate = itemView.findViewById(R.id.txtModuleFBEndDate);
        }
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    }
    public ModuleAdapter(Context context, ArrayList<ModuleViewModel> listModule){
        this.context = context;
        this.listModule = listModule;
    }
    @NonNull
    @Override

    public ModuleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_recycler_view_item, parent, false);
        return new ModuleAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ModuleAdapter.ViewHolder holder, final int position){
        ModuleViewModel module = listModule.get(position);
        holder.txtModuleID.setText(String.valueOf(module.getModuleId()));
        holder.txtModuleName.setText(module.getModuleName());
        holder.txtModuleAdminID.setText(String.valueOf(module.getAdminId()));
        holder.txtModuleStartDate.setText(module.getStartDate());
        holder.txtModuleEndDate.setText(module.getEndDate());
        holder.txtModuleFBTitle.setText(module.getFbTitle());
        holder.txtModuleFBStartDate.setText(module.getFbStartDate());
        holder.txtModuleFBEndDate.setText(module.getFbEndDate());
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listModule.size();
    }
}
