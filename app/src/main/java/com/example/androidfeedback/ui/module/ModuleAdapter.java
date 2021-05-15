package com.example.androidfeedback.ui.module;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text1, text2,text3, text4,text5, text6,text7, text8;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            text1 = itemView.findViewById(R.id.txtTopicID);
            text2 = itemView.findViewById(R.id.txtTopicName);
            text3 = itemView.findViewById(R.id.txtQuestionID);
            text4 = itemView.findViewById(R.id.txtQuestionContent);
            text5 = itemView.findViewById(R.id.txtAssignmentRegistrationCode);
            text6 = itemView.findViewById(R.id.text6);
            text7 = itemView.findViewById(R.id.text7);
            text8 = itemView.findViewById(R.id.text8);
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
        holder.text1.setText(String.valueOf(module.getModuleId()));
        holder.text2.setText(module.getModuleName());
        holder.text3.setText(String.valueOf(module.getAdminId()));
        holder.text4.setText(module.getStartDate());
        holder.text5.setText(module.getEndDate());
        holder.text6.setText(module.getFbTitle());
        holder.text7.setText(module.getFbStartDate());
        holder.text8.setText(module.getFbEndDate());
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
