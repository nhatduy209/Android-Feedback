package com.example.androidfeedback.ui.feedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class FeedbackQuestionAdapter extends RecyclerView.Adapter<FeedbackQuestionAdapter.ViewHolder>{
    Context context;
    private ArrayList<QuestionViewModel> listQuestion;
    private int position;
    private FeedbackReviewModel reviewModel;
    private ArrayList<QuestionViewModel> listReview;
    private FeedbackTopicAdapter feedbackTopicAdapter;
    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public FeedbackQuestionAdapter(Context context, ArrayList<QuestionViewModel> listQuestion,FeedbackTopicAdapter feedbackTopicAdapter){
        this.context = context;
        this.listQuestion = listQuestion;
        this.feedbackTopicAdapter = feedbackTopicAdapter;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private CheckBox cb;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cb = itemView.findViewById(R.id.cbQuestionItemInTopic);
        }
    }
    @NonNull
    @Override

    public FeedbackQuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item_in_topic, parent, false);
        listReview = new ArrayList<QuestionViewModel>();
//        reviewModel = new FeedbackReviewModel(position,new ArrayList<QuestionViewModel>());
        return new FeedbackQuestionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedbackQuestionAdapter.ViewHolder holder, final int position){
        final QuestionViewModel question = listQuestion.get(position);
        holder.cb.setText(question.getQuestionContent());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    feedbackTopicAdapter.setListReview(question);
                }
                else{
                    feedbackTopicAdapter.getListReview().remove(question);
                }
            }
        });
    }

    @Override
    public void onViewRecycled(FeedbackQuestionAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listQuestion.size();
    }
}
