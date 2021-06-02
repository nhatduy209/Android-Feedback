package com.example.androidfeedback.ui.feedback;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

import common.serviceAPI.CallDelete;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final FeedbackViewModel feedback = listFeedback.get(position);
        String title = "<b>Feedback Title: </b>" + feedback.getFeedbackTitle();
        String id = "<b>Admin ID: </b>" + feedback.getAdminId();
        holder.feedbackID.setText(String.valueOf(feedback.getFeedbackId()));
        holder.feedbackTitle.setText(android.text.Html.fromHtml(title));
        holder.adminId.setText(android.text.Html.fromHtml(id));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());//khởi tạo alert
                View v = View.inflate(context,R.layout.delete_layout,null);
                Button btnYes = v.findViewById(R.id.btnYes);
                Button btnCancel = v.findViewById(R.id.btnCancel);
                TextView txtMessage = v.findViewById(R.id.txtDeleteMessageSmall);

                alert.setView(v);
                final AlertDialog dialog = alert.create();
                txtMessage.setText("Do you want to delete this item?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallDelete callDelete = retrofit.create(CallDelete.class);

                        Call<FeedbackViewModel> deleteFeedback = callDelete
                                .deleteFeedback(feedback.getFeedbackId());

                        // call callback
                        deleteFeedback.enqueue(new Callback<FeedbackViewModel>() {
                            @Override
                            public void onResponse(Call<FeedbackViewModel> call, Response<FeedbackViewModel> response) {
                                String res = response.message();
                                Toast.makeText(context , response.body().getMessage(), Toast.LENGTH_LONG).show();
                                removeItem(feedback);
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<FeedbackViewModel> call, Throwable t) {

                            }
                        });
                        dialog.show();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String AdminID = feedback.getAdminId();
                Intent intent = new Intent(context, EditFeedBack.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("AdminID",AdminID);
                context.startActivity(intent);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String AdminID = feedback.getAdminId();
                Intent intent = new Intent(context, AddFeedback.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("li",AdminID);
                context.startActivity(intent);
            }
        });
    }


    public FeedbackAdapter(Context context, ArrayList<FeedbackViewModel> listClass){
        this.context = context;
        this.listFeedback = listClass;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView feedbackID, feedbackTitle, adminId ;
        private Button btnEdit,btnDetail,btnDelete;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            btnDetail = itemView.findViewById(R.id.btn_feebackDetail);
            btnEdit = itemView.findViewById(R.id.btn_editFeedback);
            btnDelete = itemView.findViewById(R.id.btn_deleteFeedback);
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

    private void removeItem(FeedbackViewModel feedback) {
        int currPosition = listFeedback.indexOf(feedback);
        listFeedback.remove(currPosition);
        notifyItemRemoved(currPosition);
    }
}
