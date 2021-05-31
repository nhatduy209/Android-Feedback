package com.example.androidfeedback.ui.assignment;

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

public class EditAssignment extends AppCompatActivity {
    private Context context = this ;
    private TextView txtAssModuleName,txtAssClassName,txtAssModuleID,txtAssClassID,txtAssTrainer;
    private Button btnBack,btnSave;
    private int dateAdd = 0 ;    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_edit_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        txtAssModuleName = findViewById(R.id.txtAssEditModuleName);
        txtAssClassName = findViewById(R.id.txtAssEditClassName);
        txtAssTrainer = findViewById(R.id.txtAssEditTrainerID);
        txtAssClassID = findViewById(R.id.txtAssEditClassID);
        txtAssModuleID = findViewById(R.id.txtAssEditModuleID);
        btnSave = findViewById(R.id.btnAssSaveEdit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                navController.navigate(R.id.nav_assignment);
            }
        });

        btnBack = findViewById(R.id.btnAssBackEdit);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_assignment);
            }
        });

        // get current data if edit
        Bundle b = getIntent().getExtras();
        try {
            String moduleName = b.getString("moduleName");  // get data passing from other activity
            txtAssModuleName.setText(moduleName);

            int moduleID = b.getInt("moduleID");  // get data passing from other activity
            txtAssModuleID.setText(String.valueOf(moduleID));

            String className = b.getString("className");  // get data passing from other activity
            txtAssClassName.setText(className);

            int classID = b.getInt("classID");  // get data passing from other activity
            txtAssClassID.setText(String.valueOf(classID));

            String trainerId = b.getString("trainerID");
            String trainerName = b.getString("trainerName");
            txtAssTrainer.setText(trainerName);
        } catch (Exception e) {
            return;
        }
    }
}
