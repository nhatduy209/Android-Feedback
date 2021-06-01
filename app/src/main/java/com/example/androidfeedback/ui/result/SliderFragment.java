package com.example.androidfeedback.ui.result;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.statistic.ClassStatisticViewModel;
import com.example.androidfeedback.ui.statistic.ModuleStatisticViewModel;
import com.example.androidfeedback.ui.statistic.PieChartViewModel;
import com.example.androidfeedback.ui.statistic.StatisticDescription;
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

public class SliderFragment extends Fragment {
    private ViewPager mSliderViewPager;
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
    private PieChart pieChart2;
    private ArrayList<PieEntry> pieEntries = new ArrayList();
    StatisticDescription description;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_result, container, false);

        retrofit= RetrofitInstance.getClient();
        callGet = retrofit.create(CallGet.class);
        description= new StatisticDescription();


        btnViewComment = (Button)root.findViewById(R.id.btnViewComment);
        btnViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),ViewComment.class);
//                startActivity(intent);
//                mSliderViewPager.setVisibility(View.GONE);

                Toast.makeText(getActivity(), "On click", Toast.LENGTH_SHORT).show();
                ViewCommentFragment fragment2 = new ViewCommentFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, (Fragment)fragment2);
                fragmentTransaction.commit();
            }
        });
        mSliderViewPager= root.findViewById(R.id.slideViewPager);

        Button btnShowOverview= root.findViewById(R.id.btnShowOverview);
        btnShowOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TextView className = root.findViewById(R.id.txtClassNameStatistic);
//                className.setText(currentClass.second);
//                TextView moduleName = root.findViewById(R.id.txtModuleNameStatistic);
//                moduleName.setText(currentModule.second);

//

                pieChart= root.findViewById(R.id.pieChart);
                createSliderPage();
                drawChart(pieData);
            }
        });

        listClass= new ArrayList<ClassStatisticViewModel>();
        listModule= new ArrayList<ModuleStatisticViewModel>() ;
        pieData= new ArrayList<PieChartViewModel>();

        // spinner here
//        spinnerClass =(Spinner)root.findViewById(R.id.spinnerClass);
//        spinnerModule = (Spinner)root.findViewById(R.id.spinnerModule);

//        Call<StatisticViewModel> getSelectListVM = callGet.getSelectList();
//        getSelectListVM.enqueue(new Callback<StatisticViewModel>() {
//            @Override
//            public void onResponse(Call<StatisticViewModel> call, Response<StatisticViewModel> response) {
//                String a= response.message();
//                listClass=(ArrayList<ClassStatisticViewModel>)response.body().getClasses();
//                listModule=(ArrayList<ModuleStatisticViewModel>)response.body().getCourses();
//                setSpinnerClass(spinnerClass,listClass);
//                setSpinnerModule(spinnerModule,listModule);
//            }
//
//            @Override
//            public void onFailure(Call<StatisticViewModel> call, Throwable t) {
//                String a= t.getMessage();
//            }
//        });

        return root;
    }
    private void createSliderPage( ){
        int layouts[]= {
                R.layout.fragment_slider2,
                R.layout.fragment_slider3,
        };

        mSliderViewPager.setAdapter(new PagerAdapter(){
            @NonNull
            @Override
            public Object instantiateItem(@NonNull  ViewGroup container, int position) {
                LayoutInflater layoutInflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view= layoutInflater.inflate(layouts[position],container,false);
                container.addView(view);
                return view;
            }

            @Override
            public int getCount() {
                return layouts.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view,@NonNull Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(@NonNull  ViewGroup container, int position, @NonNull  Object object) {
                View view =(View) object;
                container.removeView(view);
            }
        });
        mSliderViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getActivity(), "onPageSelected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
//                drawChart(pieData);
                int i=1;
            }

            @Override
            public void onFailure(Call<ArrayList<PieChartViewModel>> call, Throwable t) {
                String a= t.getMessage();
                int aa=1;
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
        pieDataSet.setDrawValues(false);
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

//        View root2 = inflater.inflate(R.layout.fragment_result, container, false);
        PieChart pie = getActivity().findViewById(R.id.pieChart2);
        pie=pieChart;
        int x=1;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "SliderFragment: onCreate", Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStart() {
        Toast.makeText(getActivity(), "SliderFragment: onStart", Toast.LENGTH_SHORT).show();

        super.onStart();
    }

    @Override
    public void onResume() {
        Toast.makeText(getActivity(), "SliderFragment: onResume", Toast.LENGTH_SHORT).show();

        super.onResume();
    }

    @Override
    public void onPause() {
        Toast.makeText(getActivity(), "SliderFragment: onPause", Toast.LENGTH_SHORT).show();

        super.onPause();
    }

    @Override
    public void onStop() {
        Toast.makeText(getActivity(), "SliderFragment: onStop", Toast.LENGTH_SHORT).show();

        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Toast.makeText(getActivity(), "SliderFragment: onDestroyView", Toast.LENGTH_SHORT).show();

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getActivity(), "SliderFragment: onDestroy", Toast.LENGTH_SHORT).show();

        super.onDestroy();
    }

}
