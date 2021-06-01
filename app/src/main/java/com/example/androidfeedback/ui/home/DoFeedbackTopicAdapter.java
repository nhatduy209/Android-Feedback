package com.example.androidfeedback.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.feedback.AddFeedback;
import com.example.androidfeedback.ui.feedback.EditFeedBack;
import com.example.androidfeedback.ui.feedback.FeedbackViewModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class DoFeedbackTopicAdapter extends RecyclerView.Adapter<DoFeedbackTopicAdapter.ViewHolder>  {
    private int position;
    private Context context;
    private String[] listChoosen;
    private int[] listTopic;
    private DoFeedbackQuestionAdapter questionAdapter;
    @NonNull
    @Override
    public DoFeedbackTopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.do_feedback_part_b_item, parent, false);
        return new DoFeedbackTopicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DoFeedbackTopicAdapter.ViewHolder holder, int position) {
//        final FeedbackViewModel feedbacks = listFeedback.get(position);
        final int topic = listTopic[position];
        ArrayList<QuestionViewModel> listQuestion = new ArrayList<QuestionViewModel>();
        QuestionViewModel question = new QuestionViewModel(1,"dui dẻ hong quạo",1);
        listQuestion.add(question);
        listQuestion.add(question);
        listQuestion.add(question);
        questionAdapter = new DoFeedbackQuestionAdapter(context,listQuestion,this);
        holder.txtTopicName.setText(String.valueOf(topic));
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(questionAdapter);
    }


    public DoFeedbackTopicAdapter(Context context, int[] listTopic){
        this.context = context;
        this.listTopic = listTopic;
    }

    //create view holder
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTopicName;
        private RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTopicName = itemView.findViewById(R.id.txtTopicNameDoFeedback);
            recyclerView = itemView.findViewById(R.id.recyclerQuestionDoFeedback);
        }
    }

    @Override
    public void onViewRecycled(DoFeedbackTopicAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listTopic.length;
    }

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
