package com.example.androidfeedback.ui.statistic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;

public class StatisticAdapter extends   RecyclerView.Adapter<StatisticAdapter.ViewHolder>{
    private Context context;
//    private ArrayList<ClassViewModel> listClass;

    private int position;
    private ArrayList<PieBaseOnTopic> listData;
    @NonNull
    @Override
    public StatisticAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistic_viewholder, parent, false);
        return new StatisticAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  StatisticAdapter.ViewHolder holder, int position) {
        final PieBaseOnTopic comment = listData.get(position);
        holder.txtTittle.setText("hihi");
//        holder.txtTraineeID.setText(comment.getTraineeID());
//        holder.txtContent.setText(comment.getContent());
    }

    //tạm thời dùng listInt để test
    public StatisticAdapter(Context context,  ArrayList<PieBaseOnTopic> listData){
        this.context = context;
        this.listData = listData;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTittle ;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTittle =itemView.findViewById(R.id.txtTitleStatistic);
            int x=1;
//            txtTraineeID = itemView.findViewById(R.id.txtCommentTraineeID);
//            txtContent = itemView.findViewById(R.id.txtCommentContent);
        }
    }

    @Override
    public void onViewRecycled(StatisticAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}