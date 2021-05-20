package com.example.androidfeedback.ui.assignment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.uiclass.AddClass;

import java.util.Calendar;

public class AddAssignment extends AppCompatActivity {
    private Context context = this ;
    private Button btnBack ;
    private Button btnSave ;
    private int dateAdd = 0 ;    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_add_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        btnBack = findViewById(R.id.btnAssBackModule);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_class);
            }
        });

//        // get current data if edit
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
