package com.example.androidfeedback.ui.enrollment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.uiclass.AddClass;

import java.util.Calendar;

public class DetailEnrollment extends AppCompatActivity {
    private Context context = this ;
//    private TextView txtEnTraineeId,txtEnTraineeName,txtEnClassName;
    private Button btnBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrollment_detail_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

//        txtEnTraineeName = findViewById(R.id.txtEnEditTraineeName);
//        txtEnTraineeId = findViewById(R.id.txtEnEditTraineeID);
//        txtEnClassName = findViewById(R.id.txtEnEditTraineeName);

        btnBack = findViewById(R.id.btnInfoBack);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_class);
            }
        });

        // get current data if edit
        Bundle b = getIntent().getExtras();
        try {
            String traineeID = b.getString("traineeID");  // get data passing from other activity
//            txtEnTraineeId.setText(traineeID);
            String classID = b.getString("classID");  // get data passing from other activity
//            txtEnTraineeName.setText(traineeName);
//            String className = b.getString("className");  // get data passing from other activity
//            txtEnClassName.setText(className);
        } catch (Exception e) {
            return;
        }
    }
}
