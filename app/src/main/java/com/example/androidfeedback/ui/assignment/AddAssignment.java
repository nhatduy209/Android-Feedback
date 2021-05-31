package com.example.androidfeedback.ui.assignment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.androidfeedback.ui.module.ModuleViewModel;
import com.example.androidfeedback.ui.uiclass.AddClass;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddAssignment extends AppCompatActivity {
    private Context context = this ;
    private Spinner spinnerClass;
    private Spinner spinnerModule;
    private  Spinner spinnerTrainer;
    private Button btnBack,btnSave;
    private int moduleId = 0,classId = 0;
    private  String trainerId;
    private int dateAdd = 0 ;    // choose which date pick is press by user


     ArrayList<ClassViewModel> listClass;
     ArrayList<ModuleViewModel> listModule;
    ArrayList<TrainerModel> listTrainer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_add_layout);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        // Create assignment model

        listClass = new ArrayList<ClassViewModel>();
        listModule = new ArrayList<ModuleViewModel>();
        listTrainer = new ArrayList<TrainerModel>();

        //Spinner
        spinnerClass =findViewById(R.id.spAssAddClass);
        spinnerModule = findViewById(R.id.spAssAddModule);
        spinnerTrainer = findViewById(R.id.spAssAddTrainer);

        btnSave = findViewById(R.id.btnAssSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(moduleId != 0 && classId !=0 && trainerId!=null )
                {
                    AddAssignmentModel addAssignment = new AddAssignmentModel(classId,moduleId,trainerId);
                    Retrofit retrofit = RetrofitInstance.getClient();

                    CallPost callPost = retrofit.create(CallPost.class);

                    Call<AddAssignmentModel> addAssignmnet  = callPost.addAssignmentAPI(addAssignment);

                    // call callback
                    addAssignmnet.enqueue(new Callback<AddAssignmentModel>() {
                        @Override
                        public void onResponse(Call<AddAssignmentModel> call, Response<AddAssignmentModel> response) {
                            String res = response.message();

                            // load fragment again

                            finish();
                            navController.navigate(R.id.nav_assignment);

                        }

                        @Override
                        public void onFailure(Call<AddAssignmentModel> call, Throwable t) {

                        }


                    });
                }



            }
        });

        btnBack = findViewById(R.id.btnAssBack);
        btnBack.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
                navController.navigate(R.id.nav_assignment);
            }
        });

        //Get list  module
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<List<ModuleViewModel>> getListModule = callGet.getListModule();

        getListModule.enqueue(new Callback<List<ModuleViewModel>>() {
            @Override
            public void onResponse(Call<List<ModuleViewModel>> call, Response<List<ModuleViewModel>> response) {
                listModule = (ArrayList<ModuleViewModel>) response.body();
                setSpinnerModule(spinnerModule,listModule);

            }

            @Override
            public void onFailure(Call<List<ModuleViewModel>> call, Throwable t) {

            }
        });
        //Get list classes
             // get session
        SharedPreferences pref = this.getSharedPreferences("GetSession",Context.MODE_PRIVATE);
        String userId = pref.getString("userId", "");
        String userName = pref.getString("userName", "");
        String role  = pref.getString("role", "");

        Call<List<ClassViewModel>> getListClass = callGet.getListClass(role,userId);

        getListClass.enqueue(new Callback<List<ClassViewModel>>() {
            @Override
            public void onResponse(Call<List<ClassViewModel>> call, Response<List<ClassViewModel>> response) {
                listClass = (ArrayList<ClassViewModel>) response.body();
                setSpinnerClass(spinnerClass,listClass);
            }

            @Override
            public void onFailure(Call<List<ClassViewModel>> call, Throwable t) {
                String a = t.getMessage();
            }
        });


        //Get list  trainer
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

    }

    private void setSpinnerClass(Spinner spinnerClass, List<ClassViewModel> listClass) {
        ArrayAdapter dataAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,listClass);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClass.setAdapter(dataAdapter);
//       // When user select a List-Item.
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ClassViewModel classViewModel = (ClassViewModel) parent.getSelectedItem();
                classId = classViewModel.getClassId();
                Toast.makeText(context , "class ID: "+classViewModel.getClassId()+",  " +
                        "Class Name : "+classViewModel.getClassName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setSpinnerModule(Spinner spinnerModule, List<ModuleViewModel> listModule) {
        ArrayAdapter dataAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item,listModule);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModule.setAdapter(dataAdapter);
//       // When user select a List-Item.
        spinnerModule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ModuleViewModel moduleViewModel = (ModuleViewModel) parent.getSelectedItem();
                moduleId = moduleViewModel.getModuleId();
                Toast.makeText(context , "module ID: "+moduleViewModel.getModuleId()+",  " +
                        "Module Name : "+moduleViewModel.getModuleName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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
