package com.example.androidfeedback.ui.uiclass;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.AddQuestion;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import common.ValidationEditText;
import common.serviceAPI.CallPost;
import common.serviceAPI.CallPut;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddClass extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private TextView datePickerEnd , classNameEditText, capacityEditText , datePickerStart,tvTitle ;
    private Context context = this ;
    private Button btnBack ;
    private Button btnSave ;
    private int dateAdd = 0 ,classId ;    // choose which date pick is press by user
    private String dateStartAdd , dateEndAdd ;
    private boolean isEditing = false ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_class);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);
        String a = "" ;
        // assign variable
        classNameEditText = findViewById(R.id.editTextClassName);
        capacityEditText = findViewById(R.id.EditTextCapacity);
        datePickerEnd = findViewById(R.id.date_picker_end);
        tvTitle = findViewById(R.id.tvAddClass);
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
                navController.navigate(R.id.nav_assignment);
            }
        });

        // press btn save
        btnSave = findViewById(R.id.btn_Save_Class);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validation error
               boolean canAdd =  validationError();

               if(canAdd == false ){
                   return;
               }


               // check if user click add or edit
               if(!isEditing){
                   ClassViewModel classModel = new ClassViewModel(0,classNameEditText.getText().toString()
                           , dateStartAdd,dateEndAdd , capacityEditText.getText().toString(),false);
                   Retrofit retrofit = RetrofitInstance.getClient();
                   CallPost callPost = retrofit.create(CallPost.class);
                   Call<ClassViewModel> addClass  = callPost.addClassAPI(classModel);

                   // call callback
                   addClass.enqueue(new Callback<ClassViewModel>() {
                       @Override
                       public void onResponse(Call<ClassViewModel> call, Response<ClassViewModel> response) {
                           String res = response.message();
                           // load fragment again

                           SharedPreferences pref = getSharedPreferences("Refresh",Context.MODE_PRIVATE);
                           SharedPreferences.Editor editor = pref.edit();
                           editor.putBoolean("shouldReload",true);
                           editor.apply();

                           List<Fragment> frag = AddClass.this.getSupportFragmentManager().getFragments();
                           finish();
                           navController.navigate(R.id.nav_assignment);
                       }

                       @Override
                       public void onFailure(Call<ClassViewModel> call, Throwable t) {

                       }
                   });
               }
               else {
                   ClassViewModel classModel = new ClassViewModel(classId,classNameEditText.getText().toString()
                           , dateStartAdd,dateEndAdd , capacityEditText.getText().toString(),false);

                   Retrofit retrofit = RetrofitInstance.getClient();

                   CallPut callPost = retrofit.create(CallPut.class);

                   Call<ClassViewModel> updateClass  = callPost.updateClassAPI(classId,classModel);

                   // call callback
                   updateClass.enqueue(new Callback<ClassViewModel>() {
                       @Override
                       public void onResponse(Call<ClassViewModel> call, Response<ClassViewModel> response) {
                           String res = response.message();
                           Toast.makeText(context , response.body().getMessage(), Toast.LENGTH_LONG).show();
                           // load fragment again

                           SharedPreferences pref = getSharedPreferences("Refresh",Context.MODE_PRIVATE);
                           SharedPreferences.Editor editor = pref.edit();
                           editor.putBoolean("shouldReload",true);
                           editor.apply();

                           finish();
                           navController.navigate(R.id.nav_assignment);
                           // load fragment again

                       }

                       @Override
                       public void onFailure(Call<ClassViewModel> call, Throwable t) {

                       }
                   });
               }



            }
        });

        // get current data if edit
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
            classId = b.getInt("classId");

            isEditing = b.getBoolean("isEditing");
            String aa=  "1" ;
            tvTitle.setText("Edit Class");
        }catch(Exception e){
            return ;
        }


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (dateAdd == 1) {
            datePickerStart.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            dateStartAdd = year + "-" + (month + 1) + "-" + dayOfMonth;
            dateAdd = 0;  // return default value
        }
        else{
            datePickerEnd.setText(dayOfMonth +"-" + (month + 1) + "-" + year);
            dateEndAdd = year + "-" + (month + 1) + "-" + dayOfMonth;
        }
    }

    public boolean validationError(){
        EditText className , Capacity ;
        TextView   dateEnd , dateStart;
        boolean canAddClassName,canAddCapacity,canAddDateEnd,canAddDateStart;
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

        canAddClassName = validate.validateEditText(className ,errorClassName );
        canAddCapacity =  validate.validateEditText(Capacity , errorCapacity );
        canAddDateEnd = validate.validateTextView(dateEnd , errorEmptyDateEnd);
        canAddDateStart = validate.validateTextView(dateStart , errorEmptyDateStart);

        if(canAddClassName == false || canAddCapacity == false ||  canAddDateEnd == false || canAddDateStart == false )
        {
            return false;
        }
        return true;

    }
}
