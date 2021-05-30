package com.example.androidfeedback.ui.enrollment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
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
    ArrayList<ClassViewModel> listClass;
    Button btnAdd;
    Spinner filterSpinner ;
    private String filterName = "All";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_enrollment,null);
        enrollmentList = new ArrayList<>();
        listClass = new ArrayList<>();
        recyclerEnrollmentView = root.findViewById(R.id.recyclerEnrollmentView);
        filterSpinner = root.findViewById(R.id.spEnListClassName);




        btnAdd = root.findViewById(R.id.btnAddEnrollment);

        ArrayList<String> arrayList = new ArrayList<>();

        //call api get name class
        // get seesion
        SharedPreferences pref = getActivity().getSharedPreferences("GetSession", Context.MODE_PRIVATE);
        String userId = pref.getString("userId", "");
        String role  = pref.getString("role", "");
        // call api to get list question
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);
        Call<List<ClassViewModel>> getListClass = callGet.getListClass(role,userId);

        getListClass.enqueue(new Callback<List<ClassViewModel>>() {
            @Override
            public void onResponse(Call<List<ClassViewModel>> call, Response<List<ClassViewModel>> response) {
                String a = response.message();
                listClass = (ArrayList<ClassViewModel>) response.body();

                if(listClass.size() > 0){
                    final String[] listClassName = new String[listClass.size() + 1 ];
                    listClassName[0] ="All";
                    int count = 1 ;
                    if(!listClass.isEmpty()){
                        for( ClassViewModel i : listClass){
                            listClassName[count] = i.getClassName();
                            count++;
                        }
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, listClassName);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    filterSpinner.setAdapter(arrayAdapter);
                    filterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            filterName = parent.getItemAtPosition(position).toString();

                            // call api to get list question
                            Retrofit retrofit = RetrofitInstance.getClient();

                            CallGet callGet = retrofit.create(CallGet.class);

                            Call<List<EnrollmentViewModel>> getListEnrollment = callGet.getListEnrollment(filterName);

                            getListEnrollment.enqueue(new Callback<List<EnrollmentViewModel>>() {
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
                            Toast.makeText(parent.getContext(), "Selected: " + filterName,Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onNothingSelected(AdapterView <?> parent) {

                        }
                    });
                }


            }
            @Override
            public void onFailure(Call<List<ClassViewModel>> call, Throwable t) {
                String a = t.getMessage();
            }
        });
        return root;
    }
    public void reload(ArrayList<EnrollmentViewModel> listEnrollment, View view){
        enrollmentAdapter = new EnrollmentAdapter(getActivity().getApplicationContext(), listEnrollment);
        enrollmentAdapter.notifyDataSetChanged();
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerEnrollmentView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerEnrollmentView.setAdapter(enrollmentAdapter);
    }
}
