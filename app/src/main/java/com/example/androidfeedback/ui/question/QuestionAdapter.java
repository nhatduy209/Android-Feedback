package com.example.androidfeedback.ui.question;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.module.ModuleAdapter;
import com.example.androidfeedback.ui.module.ModuleViewModel;

import java.util.ArrayList;

import common.serviceAPI.CallDelete;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        private Button btnEdit,btnDelete;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtQuestionID = itemView.findViewById(R.id.txtQuestionID);
            txtQuestionContent = itemView.findViewById(R.id.txtQuestionContent);
            txtTopicId = itemView.findViewById(R.id.txtTopicID);
            txtTopicName = itemView.findViewById(R.id.txtTopicName);
            btnEdit = itemView.findViewById(R.id.btnEditQuestion);
            btnDelete=itemView.findViewById(R.id.btnDeleteQuestion);
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
        final QuestionViewModel question = listQuestion.get(position);
        holder.txtTopicId.setText(String.valueOf(question.getTopicID()));
        holder.txtTopicName.setText(question.getTopicName());
        holder.txtQuestionID.setText(String.valueOf(question.getQuestionID()));
        holder.txtQuestionContent.setText(question.getQuestionContent());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),AddQuestion.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("topicId",question.getTopicID());
                intent.putExtra("topicName",question.getTopicName());
                intent.putExtra("questionID",question.getQuestionID());
                intent.putExtra("questionContent",question.getQuestionContent());
                intent.putExtra("isEditing", true);
                context.startActivity(intent);
            }
        });
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
                txtMessage.setText("Do you want to delete this question?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallDelete callDelete = retrofit.create(CallDelete.class);

                        Call<QuestionViewModel> deleteQuestion = callDelete
                                .deleteQuestion(question.getQuestionID());

                        // call callback
                        deleteQuestion.enqueue(new Callback<QuestionViewModel>() {
                            @Override
                            public void onResponse(Call<QuestionViewModel> call, Response<QuestionViewModel> response) {
                                String res = response.message();
                                Toast.makeText(context , response.body().getMessage(), Toast.LENGTH_LONG).show();
                                removeItem(question);
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<QuestionViewModel> call, Throwable t) {

                            }
                        });
                        dialog.dismiss();
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

    private void removeItem(QuestionViewModel question) {
        int currPosition = listQuestion.indexOf(question);
        listQuestion.remove(currPosition);
        notifyItemRemoved(currPosition);
    }
}
