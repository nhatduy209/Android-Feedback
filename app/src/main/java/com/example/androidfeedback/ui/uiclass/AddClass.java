package com.example.androidfeedback.ui.uiclass;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;

import java.util.Calendar;

public class AddClass extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView datePickerStart ;
    private TextView datePickerEnd ;
    private Context context = this ;
    private Button btnBack ;
    private Button btnSave ;
    private int dateAdd = 0 ;    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_class);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        datePickerStart = findViewById(R.id.date_picker_start);
        datePickerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateAdd = 1 ;
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,AddClass.this,year, month,day);
                datePickerDialog.show();
            }
        });

        datePickerEnd = findViewById(R.id.date_picker_end);
        datePickerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,AddClass.this,year, month,day);
                datePickerDialog.show();
            }
        });

        btnBack = findViewById(R.id.btn_Back_Class);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_class);
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (dateAdd == 1) {
            datePickerStart.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            dateAdd = 0;  // return default value
        }
        else{
            datePickerEnd.setText(dayOfMonth +"-" + (month + 1) + "-" + year);
        }
    }
}
