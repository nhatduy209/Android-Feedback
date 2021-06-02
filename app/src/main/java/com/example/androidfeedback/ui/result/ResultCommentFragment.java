package com.example.androidfeedback.ui.result;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.statistic.ClassStatisticViewModel;
import com.example.androidfeedback.ui.statistic.ModuleStatisticViewModel;
import com.example.androidfeedback.ui.statistic.PieChartViewModel;
import com.example.androidfeedback.ui.statistic.StatisticDescription;
import com.example.androidfeedback.ui.statistic.StatisticViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResultCommentFragment extends Fragment {
    private Button btnViewComment;
    private Spinner spinnerClass;
    private Spinner spinnerModule;
    private ArrayList<ClassStatisticViewModel> listClass;
    private ArrayList<ModuleStatisticViewModel> listModule;
    private Pair<Integer,String> currentClass;
    private Pair<Integer,String> currentModule;
    private Retrofit retrofit;
    private CallGet callGet;
    private ArrayList<PieChartViewModel> pieData;
    private PieChart pieChart;
    private ArrayList<PieEntry> pieEntries = new ArrayList();
    StatisticDescription description;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_result, container, false);
        retrofit= RetrofitInstance.getClient();
        callGet = retrofit.create(CallGet.class);
        description= new StatisticDescription();

        btnViewComment = root.findViewById(R.id.btnViewComment);
        btnViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ViewComment.class);
                startActivity(intent);
            }
        });

        Button btnShowOverview=root.findViewById(R.id.btnShowOverview);
        btnShowOverview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                TextView className = root.findViewById(R.id.txtClassNameStatistic);
//                className.setText(currentClass.second);
//                TextView moduleName = root.findViewById(R.id.txtModuleNameStatistic);
//                moduleName.setText(currentModule.second);
//                callPieChart();

            }
        });

        listClass= new ArrayList<ClassStatisticViewModel>();
        listModule= new ArrayList<ModuleStatisticViewModel>() ;
        pieData= new ArrayList<PieChartViewModel>();
//        pieChart=root.findViewById(R.id.pieChart);
        // spinner here
        spinnerClass =(Spinner)root.findViewById(R.id.spinnerClass);
        spinnerModule = (Spinner)root.findViewById(R.id.spinnerModule);

        // call api to get list question
//        View v = root.findViewById(R.id.layoutSwipe);
//        v.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
//            public void onSwipeTop() {
//
//                Toast.makeText(getActivity(), "top", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeRight() {
//                Toast.makeText(getActivity(), "right", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeLeft() {
//                TextView className = root.findViewById(R.id.txtClassNameStatistic);
//                className.setText("hihii");
//                Toast.makeText(getActivity(), "left", Toast.LENGTH_SHORT).show();
//            }
//            public void onSwipeBottom() {
//                Toast.makeText(getActivity(), "bottom", Toast.LENGTH_SHORT).show();
//            }
//
//        });
//

        Call<StatisticViewModel>  getSelectListVM = callGet.getSelectList();
        getSelectListVM.enqueue(new Callback<StatisticViewModel>() {
            @Override
            public void onResponse(Call<StatisticViewModel> call, Response<StatisticViewModel> response) {
                String a= response.message();
                listClass=(ArrayList<ClassStatisticViewModel>)response.body().getClasses();
                listModule=(ArrayList<ModuleStatisticViewModel>)response.body().getCourses();
                setSpinnerClass(spinnerClass,listClass);
                setSpinnerModule(spinnerModule,listModule);
            }

            @Override
            public void onFailure(Call<StatisticViewModel> call, Throwable t) {
                String a= t.getMessage();
            }
        });

        return root;
    }
    private void setSpinnerClass( Spinner spinner, ArrayList<ClassStatisticViewModel> listData){
        ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,listData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ClassStatisticViewModel classes = (ClassStatisticViewModel) parent.getSelectedItem();
                currentClass= new Pair<>(classes.getClassID(),
                        classes.getClassName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void setSpinnerModule( Spinner spinner, ArrayList<ModuleStatisticViewModel> listData){
        ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,listData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
//       // When user select a List-Item.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ModuleStatisticViewModel module = (ModuleStatisticViewModel) parent.getSelectedItem();
                currentModule= new Pair<>(module.getModuleID(),
                        module.getModuleName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void callPieChart(){
        Call<ArrayList<PieChartViewModel>> getDataPieChart =callGet.getPieChart(currentClass.first,currentModule.first);
        getDataPieChart.enqueue(new Callback<ArrayList<PieChartViewModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PieChartViewModel>> call, Response<ArrayList<PieChartViewModel>> response) {
                String a= response.message();
                pieData=(ArrayList<PieChartViewModel>)response.body();
                drawChart(pieData);
                int x=1;
            }

            @Override
            public void onFailure(Call<ArrayList<PieChartViewModel>> call, Throwable t) {
                String a= t.getMessage();
            }
        });
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
        legend.setDrawInside(false);
        legend.setEnabled(true);
        //create a pieData object
        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
