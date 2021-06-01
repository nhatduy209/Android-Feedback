package com.example.androidfeedback.ui.uiclass.trainee;

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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.module.AddModule;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;
import com.example.androidfeedback.ui.uiclass.trainee.TraineeClassAdapter;

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

public class ClassTraineeFragment extends Fragment{

    private RecyclerView recyclerClass;
    TraineeClassAdapter classAdapter;
    ArrayList<ClassViewModel> listClass;
    private Button btnAdd ;
    private ImageView btnEdit  ;
    private Context finalContext;
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_class_trainee, null  );
        listClass = new ArrayList<ClassViewModel>();
        recyclerClass = root.findViewById(R.id.recyclerTraineeClassView);

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
        reload(listClass,root);
        return root ;
    }

    public void reload(ArrayList<ClassViewModel> listClass, View view){
        classAdapter = new TraineeClassAdapter(getActivity().getApplicationContext(), listClass);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerClass.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerClass.setAdapter(classAdapter);
    }
}
