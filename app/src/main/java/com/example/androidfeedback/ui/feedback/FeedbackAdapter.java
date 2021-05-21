package com.example.androidfeedback.ui.feedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.ViewHolder>  {
    private int position;
    private Context context;
    ArrayList<FeedbackViewModel> listFeedback;
    @NonNull
    @Override
    public FeedbackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_recycler_view_item, parent, false);
        return new FeedbackAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final FeedbackViewModel classes = listFeedback.get(position);
        holder.feedbackID.setText(classes.getFeedbackId());
        holder.feedbackTitle.setText(classes.getFeedbackTitle());
        holder.adminId.setText(classes.getAdminId());
    }


    public FeedbackAdapter(Context context, ArrayList<FeedbackViewModel> listClass){
        this.context = context;
        this.listFeedback = listClass;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView feedbackID, feedbackTitle, adminId ;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            feedbackID = itemView.findViewById(R.id.feedbackIDView);
            feedbackTitle = itemView.findViewById(R.id.feedbackTitle);
            adminId = itemView.findViewById(R.id.adminID);
        }
    }

    @Override
    public void onViewRecycled(FeedbackAdapter.ViewHolder holder) {
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
