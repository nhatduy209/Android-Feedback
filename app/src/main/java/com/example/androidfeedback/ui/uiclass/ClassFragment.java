package com.example.androidfeedback.ui.uiclass;

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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.module.AddModule;
import com.example.androidfeedback.ui.question.QuestionFragment;
import com.example.androidfeedback.ui.question.QuestionViewModel;

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

public class ClassFragment extends Fragment{

    private RecyclerView recyclerClass;
    ClassAdapter classAdapter;
    ArrayList<ClassViewModel> listClass;
    private Button btnAdd ;
    private ImageView btnEdit  ;
    private Context finalContext;
    private boolean allowRefresh  = false ;
    private boolean allowResume = false ;
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_class, null  );
//       final View smallRoot  = inflater.inflate(R.layout.class_recycler_view_item, null );
        listClass = new ArrayList<ClassViewModel>();
        recyclerClass = root.findViewById(R.id.recyclerClassView);
        btnAdd = root.findViewById(R.id.btn_add);


        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddClass.class);
                startActivity(intent);
            }
        });


        FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
        fl.removeAllViews();

        // get seesion
        SharedPreferences prefs = getActivity().getSharedPreferences("Refresh",Context.MODE_PRIVATE);
        boolean shouldAttach = prefs.getBoolean("shouldAttach", true);
        if(shouldAttach){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("shouldAttach",false);
            editor.putBoolean("shouldReload",false);
            editor.apply();
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.replace(this.getId(),new ClassFragment()).commit();
        }
        // get seesion
            SharedPreferences pref = getActivity().getSharedPreferences("GetSession",Context.MODE_PRIVATE);
            String userId = pref.getString("userId", "");
            String userName = pref.getString("userName", "");
            String role  = pref.getString("role", "");
            // call api to get list question
            Retrofit retrofit = RetrofitInstance.getClient();

            CallGet callGet = retrofit.create(CallGet.class);

            Call<List<ClassViewModel>> getListClass = callGet.getListClass(role,userId);

            getListClass.enqueue(new Callback<List<ClassViewModel>>() {
                @Override
                public void onResponse(Call<List<ClassViewModel>> call, Response<List<ClassViewModel>> response) {
                    listClass = (ArrayList<ClassViewModel>) response.body();
                    reload(listClass, root );
                }

                @Override
                public void onFailure(Call<List<ClassViewModel>> call, Throwable t) {
                    String a = t.getMessage();
                }
            });
        return root ;
    }

    public void reload(ArrayList<ClassViewModel> listClass, View view){
        classAdapter = new ClassAdapter(getActivity(), listClass);
        classAdapter.notifyDataSetChanged();
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerClass.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerClass.setAdapter(classAdapter);
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
            ft.replace(this.getId(),new ClassFragment()).commit();
        }
    }
}