package com.example.androidfeedback.ui.result;

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

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.home.FeedbackTraineeViewModel;

public class ResultFragment extends Fragment {
    private FeedbackTraineeViewModel feedbackTraineeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        feedbackTraineeViewModel =
                ViewModelProviders.of(this).get(FeedbackTraineeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }
}
