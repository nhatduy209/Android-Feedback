package com.example.androidfeedback.ui.uiclass;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

public class ClassFragment extends Fragment{

    private RecyclerView recyclerClass;
    ClassAdapter classAdapter;
    ArrayList<ClassViewModel> listClass;
    private Button btnAdd ;
    private ImageView btnEdit  ;
    private Context finalContext;
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_class, null  );
        final View smallRoot  = inflater.inflate(R.layout.class_recycler_view_item, null );
        listClass = new ArrayList<ClassViewModel>();
        recyclerClass = root.findViewById(R.id.recyclerClassView);
        btnAdd = root.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddClass.class);
                startActivity(intent);

            }
        });


        ClassViewModel classes = new ClassViewModel("","Name","tt",
                "11","40");
        classes.setClassId("idne");
        classes.setClassName("duy");
        classes.setStartDate("11");
        classes.setEndDate("22");
        classes.setCapacity("40");
        listClass.add(classes);
        reload(listClass,root);
        return root ;
    }

    public void reload(ArrayList<ClassViewModel> listClass, View view){
        classAdapter = new ClassAdapter(getActivity().getApplicationContext(), listClass);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerClass.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerClass.setAdapter(classAdapter);
    }
}
