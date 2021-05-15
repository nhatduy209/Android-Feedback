package com.example.androidfeedback.ui.question;

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
import com.example.androidfeedback.ui.module.ModuleAdapter;
import com.example.androidfeedback.ui.module.ModuleViewModel;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {
    private RecyclerView recyclerQuestionView;
    QuestionAdapter questionAdapter;
    ArrayList<QuestionViewModel> questionList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_question, container, false);
        questionList = new ArrayList<QuestionViewModel>();
        recyclerQuestionView = root.findViewById(R.id.recyclerQuestionView);
        QuestionViewModel question = new QuestionViewModel(1,"11",1,"22");
        question.setQuestionID(1);
        question.setQuestionContent("111");
        question.setTopicID(1);
        question.setQuestionContent("222");
        questionList.add(question);
        questionList.add(question);
        questionList.add(question);
        reload(questionList,root);
        return root;
    }
    public void reload(ArrayList<QuestionViewModel> listQuestion, View view){
        questionAdapter = new QuestionAdapter(getActivity().getApplicationContext(), listQuestion);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerQuestionView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerQuestionView.setAdapter(questionAdapter);
    }
}
