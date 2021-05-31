package com.example.androidfeedback.ui.enrollment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.assignment.TrainerModel;
import com.example.androidfeedback.ui.question.AddQuestion;
import com.example.androidfeedback.ui.uiclass.AddClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditEnrollment extends AppCompatActivity {
    private Context context = this ;
    private TextView txtEnTraineeId,txtEnTraineeName,txtEnClassName;
    private Button btnBack,btnSave;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrollment_edit_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        txtEnTraineeName = findViewById(R.id.txtEnEditTraineeName);
        txtEnTraineeId = findViewById(R.id.txtEnEditTraineeID);
        txtEnClassName = findViewById(R.id.txtEnEditClassName);



        btnSave = findViewById(R.id.btnEnSaveEdit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //load current fragment
                // load fragment again
                FragmentManager manager = EditEnrollment.this.getSupportFragmentManager();
                Fragment currentFragment = manager.findFragmentByTag("EnrollmentFragment");
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(currentFragment);
                fragmentTransaction.attach(currentFragment);
                fragmentTransaction.commit();
                finish();
                navController.navigate(R.id.nav_enrollment);
            }
        });

        btnBack = findViewById(R.id.btnEnBackEdit);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //load current fragment
                // load fragment again
                FragmentManager manager = EditEnrollment.this.getSupportFragmentManager();
                Fragment currentFragment = manager.findFragmentByTag("EnrollmentFragment");
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(currentFragment);
                fragmentTransaction.attach(currentFragment);
                fragmentTransaction.commit();


                finish();
                navController.navigate(R.id.nav_class);
            }
        });

        // get current data if edit
        Bundle b = getIntent().getExtras();
        try {
            String traineeID = b.getString("traineeID");  // get data passing from other activity
            txtEnTraineeId.setText(traineeID);

            String traineeName = b.getString("traineeName");  // get data passing from other activity
            txtEnTraineeName.setText(traineeName);
            String className = b.getString("className");  // get data passing from other activity
            txtEnClassName.setText(className);
        } catch (Exception e) {
            return;
        }

    }

}
