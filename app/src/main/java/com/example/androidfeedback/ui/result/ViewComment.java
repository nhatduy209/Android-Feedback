package com.example.androidfeedback.ui.result;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

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
        recyclerView = findViewById(R.id.recyclerCommentResult2);
        int[] listClass = new int[]{1,2,3,4};

//        commentAdapter = new ResultCommentAdapter(context,lis);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(commentAdapter);
    }
}
