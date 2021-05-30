package com.example.androidfeedback.ui.enrollment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.AddQuestion;
import com.example.androidfeedback.ui.uiclass.AddClass;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditEnrollment extends AppCompatActivity {
    private Context context = this ;
    private TextView txtEnTraineeId,txtEnTraineeName,txtEnClassName;
    private Button btnBack,btnSave;
    private ArrayList<ClassViewModel> listClass ;
    private int dateAdd = 0 ;    // choose which date pick is press by user
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrollment_edit_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        txtEnTraineeName = findViewById(R.id.txtEnEditTraineeName);
        txtEnTraineeId = findViewById(R.id.txtEnEditTraineeID);
        txtEnClassName = findViewById(R.id.txtEnEditClassName);

        btnSave = findViewById(R.id.btnEnSaveEdit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //load current fragment
                // load fragment again
                FragmentManager manager = EditEnrollment.this.getSupportFragmentManager();
                Fragment currentFragment = manager.findFragmentByTag("EnrollmentFragment");
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.detach(currentFragment);
                fragmentTransaction.attach(currentFragment);
                fragmentTransaction.commit();
                finish();
                navController.navigate(R.id.nav_enrollment);
            }
        });

        // get seesion
        SharedPreferences pref = context.getApplicationContext().getSharedPreferences("GetSession",Context.MODE_PRIVATE);
        String userId = pref.getString("userId", "");
        String role  = pref.getString("role", "");
        // call api to get list question
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);
        Call<List<ClassViewModel>> getListClass = callGet.getListClass(role,userId);

        getListClass.enqueue(new Callback<List<ClassViewModel>>() {
            @Override
            public void onResponse(Call<List<ClassViewModel>> call, Response<List<ClassViewModel>> response) {
                String a = response.message();
                listClass = (ArrayList<ClassViewModel>) response.body();
            }

            @Override
            public void onFailure(Call<List<ClassViewModel>> call, Throwable t) {
                String a = t.getMessage();
            }
        });


        btnBack = findViewById(R.id.btnEnBackEdit);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //load current fragment
                finish();
                navController.navigate(R.id.nav_enrollment);
            }
        });


        txtEnClassName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] listClassName = new String[listClass.size()];
                int count = 0 ;
                if(!listClass.isEmpty()){
                    for( ClassViewModel i : listClass){
                        listClassName[count] = i.getClassName();
                        count++;
                    }
                }
                new AlertDialog.Builder(context)
                        .setTitle("Choose class")
                        .setItems(listClassName, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                txtEnClassName.setText(listClassName[which]);
                           }
                        })
                        .setNegativeButton("No", null).show();
            }
        });

        // get current data if edit
        Bundle b = getIntent().getExtras();
        try {
            String traineeID = b.getString("traineeID");  // get data passing from other activity
            txtEnTraineeId.setText(traineeID);
            String traineeName = b.getString("traineeName");  // get data passing from other activity
            txtEnTraineeName.setText(traineeName);
            String className = b.getString("className");  // get data passing from other activity
            txtEnClassName.setText(className);
        } catch (Exception e) {
            return;
        }
    }
}
