package com.example.androidfeedback.ui.feedback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
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
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.uiclass.AddClass;
import com.example.androidfeedback.ui.uiclass.ClassAdapter;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FeedbackFragment extends Fragment {
    private RecyclerView recyclerFeedback;
    FeedbackAdapter feedbackAdapter;
    ArrayList<FeedbackViewModel> listFeedback;
    private Button btnAdd ;
    private Context finalContext;
    public View onCreateView(@NonNull  LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_feedback, null  );
        listFeedback = new ArrayList<FeedbackViewModel>();
        recyclerFeedback = root.findViewById(R.id.recyclerFeedbackView);

        FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
        fl.removeAllViews();

        btnAdd = root.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddFeedback.class);
                startActivity(intent);
            }
        });

        // call api to get list feedback
        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<List<FeedbackViewModel>> getListQuestions = callGet.getListFeedback();

        getListQuestions.enqueue(new Callback<List<FeedbackViewModel>>() {
            @Override
            public void onResponse(Call<List<FeedbackViewModel>> call, Response<List<FeedbackViewModel>> response) {
                listFeedback = (ArrayList<FeedbackViewModel>) response.body();
                reload(listFeedback, root );
            }

            @Override
            public void onFailure(Call<List<FeedbackViewModel>> call, Throwable t) {

            }
        });

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
