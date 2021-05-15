package com.example.androidfeedback.ui.question;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.module.ModuleAdapter;
import com.example.androidfeedback.ui.module.ModuleViewModel;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    private Context context;
    ArrayList<QuestionViewModel> listQuestion;
    private int position;

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtQuestionContent, txtQuestionID,txtTopicId,txtTopicName;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtQuestionID = itemView.findViewById(R.id.txtQuestionID);
            txtQuestionContent = itemView.findViewById(R.id.txtQuestionContent);
            txtTopicId = itemView.findViewById(R.id.txtTopicID);
            txtTopicName = itemView.findViewById(R.id.txtTopicName);
        }
    }
    public QuestionAdapter(Context context, ArrayList<QuestionViewModel> listQuestion){
        this.context = context;
        this.listQuestion = listQuestion;
    }
    @NonNull
    @Override

    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_recycler_view_item, parent, false);
        return new QuestionAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final QuestionAdapter.ViewHolder holder, final int position){
        QuestionViewModel question = listQuestion.get(position);
        holder.txtTopicId.setText(String.valueOf(question.getTopicID()));
        holder.txtTopicName.setText(question.getTopicName());
        holder.txtQuestionID.setText(String.valueOf(question.getQuestionID()));
        holder.txtQuestionContent.setText(question.getQuestionContent());
    }

    @Override
    public void onViewRecycled(QuestionAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listQuestion.size();
    }
}
