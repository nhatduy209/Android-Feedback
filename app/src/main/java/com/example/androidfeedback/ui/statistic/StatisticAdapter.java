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
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.ViewHolder>{
    private int position;
    private PieChart pieChart;
    private Context context;
    StatisticDescription description = new StatisticDescription();
    ArrayList<PieBaseOnTopic> listPieData;
    @NonNull
    @Override
    public StatisticAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistic_viewholder, parent, false);
        return new StatisticAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final StatisticAdapter.ViewHolder holder, int position) {
        final PieBaseOnTopic pieData = new PieBaseOnTopic();
        holder.txtTopic.setText(pieData.getData().first);
//        holder.classID.setText(feedback.getTraineeClassID());
//        holder.className.setText(feedback.getTraineeClassName());
//        holder.moduleId.setText(feedback.getTraineeModuleID());
//        String moduleName = "<b>Module Name: </b>" + feedback.getTraineeModuleName();
//        holder.moduleName.setText(android.text.Html.fromHtml(moduleName));
//        holder.endTime.setText(feedback.getTraineeEndTime());
//        if(feedback.isStatus()){
//            holder.status.setText("Complete");
////            holder.btnDoFeedback.setVisibility(View.GONE);
//        }
//        else
//            holder.status.setText("Incomplete");
//        holder.btnDoFeedback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                holder.btnDoFeedback.setVisibility(View.GONE);
////                String AdminID = feedbackes.getAdminId();
//                Intent intent = new Intent(context, DoFeedback.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                intent.putExtra("li",AdminID);
//                context.startActivity(intent);
//            }
//        });
    }


    public StatisticAdapter(Context context, ArrayList<PieBaseOnTopic> listPieData){
        this.context = context;
        this.listPieData = listPieData;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTopic ;
        private PieChart pieChart;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTopic = itemView.findViewById(R.id.txtTitleStatistic);
            pieChart=itemView.findViewById(R.id.pieChart);
        }
    }

    @Override
    public void onViewRecycled(StatisticAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listPieData.size();
    }

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private void drawChart(ArrayList<PieChartViewModel> pieDataAPI) {
        pieChart.setUsePercentValues(true);

        pieDataAPI.add( new PieChartViewModel(1,0.5f));
        pieDataAPI.add( new PieChartViewModel(2,0.5f));

        ArrayList<PieEntry> label = new ArrayList<>();
        for(int i=0;i<pieDataAPI.size();i++){
            label.add(new PieEntry(pieDataAPI.get(i).getPercent(),
                    description.getString(pieDataAPI.get(i).getValue())));
        }

        // set label
        pieChart.setEntryLabelColor(Color.WHITE);
        //create the DATA

        PieDataSet pieDataSet=new PieDataSet(label,"");
        pieDataSet.setSliceSpace(0);
        pieDataSet.setValueTextSize(12);
//        pieDataSet.setLabel("HIHI");
        //remove hole in center
        pieChart.setDrawHoleEnabled(false);
//        pieChart.setDrawCenterText(true);
        pieChart.setDrawCenterText(false);
        pieChart.setDrawEntryLabels(false);
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
//        pieDataSet.setDrawValues(false);
        pieChart.getDescription().setEnabled(false);
        pieDataSet.setColors(colors);
        //add legend to chart
        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
//        legend.setDrawInside(false);
//        legend.setEnabled(true);
        //create a pieData object
        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
