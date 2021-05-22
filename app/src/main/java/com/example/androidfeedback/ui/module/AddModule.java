package com.example.androidfeedback.ui.module;

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

public class AddModule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView datePickerEnd , txtAddModuleName,txtAdminID,txtFBTitle, datePickerStart, fbDatePickerStart,fbDatePickerEnd,tvAddModule;
    private Context context = this ;
    private Button btnBack ;
    private Button btnSave ;
    private int dateAdd = 0 ;    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_add_layout);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        txtAddModuleName = findViewById(R.id.txtAddModuleName);
        txtAdminID = findViewById(R.id.txtAddModuleAdmin);
        txtFBTitle = findViewById(R.id.txtAddFBModuleTitle);
        tvAddModule = findViewById(R.id.tvAddModule);

        datePickerStart = findViewById(R.id.txtAddModuleStartDate);
        datePickerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateAdd = 1;
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, AddModule.this, year, month, day);
                datePickerDialog.show();
            }
        });

        datePickerEnd = findViewById(R.id.txtAddModuleEndDate);
        datePickerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateAdd = 2;
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, AddModule.this, year, month, day);
                datePickerDialog.show();
            }
        });

        fbDatePickerStart = findViewById(R.id.txtAddFBModuleSD);
        fbDatePickerStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateAdd = 3;
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, AddModule.this, year, month, day);
                datePickerDialog.show();
            }
        });

        fbDatePickerEnd = findViewById(R.id.txtAddFBModuleED);
        fbDatePickerEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, AddModule.this, year, month, day);
                datePickerDialog.show();
            }
        });

        btnSave = findViewById(R.id.btnSaveAddModule);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                navController.navigate(R.id.nav_class);
            }
        });

        btnBack = findViewById(R.id.btnBackAddModule);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_module);
            }
        });

        // get current data if edit
        Bundle b = getIntent().getExtras();
        try {
            String moduleName = b.getString("moduleName");  // get data passing from other activity
            txtAddModuleName.setText(moduleName);
            String adminID = b.getString("adminID");  // get data passing from other activity
            txtAdminID.setText(adminID);
            String fbTitle = b.getString("fbTitle");  // get data passing from other activity
            txtFBTitle.setText(fbTitle);
            String dateEnd = b.getString("endDate");  // get data passing from other activity
            datePickerEnd.setText(dateEnd);
            String dateStart = b.getString("startDate");  // get data passing from other activity
            datePickerStart.setText(dateStart);
            String fbDateEnd = b.getString("fbEndDate");  // get data passing from other activity
            fbDatePickerEnd.setText(fbDateEnd);
            String fbDateStart = b.getString("fbStartDate");  // get data passing from other activity
            fbDatePickerStart.setText(fbDateStart);
            tvAddModule.setText("Edit Module List");
        } catch (Exception e) {
            return;
        }
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