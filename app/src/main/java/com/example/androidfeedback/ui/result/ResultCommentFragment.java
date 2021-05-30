package com.example.androidfeedback.ui.result;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.statistic.ClassStatisticViewModel;
import com.example.androidfeedback.ui.statistic.ModuleStatisticViewModel;
import com.example.androidfeedback.ui.statistic.PieChartViewModel;
import com.example.androidfeedback.ui.statistic.StatisticViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

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
    PieDataSet pieDataSet;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_result, container, false);
        retrofit= RetrofitInstance.getClient();
        callGet = retrofit.create(CallGet.class);

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
            @Override
            public void onClick(View v) {
                TextView className = root.findViewById(R.id.txtClassNameStatistic);
                className.setText(currentClass.second);
                TextView moduleName = root.findViewById(R.id.txtModuleNameStatistic);
                moduleName.setText(currentModule.second);
                callPieChart();
                samplePieChart();
            }
        });

        listClass= new ArrayList<ClassStatisticViewModel>();
        listModule= new ArrayList<ModuleStatisticViewModel>() ;
        pieData= new ArrayList<PieChartViewModel>();
        pieChart=root.findViewById(R.id.pieChart);
        // spinner here
        spinnerClass =(Spinner)root.findViewById(R.id.spinnerClass);
        spinnerModule = (Spinner)root.findViewById(R.id.spinnerModule);

        // call api to get list question


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
            }

            @Override
            public void onFailure(Call<ArrayList<PieChartViewModel>> call, Throwable t) {
                String a= t.getMessage();
            }
        });
    }
    private void drawPieChart(){
        pieEntries.add(new PieEntry(50,1));
        pieEntries.add(new PieEntry(50,2));

        PieDataSet pieDataSet = new PieDataSet(pieEntries,"DashBoard");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        pieDataSet.setValueTextSize(16);
        PieData pieDatai = new PieData(pieDataSet);

//        pieChart = pieChart.findViewById(R.id.pieChart);

        pieChart.setData(pieDatai);

        //new
        //remove hole in center
        pieChart.setDrawHoleEnabled(false);
        pieChart.setDrawCenterText(true);

        //new
        pieChart.setCenterText("");
        pieChart.setDrawCenterText(true);

        // set label
        pieChart.setEntryLabelColor(Color.BLACK);

        // remove legend
        pieChart.getLegend().setEnabled(false);

        // chart title
        pieChart.getDescription().setEnabled(false);
    }
    private void samplePieChart(){
        pieEntries.add(new PieEntry(50f,2));
        pieEntries.add(new PieEntry(50f,1));

        pieDataSet= new PieDataSet(pieEntries,"");
        PieData pieDatai = new PieData(pieDataSet);
        pieChart.setData(pieDatai);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setSliceSpace(1f);

    }
}
