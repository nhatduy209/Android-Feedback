package com.example.androidfeedback.ui.uiclass.trainee;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;

public class DetailClassTrainee extends AppCompatActivity {
    private RecyclerView recyclerDetailClass;
    TraineeDetailClassAdapter classAdapter;
    ArrayList<ClassViewModel> listClass;
    private Context context;
    private TextView txtclassID, txtclassName;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.class_detail_trainee, null);
        listClass = new ArrayList<ClassViewModel>();
        recyclerDetailClass = root.findViewById(R.id.recyclerDetailTraineeClassView);
        txtclassID = findViewById(R.id.txtDetailClassTraineeID);
        txtclassName = findViewById(R.id.txtDetailClassTraineeName);
        ClassViewModel classes = new ClassViewModel(0,"", "Name", "tt",
                "11", false);
        classes.setClassId(1);
        classes.setClassName("duy");
        classes.setStartDate("11");
        classes.setEndDate("22");
        classes.setCapacity("40");
        listClass.add(classes);

        Bundle b = getIntent().getExtras();
        try {
            String classID = b.getString("classID");  // get data passing from other activity
            txtclassID.setText(classID);
            String adminID = b.getString("className");  // get data passing from other activity
            txtclassName.setText("đầm thấm");
        } catch (Exception e) {

        }

        reload(listClass, root);
        return root;
    }
    public void reload(ArrayList<ClassViewModel> listClass, View view){
        classAdapter = new TraineeDetailClassAdapter(getApplicationContext(), listClass);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerDetailClass.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerDetailClass.setAdapter(classAdapter);
    }
}
