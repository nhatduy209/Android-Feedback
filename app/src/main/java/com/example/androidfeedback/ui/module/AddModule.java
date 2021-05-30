package com.example.androidfeedback.ui.module;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import common.ValidationEditText;

import static java.security.AccessController.getContext;

public class AddModule extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText datePickerEnd, txtAddModuleName,txtAdminID,txtFBTitle, datePickerStart, fbDatePickerStart,fbDatePickerEnd;
    private Context context = this ;
    private Button btnBack ;
    private Button btnSave ;
    private TextView erModuleName,erDatePickerStart,erDatePickerEnd,erFBDatePickerStart,erFBDatePickerEnd,tvAddModule;
    private boolean check;
    private int dateAdd = 0 ;
    private ArrayList<Integer> startDate;
    private ArrayList<Integer> FBStartDate;
    private ValidationEditText validationEditText;
    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_add_layout);
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        txtAddModuleName = findViewById(R.id.txtAddModuleName);
        txtAdminID = findViewById(R.id.txtAddModuleAdmin);
        txtFBTitle = findViewById(R.id.txtAddFBModuleTitle);
        tvAddModule = findViewById(R.id.tvAddModule);
        //error message
        erModuleName = findViewById(R.id.erAddModuleName);
        erDatePickerStart = findViewById(R.id.erAddModuleStartDate);
        erDatePickerEnd = findViewById(R.id.erAddModuleEndDate);
        erFBDatePickerStart = findViewById(R.id.erModuleFBStartDate);
        erFBDatePickerEnd = findViewById(R.id.erAddModuleFBEndDate);
        check = true;
        startDate = new ArrayList<>();
        FBStartDate = new ArrayList<>();
        validationEditText = new ValidationEditText();
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
//                finish();
//                navController.navigate(R.id.nav_class);
                check = checkEmpty();
                if(!check)
                    Toast.makeText(context,String.valueOf(check), Toast.LENGTH_SHORT).show();
//                else{
//                    finish();
//                    navController.navigate(R.id.nav_module);
//                }
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

//        if (dateAdd == 1) {
//            startDate[0]=month;
//            startDate[1]=dayOfMonth;
//            startDate[2]=year;
//            if(CheckDate(dayOfMonth,month,year)){
//                check = validationEditText.validateEditText(datePickerStart,erDatePickerStart);
//                Toast.makeText(context,String.valueOf(check), Toast.LENGTH_SHORT).show();
//            }
//            else
//                datePickerStart.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
//            dateAdd = 0;  // return default value
//        }
        if (dateAdd == 1) {
            datePickerStart.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            dateAdd = 0;  // return default value
        }
        else if (dateAdd == 2) {
            datePickerEnd.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            dateAdd = 0;  // return default value
        }
        else if (dateAdd == 3) {
//            FBStartDate[0]=month;
//            FBStartDate[1]=dayOfMonth;
//            FBStartDate[2]=year;
            fbDatePickerStart.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            dateAdd = 0;  // return default value
        }
        else{

            fbDatePickerEnd.setText(dayOfMonth +"-" + (month + 1) + "-" + year);
        }
    }

    public boolean checkEmpty(){
        boolean valModuleName,valStartDate,valEndDate,valFBStartDate,valFBEndDate;
        valModuleName = validationEditText.validateEditText(txtAddModuleName,erModuleName);
        valStartDate = validationEditText.validateEditText(datePickerStart,erDatePickerStart);
        valEndDate = validationEditText.validateEditText(datePickerEnd,erDatePickerEnd);
        valFBStartDate = validationEditText.validateEditText(fbDatePickerStart,erFBDatePickerStart);
        valFBEndDate = validationEditText.validateEditText(fbDatePickerEnd,erFBDatePickerEnd);
        if(!valModuleName || !valStartDate || !valEndDate || !valFBStartDate || !valFBEndDate)
            return false;
        return true;
    }

    public boolean CheckDate(int dd,int mm,int yyyy){
        Date curentTime = java.util.Calendar.getInstance().getTime();
        boolean check = false;
        if(yyyy >= curentTime.getYear())
            if( mm >= curentTime.getMonth())
                if(dd >= curentTime.getDate()){
                    return true;
                }
        return false;
    }
}