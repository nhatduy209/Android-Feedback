package com.example.androidfeedback.ui.module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class ModuleFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerCategoryView;
    ModuleAdapter moduleAdapter;
    ArrayList<ModuleViewModel> moduleList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_module, container, false);
        moduleList = new ArrayList<ModuleViewModel>();
        recyclerCategoryView = root.findViewById(R.id.recyclerModuleView);
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
        moduleList.add(module);
        reload(moduleList,root);
        return root;
    }
    public void reload(ArrayList<ModuleViewModel> listCategory, View view){
        moduleAdapter = new ModuleAdapter(getActivity().getApplicationContext(), listCategory);
       // recyclerCategoryView.setHasFixedSize(true);
        recyclerCategoryView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerCategoryView.setAdapter(moduleAdapter);
    }
}
