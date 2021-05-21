package com.example.androidfeedback.ui.feedback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.gallery.GalleryViewModel;
import com.example.androidfeedback.ui.uiclass.AddClass;
import com.example.androidfeedback.ui.uiclass.ClassAdapter;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;

public class FeedbackFragment extends Fragment {
    private RecyclerView recyclerFeedback;
    FeedbackAdapter feedbackAdapter;
    ArrayList<FeedbackViewModel> listFeedback;
    private Button btnAdd ;
    private ImageView btnEdit  ;
    private Context finalContext;
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_feedback, null  );
        listFeedback = new ArrayList<FeedbackViewModel>();
        recyclerFeedback = root.findViewById(R.id.recyclerFeedbackView);
        btnAdd = root.findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddClass.class);
                startActivity(intent);
            }
        });


        FeedbackViewModel feedback = new FeedbackViewModel("123","Test 1 ","nhatduyamind");
        feedback.setFeedbackId("123");
        feedback.setFeedbackTitle("feedback 1 ");
        feedback.setAdminId("nhatduyadmin");
        listFeedback.add(feedback);
        reload(listFeedback,root);
        return root ;
    }

    public void reload(ArrayList<FeedbackViewModel> listFeedback, View view){
        feedbackAdapter = new FeedbackAdapter(getActivity().getApplicationContext(), listFeedback);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerFeedback.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerFeedback.setAdapter(feedbackAdapter);
    }
}
