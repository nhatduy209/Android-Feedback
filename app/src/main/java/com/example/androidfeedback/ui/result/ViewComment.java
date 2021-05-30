package com.example.androidfeedback.ui.result;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewComment extends AppCompatActivity {
    private TextView txtClass, txtModule;
    private Button btnShowOverview, btnShowDetail;
    private RecyclerView recyclerView;
    private ResultCommentAdapter commentAdapter;
    private Context context;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.comment_result_view);
        final NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

        txtClass = findViewById(R.id.txtClassCommentResult);
        txtModule = findViewById(R.id.txtModuleCommentResult);
        btnShowDetail = findViewById(R.id.btnShowDetailComment);
        btnShowOverview = findViewById(R.id.btnShowOverview);
        recyclerView = findViewById(R.id.recyclerCommentResult);
        int[] listClass = new int[]{1,2,3,4};

        commentAdapter = new ResultCommentAdapter(context,listClass);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(commentAdapter);
    }
}
