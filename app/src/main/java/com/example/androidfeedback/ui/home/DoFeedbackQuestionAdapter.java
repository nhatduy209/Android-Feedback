package com.example.androidfeedback.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.feedback.FeedbackReviewModel;
import com.example.androidfeedback.ui.feedback.FeedbackTopicAdapter;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

public class DoFeedbackQuestionAdapter extends RecyclerView.Adapter<DoFeedbackQuestionAdapter.ViewHolder>{
    Context context;
    private ArrayList<QuestionViewModel> listQuestion;
    private int position;
    private FeedbackReviewModel reviewModel;
    private ArrayList<FeedbackReviewModel> listReview;
    private DoFeedbackTopicAdapter feedbackTopicAdapter;
    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public DoFeedbackQuestionAdapter(Context context,ArrayList<QuestionViewModel> listQuestion,DoFeedbackTopicAdapter feedbackTopicAdapter){
        this.context = context;
        this.listQuestion = listQuestion;
        this.feedbackTopicAdapter = feedbackTopicAdapter;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private RadioButton radStronglyDisagree, radUnDecided,radStrongAgree, radDisagree,radAgree;
        private TextView txtQuestionContent;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            radAgree = itemView.findViewById(R.id.radAgree);
            radDisagree = itemView.findViewById(R.id.radDisAgree);
            radStrongAgree = itemView.findViewById(R.id.radStrongAgree);
            radStronglyDisagree = itemView.findViewById(R.id.radStronglyDisagree);
            radUnDecided = itemView.findViewById(R.id.radUnDecided);
            txtQuestionContent = itemView.findViewById(R.id.txtQuestionContentDoFeedback);
        }
    }
    @NonNull
    @Override

    public DoFeedbackQuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_b_radio_item, parent, false);
        return new DoFeedbackQuestionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DoFeedbackQuestionAdapter.ViewHolder holder, final int position){
//        final QuestionViewModel question = listQuestion.get(position);
        holder.txtQuestionContent.setText("merge");
        holder.radStronglyDisagree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = holder.radStronglyDisagree.isChecked();
                if(checked){
                    holder.radUnDecided.setChecked(false);
                    holder.radStrongAgree.setChecked(false);
                    holder.radDisagree.setChecked(false);
                    holder.radAgree.setChecked(false);
                }

            }
        });
        holder.radDisagree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = holder.radDisagree.isChecked();
                if(checked){
                    holder.radUnDecided.setChecked(false);
                    holder.radStrongAgree.setChecked(false);
                    holder.radStronglyDisagree.setChecked(false);
                    holder.radAgree.setChecked(false);
                }
            }
        });
        holder.radAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = holder.radDisagree.isChecked();
                if(!checked){
                    holder.radUnDecided.setChecked(false);
                    holder.radStrongAgree.setChecked(false);
                    holder.radDisagree.setChecked(false);
                    holder.radStronglyDisagree.setChecked(false);
                }
            }
        });
        holder.radStrongAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = holder.radDisagree.isChecked();
                if(checked){
                    holder.radUnDecided.setChecked(false);
                    holder.radStronglyDisagree.setChecked(false);
                    holder.radDisagree.setChecked(false);
                    holder.radAgree.setChecked(false);
                }
            }
        });
        holder.radUnDecided.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean checked = holder.radUnDecided.isChecked();
                if(checked){
                    holder.radStronglyDisagree.setChecked(false);
                    holder.radStrongAgree.setChecked(false);
                    holder.radDisagree.setChecked(false);
                    holder.radAgree.setChecked(false);
                }
            }
        });

    }

    @Override
    public void onViewRecycled(DoFeedbackQuestionAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listQuestion.size();
    }
}

