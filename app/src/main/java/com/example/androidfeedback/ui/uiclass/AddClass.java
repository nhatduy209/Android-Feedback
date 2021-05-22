package com.example.androidfeedback.ui.uiclass;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;

import java.util.Calendar;

import common.ValidationEditText;

public class AddClass extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView datePickerEnd , classNameEditText, capacityEditText , datePickerStart ;
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

        // press btn save
        btnSave = findViewById(R.id.btn_Save_Class);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validation error
                validationError();
            }
        });

        // get current data if edit
        classNameEditText = findViewById(R.id.editTextClassName);
        capacityEditText = findViewById(R.id.EditTextCapacity);
        Bundle b = getIntent().getExtras();
        try{
            String className = b.getString("className");  // get data passing from other activity
            classNameEditText.setText(className);
            String capacity = b.getString("capacity");  // get data passing from other activity
            capacityEditText.setText(capacity);
            String dateEnd = b.getString("endDate");  // get data passing from other activity
            datePickerEnd.setText(dateEnd);
            String dateStart  = b.getString("startDate");  // get data passing from other activity
            datePickerStart.setText(dateStart);
        }catch(Exception e){
            return ;
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

    public void validationError(){
        EditText className , Capacity ;
        TextView   dateEnd , dateStart;
        className = findViewById(R.id.editTextClassName);
        Capacity  = findViewById(R.id.EditTextCapacity);
        dateEnd = findViewById(R.id.date_picker_end);
        dateStart = findViewById(R.id.date_picker_start);
        TextView errorClassName , errorEmptyDateEnd , errorCapacity,errorEmptyDateStart;
        errorClassName = findViewById(R.id.errorTextClassName);
        errorCapacity = findViewById(R.id.errorTextCapacity);
        errorEmptyDateEnd = findViewById(R.id.errorTextClassDateEnd);
        errorEmptyDateStart = findViewById(R.id.errorTextClassDateStart);
        ValidationEditText validate = new ValidationEditText();

        // validate

        validate.validateEditText(className ,errorClassName );
        validate.validateEditText(Capacity , errorCapacity );
        validate.validateTextView(dateEnd , errorEmptyDateEnd);
        validate.validateTextView(dateEnd , errorEmptyDateStart);
    }
}
