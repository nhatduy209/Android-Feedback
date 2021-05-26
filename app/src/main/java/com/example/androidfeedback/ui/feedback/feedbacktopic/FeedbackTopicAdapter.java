package com.example.androidfeedback.ui.feedback.feedbacktopic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.AddQuestion;
import com.example.androidfeedback.ui.question.QuestionAdapter;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class FeedbackTopicAdapter  extends RecyclerView.Adapter<FeedbackTopicAdapter.ViewHolder> {
    private int[] listTopic;
    Context context;
    private int position;

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerListQuestion;
        private TextView txtTopicName;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTopicName = itemView.findViewById(R.id.tvFBTopicQuestionName);
            recyclerListQuestion = itemView.findViewById(R.id.recyclerQuestionInTopic);
        }
    }
    public FeedbackTopicAdapter(Context context, int[] listTopic){
        this.context = context;
        this.listTopic = listTopic;
    }
    @Override
    public void onBindViewHolder(@NonNull final FeedbackTopicAdapter.ViewHolder holder, final int position){
        //truyền id của topic vào để truy vấn cho đúng luôn chứ mệt mủi
        final int topic = listTopic[position];
        ArrayList<QuestionViewModel> listQuestion = new ArrayList<QuestionViewModel>();
        QuestionViewModel question = new QuestionViewModel(1,"dui dẻ hong quạo",topic,"ai biết");
        listQuestion.add(question);
        listQuestion.add(question);
        listQuestion.add(question);
        FeedbackQuestionAdapter feedbackQuestionAdapter = new FeedbackQuestionAdapter(context,listQuestion);
        holder.txtTopicName.setText(String.valueOf(topic));
        holder.recyclerListQuestion.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerListQuestion.setAdapter(feedbackQuestionAdapter);
    }
    @NonNull
    @Override

    public FeedbackTopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_in_topic_view, parent, false);
        return new FeedbackTopicAdapter.ViewHolder(view);
    }

    @Override
    public void onViewRecycled(FeedbackTopicAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listTopic.length;
    }
}
