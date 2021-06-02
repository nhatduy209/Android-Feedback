package com.example.androidfeedback.ui.uiclass.trainee;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class DetailClassTrainee extends AppCompatActivity {
    private RecyclerView recyclerDetailClass;
    TraineeDetailClassAdapter classAdapter;
    ArrayList<ClassDetailModel> listClass;
    private Context context;
    private TextView txtclassID, txtclassName;
    private int classID ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_detail_trainee);
//        final View root = inflater.inflate(R.layout.class_detail_trainee, null);
        listClass = new ArrayList<ClassDetailModel>();
        recyclerDetailClass = findViewById(R.id.recyclerDetailTraineeClassView);
        txtclassID = findViewById(R.id.txtDetailTraineeListClassID);
        txtclassName = findViewById(R.id.txtDetailTraineeListClassName);

        Bundle b = getIntent().getExtras();
        try {
            classID = b.getInt("classId");  // get data passing from other activity
            txtclassID.setText(String.valueOf(classID));
            String className = b.getString("className");  // get data passing from other activity
            txtclassName.setText(className);
        } catch (Exception e) {
            String a = e.toString();
        }
        // get seesion
        SharedPreferences pref = getSharedPreferences("GetSession",Context.MODE_PRIVATE);
        String userId = pref.getString("userId", "");
        String userName = pref.getString("userName", "");
        String role  = pref.getString("role", "");

        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<List<ClassDetailModel>> getListClass = callGet.getListClassDetail(classID,role,userId);

        getListClass.enqueue(new Callback<List<ClassDetailModel>>() {
            @Override
            public void onResponse(Call<List<ClassDetailModel>> call, Response<List<ClassDetailModel>> response) {
                listClass = (ArrayList<ClassDetailModel>) response.body();
                reload(listClass, DetailClassTrainee.this );
            }

            @Override
            public void onFailure(Call<List<ClassDetailModel>> call, Throwable t) {
                String a = t.getMessage();
            }
        });

        reload(listClass, getApplicationContext());
    }
    public void reload(ArrayList<ClassDetailModel> listClass, Context context){
        classAdapter = new TraineeDetailClassAdapter(getApplicationContext(), listClass);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerDetailClass.setLayoutManager(new LinearLayoutManager(context));
        recyclerDetailClass.setAdapter(classAdapter);
    }
}
