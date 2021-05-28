package com.example.androidfeedback.ui.enrollment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.MainActivity;
import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.enrollment.AddEnrollment;
import com.example.androidfeedback.ui.enrollment.EnrollmentAdapter;
import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.home.HomeViewModel;
import com.example.androidfeedback.ui.module.ModuleAdapter;
import com.example.androidfeedback.ui.module.ModuleViewModel;
import com.example.androidfeedback.ui.uiclass.AddClass;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EnrollmentFragment extends Fragment {
    private RecyclerView recyclerEnrollmentView;
    EnrollmentAdapter enrollmentAdapter;
    ArrayList<EnrollmentViewModel> enrollmentList;
    Button btnAdd;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_enrollment,null);
        enrollmentList = new ArrayList<EnrollmentViewModel>();
        recyclerEnrollmentView = root.findViewById(R.id.recyclerEnrollmentView);

        btnAdd = root.findViewById(R.id.btnAddEnrollment);

        // call api to get list question
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<List<EnrollmentViewModel>> getListClass = callGet.getListEnrollment();

        getListClass.enqueue(new Callback<List<EnrollmentViewModel>>() {
            @Override
            public void onResponse(Call<List<EnrollmentViewModel>> call, Response<List<EnrollmentViewModel>> response) {
                enrollmentList = (ArrayList<EnrollmentViewModel>) response.body();
                reload(enrollmentList, root );
            }

            @Override
            public void onFailure(Call<List<EnrollmentViewModel>> call, Throwable t) {
                String a = t.getMessage();
            }
        });


        reload(enrollmentList,root);
        return root;
    }
    public void reload(ArrayList<EnrollmentViewModel> listEnrollment, View view){
        enrollmentAdapter = new EnrollmentAdapter(getActivity().getApplicationContext(), listEnrollment);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerEnrollmentView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerEnrollmentView.setAdapter(enrollmentAdapter);
    }
}
