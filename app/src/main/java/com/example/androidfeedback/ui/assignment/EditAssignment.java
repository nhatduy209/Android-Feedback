package com.example.androidfeedback.ui.assignment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.uiclass.AddClass;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.CallPut;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditAssignment extends AppCompatActivity {
    private Context context = this ;
    private TextView txtAssModuleName,txtAssClassName,txtAssModuleID,txtAssClassID,txtAssTrainer;
    private Button btnBack,btnSave;

    //For edit in api
    private int moduleId = 0,classId = 0;
    private  String trainerId, oldTrainerId;

    private Spinner spinnerTrainer;
    ArrayList<TrainerModel> listTrainer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_edit_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        txtAssModuleName = findViewById(R.id.txtAssEditModuleName);
        txtAssClassName = findViewById(R.id.txtAssEditClassName);
        txtAssTrainer = findViewById(R.id.txtAssEditTrainerID);
        txtAssClassID = findViewById(R.id.txtAssEditClassID);
        txtAssModuleID = findViewById(R.id.txtAssEditModuleID);

        listTrainer = new ArrayList<TrainerModel>();

        //Spinner
        spinnerTrainer = findViewById(R.id.spEditTrainer);


        //Get list  trainer
        Retrofit retrofit = RetrofitInstance.getClient();
        CallGet callGet = retrofit.create(CallGet.class);
        Call<List<TrainerModel>> getListTrainer = callGet.getListTrainer();

        getListTrainer.enqueue(new Callback<List<TrainerModel>>() {
            @Override
            public void onResponse(Call<List<TrainerModel>> call, Response<List<TrainerModel>> response) {
                listTrainer = (ArrayList<TrainerModel>) response.body();
                setSpinnerTrainer(spinnerTrainer,listTrainer);

            }

            @Override
            public void onFailure(Call<List<TrainerModel>> call, Throwable t) {

            }
        });

        //Edit assign
        btnSave = findViewById(R.id.btnAssSaveEdit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAssignmentModel assignmentModel = new AddAssignmentModel(classId,moduleId,trainerId);

                Retrofit retrofit = RetrofitInstance.getClient();

                CallPut callPost = retrofit.create(CallPut.class);

                Call<AddAssignmentModel> updateAssignment  = callPost.updateAssignmentAPI(oldTrainerId,assignmentModel);

                // call callback
                updateAssignment.enqueue(new Callback<AddAssignmentModel>() {
                    @Override
                    public void onResponse(Call<AddAssignmentModel> call, Response<AddAssignmentModel> response) {
                        String res = response.message();
                        Toast.makeText(context , response.body().getMessage(), Toast.LENGTH_LONG).show();
                        // load fragment again
                        finish();
                        navController.navigate(R.id.nav_assignment);

                    }

                    @Override
                    public void onFailure(Call<AddAssignmentModel> call, Throwable t) {

                    }


                });
            }
        });

        btnBack = findViewById(R.id.btnAssBackEdit);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_assignment);
            }
        });

        // get current data if edit
        Bundle b = getIntent().getExtras();
        try {
            String moduleName = b.getString("moduleName");  // get data passing from other activity
            txtAssModuleName.setText(moduleName);

            int moduleID = b.getInt("moduleID");  // get data passing from other activity
            this.moduleId = moduleID;
            txtAssModuleID.setText(String.valueOf(moduleID));

            String className = b.getString("className");  // get data passing from other activity
            txtAssClassName.setText(className);


            int classID = b.getInt("classID");  // get data passing from other activity
            txtAssClassID.setText(String.valueOf(classID));
            this.classId =classID;

            oldTrainerId = b.getString("trainerID");
            String trainerName = b.getString("trainerName");
            txtAssTrainer.setText(trainerName);
        } catch (Exception e) {
            return;
        }
    }
    private void setSpinnerTrainer(Spinner spinnerTrainer, List<TrainerModel> listTrainer) {
        ArrayAdapter dataAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,listTrainer);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTrainer.setAdapter(dataAdapter);
//       // When user select a List-Item.
        spinnerTrainer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TrainerModel trainerModel = (TrainerModel) parent.getSelectedItem();
                trainerId = trainerModel.getTrainerId();
                Toast.makeText(context , "trainer ID: "+trainerModel.getTrainerId()+",  " +
                        "Trainer Name : "+trainerModel.getTrainerName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
