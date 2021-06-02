package com.example.androidfeedback.ui.statistic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class StatisticAdapter extends   RecyclerView.Adapter<StatisticAdapter.ViewHolder>{
    private Context context;
//    private ArrayList<ClassViewModel> listClass;

    private int position;
    private ArrayList<PieBaseOnTopic> listData;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistic_viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        final PieBaseOnTopic chart = listData.get(position);
        holder.txtTittle.setText(chart.getTopicName());
        holder.pieChart.setUsePercentValues(true);
        final ArrayList<PieChartViewModel> pieDataAPI = chart.getValue();
        ArrayList<PieEntry> label = new ArrayList<>();
        for(int i=0;i<pieDataAPI.size();i++){
            label.add(new PieEntry(pieDataAPI.get(i).getPercent(),
                   ""));
        }
        // set label
        holder.pieChart.setEntryLabelColor(Color.WHITE);
        holder.pieChart.setCenterTextColor(Color.WHITE);
        //create the DATA
        PieDataSet pieDataSet=new PieDataSet(label,"");
        pieDataSet.setSliceSpace(0);
        pieDataSet.setValueTextSize(7);
        //remove hole in center
        holder.pieChart.setDrawHoleEnabled(false);
        holder.pieChart.setDrawCenterText(false);
        holder.pieChart.setDrawEntryLabels(false);
        //add colors to dataSet
        ArrayList<Integer> colors=new ArrayList<>();
        //strongly agree
        colors.add(Color.rgb(254,81,44));
        // agree
        colors.add(Color.rgb(255,102,70));
        //neural
        colors.add(Color.rgb(255,103,69));
        //disagree
        colors.add(Color.rgb(245,144,122));
        //strongly disagree
        colors.add(Color.rgb(247,193,181));
        pieDataSet.setDrawValues(true);
        holder.pieChart.getDescription().setEnabled(false);
        holder.pieChart.setDrawCenterText(true);
        pieDataSet.setColors(colors);
        //add legend to chart

        //create a pieData object
        PieData pieData=new PieData(pieDataSet);
        pieData.setValueTextColor(Color.WHITE);
        holder.pieChart.setData(pieData);
        holder.pieChart.invalidate();
        holder.pieChart.getLegend().setEnabled(false);


    }

    //tạm thời dùng listInt để test
    public StatisticAdapter( ArrayList<PieBaseOnTopic> listData){
        this.listData = listData;

    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTittle ;
        private PieChart pieChart ;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTittle =itemView.findViewById(R.id.txtTitleStatistic);
            pieChart=itemView.findViewById(R.id.pieChartTopic);
        }
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
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