package com.example.androidfeedback.ui.module;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.QuestionFragment;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.uiclass.ClassFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ModuleFragment extends Fragment{

    private RecyclerView recyclerModule;
    ModuleAdapter classAdapter;
    ArrayList<ModuleViewModel> listModule;
    private Button btnAdd ;
    private ImageView btnEdit  ;
    private Context finalContext;
    private boolean allowRefresh = false ;
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
        fl.removeAllViews();
        final View root = inflater.inflate(R.layout.fragment_module, null  );
        final View smallRoot  = inflater.inflate(R.layout.module_recycler_view_item, null );
        listModule = new ArrayList<ModuleViewModel>();
        recyclerModule = root.findViewById(R.id.recyclerModuleView);
        btnAdd = root.findViewById(R.id.btnAddModule);
        // get seesion
        SharedPreferences pref = getActivity().getSharedPreferences("GetSession",Context.MODE_PRIVATE);
        String userId = pref.getString("userId", "");
        String role  = pref.getString("role", "");

        // call api to get list question
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);
        // check role
        switch(role){
            case "Admin" :
                // call api to get list question
                Call<List<ModuleViewModel>> getListModule = callGet.getListModule();

                getListModule.enqueue(new Callback<List<ModuleViewModel>>() {
                    @Override
                    public void onResponse(Call<List<ModuleViewModel>> call, Response<List<ModuleViewModel>> response) {
                        listModule = (ArrayList<ModuleViewModel>) response.body();
                        reload(listModule, root );
                    }

                    @Override
                    public void onFailure(Call<List<ModuleViewModel>> call, Throwable t) {

                    }
                });
                break;
            case  "Trainee" :
                Call<List<ModuleViewModel>> getListModuleTrainee = callGet.getListModuleTrainee(userId);

                getListModuleTrainee.enqueue(new Callback<List<ModuleViewModel>>() {
                    @Override
                    public void onResponse(Call<List<ModuleViewModel>> call, Response<List<ModuleViewModel>> response) {
                        listModule = (ArrayList<ModuleViewModel>) response.body();
                        reload(listModule, root );
                    }

                    @Override
                    public void onFailure(Call<List<ModuleViewModel>> call, Throwable t) {

                    }
                });
                btnAdd.setVisibility(View.INVISIBLE);
                break;
        }


        SharedPreferences prefs = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
        boolean shouldAttach = prefs.getBoolean("shouldAttach", true);
        if(shouldAttach){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("shouldAttach",false);
            editor.putBoolean("shouldReload",false);
            editor.apply();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(this.getId(),new ModuleFragment()).commit();
        }

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddModule.class);
                startActivity(intent);
            }
        });


        return root;
    }

    public void reload(ArrayList<ModuleViewModel> listModule, View view){
        classAdapter = new ModuleAdapter(getActivity().getApplicationContext(), listModule);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerModule.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerModule.setAdapter(classAdapter);
    }
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        boolean shouldReload = prefs.getBoolean("shouldReload", false);
        if (!allowRefresh && shouldReload){
            allowRefresh = true;
            SharedPreferences pref = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
            editor = pref.edit();
            editor.putBoolean("shouldAttach",true);
            editor.apply();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        boolean shouldReload = prefs.getBoolean("shouldReload", false);

        if (allowRefresh) {
            // get seesion
            allowRefresh = false;
            editor.putBoolean("shouldAttach",false);
            editor.apply();
        }
        if(shouldReload){
            editor.putBoolean("shouldReload",false);
            editor.apply();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(this.getId(),new ModuleFragment()).commit();
        }
    }
}