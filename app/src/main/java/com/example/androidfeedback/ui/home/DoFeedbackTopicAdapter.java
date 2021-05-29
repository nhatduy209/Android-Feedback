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
    private int temp = 0;
    @NonNull
    @Override
    public DoFeedbackTopicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.part_b_radio_item, parent, false);
        return new DoFeedbackTopicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DoFeedbackTopicAdapter.ViewHolder holder, int position) {
//        final FeedbackViewModel feedbacks = listFeedback.get(position);
        int topic = listTopic[position];
        if(temp==topic){
            holder.txtTopicName.setVisibility(View.GONE);
        }
        else {
            temp = topic;
            holder.txtTopicName.setText(String.valueOf(topic));
        }
        holder.txtQuestionContent.setText("merge");
        holder.radStronglyDisagree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                holder.radUnDecided.setSelected(false);
                holder.radStrongAgree.setSelected(false);
                holder.radDisagree.setSelected(false);
                holder.radAgree.setSelected(false);
            }
        });
        holder.radDisagree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                holder.radUnDecided.setSelected(false);
                holder.radStrongAgree.setSelected(false);
                holder.radStronglyDisagree.setSelected(false);
                holder.radAgree.setSelected(false);
            }
        });
        holder.radAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                holder.radUnDecided.setSelected(false);
                holder.radStrongAgree.setSelected(false);
                holder.radDisagree.setSelected(false);
                holder.radStronglyDisagree.setSelected(false);
            }
        });
        holder.radStrongAgree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                holder.radUnDecided.setSelected(false);
                holder.radStronglyDisagree.setSelected(false);
                holder.radDisagree.setSelected(false);
                holder.radAgree.setSelected(false);
            }
        });
        holder.radUnDecided.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                holder.radStronglyDisagree.setSelected(false);
                holder.radStrongAgree.setSelected(false);
                holder.radDisagree.setSelected(false);
                holder.radAgree.setSelected(false);
            }
        });
//        ArrayList<QuestionViewModel> listQuestion = new ArrayList<QuestionViewModel>();
//        QuestionViewModel question = new QuestionViewModel(1,"dui dẻ hong quạo",topic,"ai biết");
//        listQuestion.add(question);
//        listQuestion.add(question);
//        listQuestion.add(question);
//        holder.recyclerView

    }


    public DoFeedbackTopicAdapter(Context context, int[] listTopic){
        this.context = context;
        this.listTopic = listTopic;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTopicName;
        private RadioButton radStronglyDisagree, radUnDecided,radStrongAgree, radDisagree,radAgree;
        private TextView txtQuestionContent;
//        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTopicName = itemView.findViewById(R.id.txtTopicQuestionDoFeedback);
//            recyclerView = itemView.findViewById(R.id.recyclerQuestionDoFeedback);
            radAgree = itemView.findViewById(R.id.radAgree);
            radDisagree = itemView.findViewById(R.id.radDisAgree);
            radStrongAgree = itemView.findViewById(R.id.radStrongAgree);
            radStronglyDisagree = itemView.findViewById(R.id.radStronglyDisagree);
            radUnDecided = itemView.findViewById(R.id.radUnDecided);
            txtQuestionContent = itemView.findViewById(R.id.txtQuestionContentDoFeedback);
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
