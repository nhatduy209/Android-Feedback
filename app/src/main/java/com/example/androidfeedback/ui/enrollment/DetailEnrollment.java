package com.example.androidfeedback.ui.enrollment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.uiclass.AddClass;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DetailEnrollment extends AppCompatActivity {
    private Context context = this ;
    private TextView txtEnTraineeId,txtEnTraineeName,txtEnClassName,txtInfoClassID,
            txtInfoCapacity,txtInfoClassName,txtInfoStartTime,txtInfoEndTime,
    txtInfoEmail,txtInfoAddress;
    private Button btnBack;
    private String classID ;
    private String traineeID ;
    private EnrollmentDetailModel detailEnrollment ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrollment_detail_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);
        txtEnTraineeName = findViewById(R.id.txtInfoTraineeName);
        txtEnTraineeId = findViewById(R.id.txtInfoTraineeID);
        txtEnClassName = findViewById(R.id.txtInfoClassName);
        txtInfoClassID = findViewById(R.id.txtInfoClassID);
        txtInfoCapacity = findViewById(R.id.txtInfoCapacity);
        txtInfoClassName = findViewById(R.id.txtInfoClassName);
        txtInfoStartTime = findViewById(R.id.txtInfoStartTime);
        txtInfoEndTime = findViewById(R.id.txtInfoEndTime);
        txtInfoEmail = findViewById(R.id.txtInfoEmail);
        txtInfoAddress  = findViewById(R.id.txtInfoAddress);
        // call api to detail class
        // get current data if edit
        Bundle b = getIntent().getExtras();
        try {
            traineeID = b.getString("traineeID");  // get data passing from other activity
            classID = b.getString("classID");
        } catch (Exception e) {
            return;
        }



        SharedPreferences pref = getSharedPreferences("GetSession",Context.MODE_PRIVATE);
        String userId = pref.getString("userId", "");
        String userName = pref.getString("userName", "");
        String role  = pref.getString("role", "");

        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<EnrollmentDetailModel> getDetailClass = callGet.getDetailEnrollment(classID,traineeID);

         getDetailClass.enqueue(new Callback<EnrollmentDetailModel>() {
            @Override
            public void onResponse(Call<EnrollmentDetailModel> call, Response<EnrollmentDetailModel> response) {
                String hi = response.message();
                detailEnrollment = response.body();

                setTextTextView(txtInfoClassID,detailEnrollment.getClassID());
                setTextTextView(txtInfoCapacity,detailEnrollment.getCapacity());
                setTextTextView(txtInfoClassName,detailEnrollment.getClassName());
                setTextTextView(txtInfoEndTime,detailEnrollment.getEndDate());
                setTextTextView(txtInfoStartTime,detailEnrollment.getStartDate());
                setTextTextView(txtEnTraineeId,traineeID);
                setTextTextView(txtEnTraineeName,detailEnrollment.getTraineeName());
                setTextTextView(txtInfoEmail,detailEnrollment.getEmail());
                setTextTextView(txtInfoAddress,detailEnrollment.getAddress());
            }

            @Override
            public void onFailure(Call<EnrollmentDetailModel> call, Throwable t) {

            }

        });

        btnBack = findViewById(R.id.btnInfoBack);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_class);
            }
        });

    }

    public void setTextTextView(TextView textView , String text){
        if(text.length() == 0 ){
            text = "empty";
            textView.setText(text);
            return;
        }
        else{
            textView.setText(text);
        }
    }
}
