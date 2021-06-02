package com.example.androidfeedback.ui.home;

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

import java.util.ArrayList;

public class FeedbackTraineeAdapter extends RecyclerView.Adapter<FeedbackTraineeAdapter.ViewHolder>  {
    private int position;
    private Context context;
    ArrayList<FeedbackTraineeViewModel> listFeedback;
    @NonNull
    @Override
    public FeedbackTraineeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_recycler_item_trainee, parent, false);
        return new FeedbackTraineeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final FeedbackTraineeViewModel feedback = listFeedback.get(position);
        holder.feedbackTitle.setText(feedback.getFeedbackTraineeTitle());
        holder.classID.setText(feedback.getTraineeClassID());
        holder.className.setText(feedback.getTraineeClassName());
        holder.moduleId.setText(feedback.getTraineeModuleID());
        String moduleName = "<b>Module Name: </b>" + feedback.getTraineeModuleName();
        holder.moduleName.setText(android.text.Html.fromHtml(moduleName));
        holder.endTime.setText(feedback.getTraineeEndTime());
        if(feedback.isStatus()){
            holder.status.setText("Complete");
//            holder.btnDoFeedback.setVisibility(View.GONE);
        }
        else
            holder.status.setText("Incomplete");
        holder.btnDoFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.btnDoFeedback.setVisibility(View.GONE);
//                String AdminID = feedbackes.getAdminId();
                Intent intent = new Intent(context, DoFeedback.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("li",AdminID);
                context.startActivity(intent);
            }
        });
    }


    public FeedbackTraineeAdapter(Context context, ArrayList<FeedbackTraineeViewModel> listFeedback){
        this.context = context;
        this.listFeedback = listFeedback;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView className,classID,moduleId,moduleName, feedbackTitle, endTime,status ;
        private Button btnDoFeedback;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            feedbackTitle = itemView.findViewById(R.id.txtHomeTraineeFeedbackTitle);
            classID = itemView.findViewById(R.id.txtHomeTraineeFeedbackTitle);
            className = itemView.findViewById(R.id.txtHomeTraineeClassName);
            moduleId = itemView.findViewById(R.id.txtHomeTraineeModuleID);
            moduleName = itemView.findViewById(R.id.txtHomeTraineeModuleName);
            endTime = itemView.findViewById(R.id.txtHomeTraineeEndTime);
            status = itemView.findViewById(R.id.txtHomeTraineeStatus);
            btnDoFeedback = itemView.findViewById(R.id.btnDoFeedbackTrainee);
        }
    }

    @Override
    public void onViewRecycled(FeedbackTraineeAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listFeedback.size();
    }

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
