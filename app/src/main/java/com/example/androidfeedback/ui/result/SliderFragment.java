package com.example.androidfeedback.ui.result;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.statistic.ClassStatisticViewModel;
import com.example.androidfeedback.ui.statistic.ModuleStatisticViewModel;
import com.example.androidfeedback.ui.statistic.PieBaseOnTopic;
import com.example.androidfeedback.ui.statistic.PieChartViewModel;
import com.example.androidfeedback.ui.statistic.StatisticAdapter;
import com.example.androidfeedback.ui.statistic.StatisticDescription;
import com.example.androidfeedback.ui.statistic.StatisticDetail;
import com.example.androidfeedback.ui.statistic.StatisticViewModel;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SliderFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewTopic;
    private RecyclerView recyclerViewQuestion;
    private StatisticAdapter statisticAdapter;
    private ViewPager mSliderViewPager;
    private Button btnViewComment;
    private Context context;
    private ArrayList<StatisticDetail> listData;
    private Spinner spinnerClass;
    private Spinner spinnerModule;
    private ArrayList<ClassStatisticViewModel> listClass;
    private ArrayList<ModuleStatisticViewModel> listModule;
    private Pair<Integer,String> currentClass;
    private Pair<Integer,String> currentModule;
    private Retrofit retrofit;
    private CallGet callGet;
    private ArrayList<PieChartViewModel> pieData;
    private ArrayList<PieBaseOnTopic> pieDataTopic;
    private PieChart pieChart;
    private ArrayList<PieEntry> pieEntries = new ArrayList();
    StatisticDescription description;
    private View root;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = inflater.inflate(R.layout.fragment_result, container, false);

        retrofit= RetrofitInstance.getClient();
        callGet = retrofit.create(CallGet.class);
        description= new StatisticDescription();
        recyclerViewQuestion = root.findViewById(R.id.recyclerCommentResult);
        pieDataTopic= new ArrayList<>();
        recyclerView = root.findViewById(R.id.recyclerCommentResult);
        listData= new ArrayList<>();
        mSliderViewPager= root.findViewById(R.id.slideViewPager);

        listClass= new ArrayList<ClassStatisticViewModel>();
        listModule= new ArrayList<ModuleStatisticViewModel>() ;
        pieData= new ArrayList<PieChartViewModel>();
        // spinner here
        spinnerClass =(Spinner)root.findViewById(R.id.spinnerClass);
        spinnerModule = (Spinner)root.findViewById(R.id.spinnerModule);

        btnViewComment = (Button)root.findViewById(R.id.btnViewComment);
        btnViewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSliderViewPager.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "On click", Toast.LENGTH_SHORT).show();
                ViewCommentFragment fragment2 = new ViewCommentFragment();
                FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, (Fragment)fragment2,"VIEW_COMMENT");
                fragmentTransaction.commit();
            }
        });

        Button btnShowOverview= root.findViewById(R.id.btnShowOverview);
        btnShowOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSliderViewPager.setVisibility(View.VISIBLE);
                createSliderPage();

                TextView className = root.findViewById(R.id.txtClassNameStatistic);
                className.setText(currentClass.second);
                TextView moduleName =root.findViewById(R.id.txtModuleNameStatistic);
                moduleName.setText(currentModule.second);
                List fragments = getActivity().getSupportFragmentManager().getFragments();
                Fragment mCurrentFragment = (Fragment) fragments.get(fragments.size()-1);
                if(fragments.size()==2){
                    getActivity().getSupportFragmentManager().beginTransaction().remove(mCurrentFragment).commit();
                }

                pieChart= root.findViewById(R.id.pieChart);

                callPieChart();
                callPieChartBaseTopic(root);
            }
        });

        // get session
        SharedPreferences pref = getActivity().getSharedPreferences("GetSession", Context.MODE_PRIVATE);
        String userId = pref.getString("userId", "");
        final String role  = pref.getString("role", "");
        switch (role){
            case "Admin":
                Call<StatisticViewModel> getSelectListVM = callGet.getSelectList();
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
                break;
            case "Trainer":
                Call<StatisticViewModel> getSelectListForTrainer = callGet.getSelectListForTrainer(userId);
                getSelectListForTrainer.enqueue(new Callback<StatisticViewModel>() {
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
                break;
        }

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
                TextView c = root.findViewById(R.id.txtHideClassID);
                c.setText(String.valueOf(classes.getClassID()));
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
                TextView m = root.findViewById(R.id.txtHideModuleID);
                m.setText(String.valueOf(module.getModuleID()));
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
                int i=1;
            }

            @Override
            public void onFailure(Call<ArrayList<PieChartViewModel>> call, Throwable t) {
                String a= t.getMessage();
                int aa=1;
            }
        });
    }
    private void callPieChartBaseTopic(View root){
        Call<ArrayList<PieBaseOnTopic>> getDataPieChart =callGet.getPieChartBaseOnTopic(currentClass.first,currentModule.first);
        getDataPieChart.enqueue(new Callback<ArrayList<PieBaseOnTopic>>() {
            @Override
            public void onResponse(Call<ArrayList<PieBaseOnTopic>> call, Response<ArrayList<PieBaseOnTopic>> response) {
                String a= response.message();
                pieDataTopic=(ArrayList<PieBaseOnTopic>)response.body();
                reload(pieDataTopic,root);
                int i=1;
            }

            @Override
            public void onFailure(Call<ArrayList<PieBaseOnTopic>> call, Throwable t) {
                String a= t.getMessage();
                int aa=1;
            }
        });
    }
    private void drawChart(ArrayList<PieChartViewModel> pieDataAPI) {
        pieChart.setUsePercentValues(true);

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
        pieDataSet.setValueTextSize(10);
        //remove hole in center
        //remove hole in center
        pieChart.setDrawHoleEnabled(false);
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
        pieDataSet.setDrawValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawCenterText(true);
        pieDataSet.setColors(colors);
        //add legend to chart
        Legend legend=pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(true);
        legend.setEnabled(true);
        //create a pieData object
        PieData pieData=new PieData(pieDataSet);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
    public void reload(ArrayList<PieBaseOnTopic> listData, View view){
        recyclerViewTopic=root.findViewById(R.id.recyclerStatisticResultView);
        StatisticAdapter statisticAdapter = new StatisticAdapter(listData);
        recyclerViewTopic.setLayoutManager(new LinearLayoutManager(root.getContext(),RecyclerView.HORIZONTAL,false));
        recyclerViewTopic.setAdapter(statisticAdapter);

    }

    private void getDetail(){
        Call<ArrayList<StatisticDetail>> getStatisticAnswer = callGet.getStatisticAnswer(currentClass.first,currentModule.first);
        getStatisticAnswer.enqueue(new Callback<ArrayList<StatisticDetail>>() {
            @Override
            public void onResponse(Call<ArrayList<StatisticDetail>> call, Response<ArrayList<StatisticDetail>> response) {
                String a= response.message();
                listData =response.body();
            }

            @Override
            public void onFailure(Call<ArrayList<StatisticDetail>> call, Throwable t) {
                String a= t.getMessage();
            }
        });
    }

}
