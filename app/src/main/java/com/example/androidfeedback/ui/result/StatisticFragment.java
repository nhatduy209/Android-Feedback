package com.example.androidfeedback.ui.result;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.statistic.ClassStatisticViewModel;
import com.example.androidfeedback.ui.statistic.ModuleStatisticViewModel;
import com.example.androidfeedback.ui.statistic.StatisticViewModel;

import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StatisticFragment extends Fragment {
    private StatisticViewModel statisticData;
    private Spinner spinnerClass;
    private Spinner spinnerModule;
    private List<ClassStatisticViewModel> listClass;
    private List<ModuleStatisticViewModel> listModule;
    private ClassStatisticViewModel selectList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_result, container,false  );
        statisticData = new StatisticViewModel();

        // call api to get list question
        // call api to get list question
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<StatisticViewModel> getSelectList = callGet.getSelectList();

        getSelectList.enqueue(new Callback<StatisticViewModel>() {
            @Override
            public void onResponse(Call<StatisticViewModel> call, Response<StatisticViewModel> response) {
                statisticData = response.body();
                String s=response.message();
                int a=1;

            }
            @Override
            public void onFailure(Call<StatisticViewModel> call, Throwable t) {
                String s=t.getMessage();
                int x=1;
            }
        });

        // spinner here
        spinnerClass =(Spinner)root.findViewById(R.id.spinnerClass);
        spinnerModule = (Spinner)root.findViewById(R.id.spinnerModule);

        setSpinnerClass(spinnerClass,listClass);
        setSpinnerModule(spinnerModule,listModule);
        return root;
    }
    private void setSpinnerClass( Spinner spinner, List<ClassStatisticViewModel> listData){
        ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,listData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
//       // When user select a List-Item.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ClassStatisticViewModel classes = (ClassStatisticViewModel) parent.getSelectedItem();
                Toast.makeText(getActivity(), "class ID: "+classes.getClassID()+",  " +
                        "Class Name : "+classes.getClassName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void setSpinnerModule( Spinner spinner, List<ModuleStatisticViewModel> listData){
        ArrayAdapter dataAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,listData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
//       // When user select a List-Item.
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ModuleStatisticViewModel modules = (ModuleStatisticViewModel) parent.getSelectedItem();
                Toast.makeText(getActivity(), "module ID: "+modules.getModuleID()+",  " +
                        "module Name : "+modules.getModuleName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        ClassStatisticViewModel classes = (ClassStatisticViewModel) adapter.getItem(position);

        Toast.makeText(getActivity(), "Selected Employee: " + classes.getClassName() ,Toast.LENGTH_SHORT).show();
    }

    private void CallAPI(){
        // call api to get list question
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<StatisticViewModel> getSelectListVM = callGet.getSelectList();

        getSelectListVM.enqueue(new Callback<StatisticViewModel>() {
            @Override
            public void onResponse(Call<StatisticViewModel> call, Response<StatisticViewModel> response) {
                statisticData = response.body();
                listClass=response.body().getClasses();
                listModule=response.body().getCourses();
                int a=1;

            }

            @Override
            public void onFailure(Call<StatisticViewModel> call, Throwable t) {
                String s=t.getMessage();
                int x=1;
            }
        });
    }
}
