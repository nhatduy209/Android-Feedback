package com.example.androidfeedback.ui.result;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.feedback.AddFeedback;
import com.example.androidfeedback.ui.feedback.EditFeedBack;
import com.example.androidfeedback.ui.feedback.FeedbackAdapter;
import com.example.androidfeedback.ui.feedback.FeedbackViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;

public class ResultCommentAdapter extends RecyclerView.Adapter<ResultCommentAdapter.ViewHolder>{
    private Context context;
//    private ArrayList<ClassViewModel> listClass;
    private int[] listClass;
    private int position;
    @NonNull
    @Override
    public ResultCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_comment_item, parent, false);
        return new ResultCommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultCommentAdapter.ViewHolder holder, int position) {
        holder.txtNo.setText("1");
        holder.txtTraineeID.setText("TraineeID");
        holder.txtContent.setText("content");
    }

//tạm thời dùng listInt để test
    public ResultCommentAdapter(Context context, int[] listClass){
        this.context = context;
        this.listClass = listClass;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtNo,txtTraineeID,txtContent ;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtNo =itemView.findViewById(R.id.txtCommentNo);
            txtTraineeID = itemView.findViewById(R.id.txtCommentTraineeID);
            txtContent = itemView.findViewById(R.id.txtCommentContent);
        }
    }

    @Override
    public void onViewRecycled(ResultCommentAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listClass.length;
    }

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
