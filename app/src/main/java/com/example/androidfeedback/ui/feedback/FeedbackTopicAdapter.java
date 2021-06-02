package com.example.androidfeedback.ui.feedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class FeedbackTopicAdapter  extends RecyclerView.Adapter<FeedbackTopicAdapter.ViewHolder> {
    private ArrayList<TopicFeedbackModel> listTopic;
    Context context;
    private int position;
    private ArrayList<QuestionViewModel>  listReview;
    private FeedbackQuestionAdapter feedbackQuestionAdapter;

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<QuestionViewModel> getListReview(){
        return listReview;
    }

    public void setListReview(QuestionViewModel questionViewModel){
        listReview.add(questionViewModel);
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
    public FeedbackTopicAdapter(Context context, ArrayList<TopicFeedbackModel> listTopic){
        this.context = context;
        this.listTopic = listTopic;
    }
    @Override
    public void onBindViewHolder(@NonNull final FeedbackTopicAdapter.ViewHolder holder, final int position){
        //truyền id của topic vào để truy vấn cho đúng luôn chứ mệt mủi
        final TopicFeedbackModel topic = listTopic.get(position);
        feedbackQuestionAdapter = new FeedbackQuestionAdapter(context,topic.getQuestions(),this);
        holder.txtTopicName.setText(topic.getTopicName());
        holder.recyclerListQuestion.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerListQuestion.setAdapter(feedbackQuestionAdapter);
    }
    @NonNull
    @Override

    public FeedbackTopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_in_topic_view, parent, false);
        listReview = new ArrayList<QuestionViewModel>();
        return new FeedbackTopicAdapter.ViewHolder(view);
    }

    @Override
    public void onViewRecycled(FeedbackTopicAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listTopic.size();
    }
}
