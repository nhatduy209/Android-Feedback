package com.example.androidfeedback.ui.module;

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

public class ModuleFragment extends Fragment{

    private RecyclerView recyclerModule;
    ModuleAdapter classAdapter;
    ArrayList<ModuleViewModel> listModule;
    private Button btnAdd ;
    private ImageView btnEdit  ;
    private Context finalContext;
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_module, null  );
        final View smallRoot  = inflater.inflate(R.layout.module_recycler_view_item, null );
        listModule = new ArrayList<ModuleViewModel>();
        recyclerModule = root.findViewById(R.id.recyclerModuleView);
        btnAdd = root.findViewById(R.id.btnAddModule);

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddModule.class);
                startActivity(intent);
            }
        });


        ModuleViewModel module = new ModuleViewModel(1,1,"tt",
                "11","22","huhu","111","222");
        module.setModuleId(1);
        module.setModuleName("thanhtoan");
        module.setAdminId(1);
        module.setStartDate("11");
        module.setEndDate("22");
        module.setFbTitle("huhu");
        module.setFbStartDate("111");
        module.setFbEndDate("222");
        listModule.add(module);
        listModule.add(module);
        listModule.add(module);
        reload(listModule,root);
        return root;
    }

    public void reload(ArrayList<ModuleViewModel> listModule, View view){
        classAdapter = new ModuleAdapter(getActivity().getApplicationContext(), listModule);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerModule.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerModule.setAdapter(classAdapter);
    }
}