package com.example.androidfeedback.ui.enrollment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.module.ModuleAdapter;
import com.example.androidfeedback.ui.module.ModuleViewModel;
import com.example.androidfeedback.ui.question.AddQuestion;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;

import common.serviceAPI.CallDelete;
import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EnrollmentAdapter extends RecyclerView.Adapter<EnrollmentAdapter.ViewHolder>{
    private Context context;
    ArrayList<EnrollmentViewModel> listEnrollmnent;
    private int position;

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTraineeID, txtTraineeName,txtClassId,txtClassName;
        private Button btnEdit,btnDelete,btnDetail;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtTraineeID = itemView.findViewById(R.id.txtEnTraineeID);
            txtTraineeName = itemView.findViewById(R.id.txtEnTraineeName);
            txtClassId = itemView.findViewById(R.id.txtEnClassID);
            txtClassName = itemView.findViewById(R.id.txtEnClassName);
            btnEdit = itemView.findViewById(R.id.btnEnrollmentEdit);
            btnDelete=itemView.findViewById(R.id.btnEnrollmentDelete);
            btnDetail=itemView.findViewById(R.id.btnEnrollmentDetail);
        }
    }
    public EnrollmentAdapter(Context context, ArrayList<EnrollmentViewModel> listEnrollmnent){
        this.context = context;
        this.listEnrollmnent = listEnrollmnent;
    }
    @NonNull
    @Override

    public EnrollmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.enrollment_recycler_view_item, parent, false);
        return new EnrollmentAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final EnrollmentAdapter.ViewHolder holder, final int position){
        final EnrollmentViewModel enrollment = listEnrollmnent.get(position);
        holder.txtTraineeID.setText(enrollment.getTraineeID());
        holder.txtTraineeName.setText(enrollment.getTrainerName());
        holder.txtClassId.setText(enrollment.getClassID());
        holder.txtClassName.setText(enrollment.getClassName());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),DetailEnrollment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("traineeID",enrollment.getTraineeID());
                intent.putExtra("classID",enrollment.getClassID());
                context.startActivity(intent);
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),EditEnrollment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("traineeID",enrollment.getTraineeID());
                intent.putExtra("traineeName",enrollment.getTrainerName());
                intent.putExtra("classID",enrollment.getClassID());
                intent.putExtra("className",enrollment.getClassName());
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
                txtMessage.setText("Do you want to delete this enrollment?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallDelete callDelete = retrofit.create(CallDelete.class);

                        Call<EnrollmentViewModel> deleteEnrollment = callDelete
                                .deleteEnrollment(Integer.parseInt(enrollment.getClassID().toString()),enrollment.getTraineeID().toString());

                        // call callback
                        deleteEnrollment.enqueue(new Callback<EnrollmentViewModel>() {
                            @Override
                            public void onResponse(Call<EnrollmentViewModel> call, Response<EnrollmentViewModel> response) {
                                String res = response.message();
                                removeItem(enrollment);
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<EnrollmentViewModel> call, Throwable t) {

                            }
                        });
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
    public void onViewRecycled(EnrollmentAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listEnrollmnent.size();
    }

    // This removes the data from our Dataset and Updates the Recycler View.
    private void removeItem(EnrollmentViewModel enrollment) {
        int currPosition = listEnrollmnent.indexOf(enrollment);
        listEnrollmnent.remove(currPosition);
        notifyItemRemoved(currPosition);
    }
}
