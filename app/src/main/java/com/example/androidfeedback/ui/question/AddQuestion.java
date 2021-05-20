package com.example.androidfeedback.ui.question;

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

public class AddQuestion extends AppCompatActivity{
    private TextView datePickerEnd , classNameEditText, capacityEditText , datePickerStart ;
    private Context context = this ;
    private Button btnBack ;
    private Button btnSave ;
    private int dateAdd = 0 ;    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_add_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        btnBack = findViewById(R.id.btnBackQuestion);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_question);
            }
        });

        // get current data if edit
//        classNameEditText = findViewById(R.id.editTextClassName);
//        capacityEditText = findViewById(R.id.EditTextCapacity);
//        Bundle b = getIntent().getExtras();
//        try{
//            String className = b.getString("className");  // get data passing from other activity
//            classNameEditText.setText(className);
//            String capacity = b.getString("capacity");  // get data passing from other activity
//            capacityEditText.setText(capacity);
//            String dateEnd = b.getString("endDate");  // get data passing from other activity
//            datePickerEnd.setText(dateEnd);
//            String dateStart  = b.getString("startDate");  // get data passing from other activity
//            datePickerStart.setText(dateStart);
//        }catch(Exception e){
//            return ;
//        }
    }
}
