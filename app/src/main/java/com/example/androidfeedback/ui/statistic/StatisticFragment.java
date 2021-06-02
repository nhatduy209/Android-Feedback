package com.example.androidfeedback.ui.statistic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidfeedback.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

public class StatisticFragment extends Fragment {
    PieChart pieChart;
    ArrayList<PieEntry> pieEntries = new ArrayList();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,container,false);

        pieChart = view.findViewById(R.id.pieChart);
        setChart();
        return view;
    }
    private void setChart(){
        // set data
//        final NoteDao noteDao = database.noteDao();
//        final List<Note> ListNote  =  noteDao.getAll(session.getIdAccount());
//        final StatusDaoClass statusDao = database.statusDaoClass();
//        final List<StatusModel> ListStatus  =  statusDao.getAllData(session.getIdAccount());
//        List<DashBoard>  ListDashBoard = new ArrayList<>();
//        for (StatusModel status : ListStatus){
//            int count = 0;
//            for (Note note  : ListNote) {
//                if(note.status.equals(status.getName())){
//                    count++;
//                }
//            }
//            DashBoard dashboard = new DashBoard(status.getName() , count);
//            ListDashBoard.add(dashboard);
//        }
//
//
//        // for each items in dashboard
//        for (int i =0; i < ListDashBoard.size();i++){
//            String status = ListDashBoard.get(i).getStatus();
//            int countStatus = ListDashBoard.get(i).getCount();
//            if(countStatus > 0 ){
//                float Rate = (float)countStatus/ ListNote.size();
//                float Percentage =  (float)Rate * 100;
//                pieEntries.add(new PieEntry(Percentage,status));
//            }
//        }
//
//
//        PieDataSet pieDataSet = new PieDataSet(pieEntries,"DashBoard");
//        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
//        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setValueTextSize(16);
//        PieData pieData = new PieData(pieDataSet);
//
//        pieChart = pieChart.findViewById(R.id.pieChart);
//
//        pieChart.setData(pieData);
//
//        //new
//        //remove hole in center
//        pieChart.setDrawHoleEnabled(false);
//        pieChart.setDrawCenterText(true);
//
//        //new
//        pieChart.setCenterText("");
//        pieChart.setDrawCenterText(true);
//
//        // set label
//        pieChart.setEntryLabelColor(Color.BLACK);
//
//        // remove legend
//        pieChart.getLegend().setEnabled(false);
//
//        // chart title
//        pieChart.getDescription().setEnabled(false);

        //pieChart.setUsePercentValues(true);
    }
}
