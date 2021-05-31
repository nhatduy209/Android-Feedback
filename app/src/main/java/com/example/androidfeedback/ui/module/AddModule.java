package com.example.androidfeedback.ui.module;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;
import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
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
    private LocalDate start,end,fbStart,fbEnd;
    private ValidationEditText validationEditText;
    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_add_layout);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//        setSupportActionBar(toolbar);
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
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
                check = check();
                if(check){
                    Toast.makeText(context,"OK!!", Toast.LENGTH_SHORT).show();
                    finish();
                    navController.navigate(R.id.nav_module);
                }
                else{
                    Toast.makeText(context,"Wrong", Toast.LENGTH_SHORT).show();
                }
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
            start = LocalDate.of(year,month+1,dayOfMonth);
            dateAdd = 0;  // return default value
        }
        else if (dateAdd == 2) {
            datePickerEnd.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            end = LocalDate.of(year,month+1,dayOfMonth);
            dateAdd = 0;  // return default value
        }
        else if (dateAdd == 3) {
            fbDatePickerStart.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            fbStart = LocalDate.of(year,month+1,dayOfMonth);
            dateAdd = 0;  // return default value
        }
        else{
            fbEnd = LocalDate.of(year,month+1,dayOfMonth);
            fbDatePickerEnd.setText(dayOfMonth +"-" + (month + 1) + "-" + year);
        }
    }

//    public boolean checkEmpty(EditText editText){
//        if(editText.getText().toString().isEmpty())
//            return true;
//        return false;
//    }
    public Boolean checkNow(LocalDate date){
        LocalDate now = LocalDate.now();
        if(now.isAfter(date))
            return true;
        return false;
    }

    public Boolean checkStartEnd(LocalDate startDate, LocalDate endDate){
        if(endDate.isAfter(startDate))
            return true;
        return false;
    }
    public boolean check(){
        boolean c =true;
        //error message
        erModuleName = findViewById(R.id.erAddModuleName);
        c = validationEditText.validateEditText(txtAddModuleName,erModuleName);
        erDatePickerStart = findViewById(R.id.erAddModuleStartDate);
        erDatePickerEnd = findViewById(R.id.erAddModuleEndDate);
        if(!validationEditText.validateEditText(datePickerStart,erDatePickerStart)){
            c=false;
        }else{
            erDatePickerStart = findViewById(R.id.erAddModuleStartDate1);
            if(checkNow(start)){
                validationEditText.validateEditText(erDatePickerStart,true);
                c=false;
            }else{
                validationEditText.validateEditText(erDatePickerStart,false);
                if(!datePickerEnd.getText().toString().isEmpty()){
                    erDatePickerStart = findViewById(R.id.erAddModuleStartDate2);
                    if(!checkStartEnd(start,end)){
                        validationEditText.validateEditText(erDatePickerStart,true);
                        c=false;
                    }
                    else
                        validationEditText.validateEditText(erDatePickerStart,false);
                }

            }
        }

        if(!validationEditText.validateEditText(datePickerEnd,erDatePickerEnd)){
            c=false;
        }else{
            erDatePickerEnd = findViewById(R.id.erAddModuleEndDate1);
            if(checkNow(end)){
                validationEditText.validateEditText(erDatePickerEnd,true);
                c=false;
            }else{
                validationEditText.validateEditText(erDatePickerEnd,false);
                if(!datePickerStart.getText().toString().isEmpty()){
                    erDatePickerEnd = findViewById(R.id.erAddModuleEndDate2);
                    if(!checkStartEnd(start,end)){
                        validationEditText.validateEditText(erDatePickerEnd,true);
                        c=false;
                    }
                    else
                        validationEditText.validateEditText(erDatePickerEnd,false);
                }
            }
        }

        erFBDatePickerStart = findViewById(R.id.erModuleFBStartDate);
        erFBDatePickerEnd = findViewById(R.id.erAddModuleFBEndDate);
        if(!validationEditText.validateEditText(fbDatePickerStart,erFBDatePickerStart)){
            c=false;
        }else{
            erFBDatePickerStart = findViewById(R.id.erModuleFBStartDate1);
            if(checkNow(fbStart)){
                validationEditText.validateEditText(erFBDatePickerStart,true);
                c=false;
            }else{
                validationEditText.validateEditText(erFBDatePickerStart,false);
                if(!fbDatePickerEnd.getText().toString().isEmpty()){
                    erFBDatePickerStart = findViewById(R.id.erModuleFBStartDate2);
                    if(!checkStartEnd(fbStart,fbEnd)){
                        validationEditText.validateEditText(erFBDatePickerStart,true);
                        c=false;
                    }
                    else
                        validationEditText.validateEditText(erFBDatePickerStart,false);
                }

            }
        }


        if(!validationEditText.validateEditText(fbDatePickerEnd,erFBDatePickerEnd)){
            c=false;
        }else{
            erFBDatePickerEnd = findViewById(R.id.erAddModuleFBEndDate1);
            if(checkNow(fbEnd)){
                validationEditText.validateEditText(erFBDatePickerEnd,true);
                c=false;
            }else{
                validationEditText.validateEditText(erFBDatePickerEnd,false);
                if(!fbDatePickerStart.getText().toString().isEmpty()){
                    erFBDatePickerEnd = findViewById(R.id.erAddModuleFBEndDate2);
                    if(!checkStartEnd(fbStart,fbEnd)){
                        validationEditText.validateEditText(erFBDatePickerEnd,true);
                        c=false;
                    }
                    else
                        validationEditText.validateEditText(erFBDatePickerEnd,false);
                }

            }
        }
        return c;
//        boolean valModuleName,valStartDate,valEndDate,valFBStartDate,valFBEndDate;
//        valModuleName = validationEditText.validateEditText(txtAddModuleName,erModuleName);
//        valStartDate = validationEditText.validateEditText(datePickerStart,erDatePickerStart);
//        valEndDate = validationEditText.validateEditText(datePickerEnd,erDatePickerEnd);
//        valFBStartDate = validationEditText.validateEditText(fbDatePickerStart,erFBDatePickerStart);
//        valFBEndDate = validationEditText.validateEditText(fbDatePickerEnd,erFBDatePickerEnd);
//        if(!valModuleName || !valStartDate || !valEndDate || !valFBStartDate || !valFBEndDate)
//            return false;
//        return true;
    }

}