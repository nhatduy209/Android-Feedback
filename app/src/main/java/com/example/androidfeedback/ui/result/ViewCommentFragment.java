package com.example.androidfeedback.ui.result;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.statistic.CommentViewModel;

import java.util.ArrayList;

import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ViewCommentFragment extends Fragment {
    private TextView txtClass, txtModule;
    private Button btnShowOverview, btnShowDetail;
    private RecyclerView recyclerView;
    private ResultCommentAdapter commentAdapter;
    private Context context;
    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;
    private ArrayList<CommentViewModel> listComment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "ViewCommentFragment"+": onCreate", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);
        Toast.makeText(getActivity(), "ViewCommentFragment: onCreateView", Toast.LENGTH_SHORT).show();

        View root = inflater.inflate(R.layout.fragment_comment, container, false);

        listComment= new ArrayList<>();

        Retrofit retrofit = RetrofitInstance.getClient();

        CallGet callGet = retrofit.create(CallGet.class);

        Call<ArrayList<CommentViewModel>> getListComment = callGet.getListComment(4,7);

        getListComment.enqueue(new Callback<ArrayList<CommentViewModel>>() {
            @Override
            public void onResponse(Call<ArrayList<CommentViewModel>> call, Response<ArrayList<CommentViewModel>> response) {
                listComment = (ArrayList<CommentViewModel>) response.body();
                recyclerView = root.findViewById(R.id.recyclerCommentResult);
                int[] listClass = new int[]{1,2,3,4};

                commentAdapter = new ResultCommentAdapter(context,listComment);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
                recyclerView.setAdapter(commentAdapter);
//                reload(listComment, root );
                String z="1";
            }

            @Override
            public void onFailure(Call<ArrayList<CommentViewModel>> call, Throwable t) {
                String z="1";
            }
        });


        return root;
    }

    @Override
    public void onStart() {
        Toast.makeText(getActivity(), "ViewCommentFragment: onStart", Toast.LENGTH_SHORT).show();

        super.onStart();
    }

    @Override
    public void onResume() {
        Toast.makeText(getActivity(), "ViewCommentFragment: onResume", Toast.LENGTH_SHORT).show();

        super.onResume();
    }

    @Override
    public void onPause() {
        Toast.makeText(getActivity(), "ViewCommentFragment: onPause", Toast.LENGTH_SHORT).show();

        super.onPause();
    }

    @Override
    public void onStop() {
        Toast.makeText(getActivity(), "ViewCommentFragment: onStop", Toast.LENGTH_SHORT).show();

        super.onStop();
    }


    @Override
    public void onDestroyView() {
        Toast.makeText(getActivity(), "ViewCommentFragment: onDestroyView", Toast.LENGTH_SHORT).show();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getActivity(), "ViewCommentFragment: onDestroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
    public void reload(ArrayList<CommentViewModel> listComment, View view){
        commentAdapter = new  ResultCommentAdapter(context,listComment);
        // recyclerCategoryView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(commentAdapter);
    }

}
