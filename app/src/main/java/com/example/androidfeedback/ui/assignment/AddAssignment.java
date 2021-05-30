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

public class AddAssignment extends AppCompatActivity {
    private Context context = this ;
    private TextView txtAssModuleName,txtAssClassName,txtAssTrainee;
    private Button btnBack,btnSave;
    private int dateAdd = 0 ;    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_add_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        txtAssModuleName = findViewById(R.id.txtAssAddModuleName);
        txtAssClassName = findViewById(R.id.txtAssAddClassName);
        txtAssTrainee = findViewById(R.id.txtAssAddTraineeID);
        btnSave = findViewById(R.id.btnAssSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                navController.navigate(R.id.nav_class);
            }
        });

        btnBack = findViewById(R.id.btnAssBack);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_class);
            }
        });

    }
}
