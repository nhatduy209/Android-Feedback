package com.example.androidfeedback.ui.uiclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;

public class ClassFragment extends Fragment {

    private RecyclerView recyclerClass;
    ClassAdapter classAdapter;
    ArrayList<ClassViewModel> listClass;
    private Button btnAdd ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_class, container, false);
        listClass = new ArrayList<ClassViewModel>();
        recyclerClass = root.findViewById(R.id.recyclerClassView);
        btnAdd = root.findViewById(R.id.btn_add);
        final Context finalContext = null;
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(finalContext.getApplicationContext());
                // Get the layout inflater
                LayoutInflater layoutInflater = LayoutInflater.from(finalContext);

                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(layoutInflater.inflate(R.layout.fragment_add_class, null))
                        // Add action buttons
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                // sign in the user ...
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
            }

        });
        ClassViewModel classes = new ClassViewModel("","Name","tt",
                "11");
        classes.setClassId("idne");
        classes.setClassName("duy");
        classes.setStartDate("11");
        classes.setEndDate("22");
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
