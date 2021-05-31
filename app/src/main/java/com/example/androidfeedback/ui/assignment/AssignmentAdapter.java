package com.example.androidfeedback.ui.assignment;

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
import com.example.androidfeedback.ui.module.AddModule;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.ArrayList;
import java.util.List;

import common.serviceAPI.CallDelete;
import common.serviceAPI.CallGet;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AssignmentAdapter extends RecyclerView.Adapter<AssignmentAdapter.ViewHolder> {


    private Context context;
    ArrayList<AssignmentModel> listAssignment;
    private int position;
    

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public AssignmentAdapter(Context context, ArrayList<AssignmentModel> listAssignment){
        this.context = context;
        this.listAssignment = listAssignment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_recyler_view_item, parent, false);
        return new AssignmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String no = String.valueOf(position+1);
        final AssignmentModel assignment = listAssignment.get(position);
        holder.txtId.setText(no);
        holder.txtModuleName.setText(assignment.getModuleName());
        holder.txtClassName.setText(assignment.getClassName());
        holder.txtTrainerName.setText(assignment.getTrainerName());
        holder.txtRegistrationCode.setText(assignment.getRegistrationCode());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // call get api to get 3 id of assignment
                Retrofit retrofit = RetrofitInstance.getClient();

                CallGet callGet = retrofit.create(CallGet.class);
                Call<AddAssignmentModel> assDb = callGet.getAssigmentId(assignment.getModuleName(),assignment.getClassName(),assignment.getTrainerName());

                assDb.enqueue(new Callback<AddAssignmentModel>() {
                    @Override
                    public void onResponse(Call<AddAssignmentModel> call, Response<AddAssignmentModel> response) {
                        AddAssignmentModel result = response.body();
                        assignment.setModuleId(result.moduleId);
                        assignment.setClassId(result.classId);
                        assignment.setTrainerId(result.trainerId);
                    }
                    @Override
                    public void onFailure(Call<AddAssignmentModel> call, Throwable t) {

                    }

                });
                Intent intent = new Intent(context.getApplicationContext(), EditAssignment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("classID",assignment.getClassId());
                intent.putExtra("className",assignment.getClassName());
                intent.putExtra("moduleID",assignment.getModuleId());
                intent.putExtra("moduleName",assignment.getModuleName());
                intent.putExtra("trainerID", assignment.getTrainerId());
                intent.putExtra("trainerName",assignment.getTrainerName());
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
                txtMessage.setText("Do you want to delete this assignment?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // call get api to get 3 id of assignment
                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallGet callGet = retrofit.create(CallGet.class);
                        Call<AddAssignmentModel> assDb = callGet.getAssigmentId(assignment.getModuleName(),assignment.getClassName(),assignment.getTrainerName());

                        assDb.enqueue(new Callback<AddAssignmentModel>() {
                            @Override
                            public void onResponse(Call<AddAssignmentModel> call, Response<AddAssignmentModel> response) {
                                AddAssignmentModel result = response.body();
                                assignment.setModuleId(result.moduleId);
                                assignment.setClassId(result.classId);
                                assignment.setTrainerId(result.trainerId);
                            }
                            @Override
                            public void onFailure(Call<AddAssignmentModel> call, Throwable t) {

                            }

                        });

                        CallDelete callDelete = retrofit.create(CallDelete.class);


                        Call<AddAssignmentModel> deleteAssignment = callDelete.deleteAssignment(assignment.getClassId(),assignment.getModuleId(),assignment.getTrainerId());

                        // call callback
                        deleteAssignment.enqueue(new Callback<AddAssignmentModel>() {
                            @Override
                            public void onResponse(Call<AddAssignmentModel> call, Response<AddAssignmentModel> response) {
                                String res = response.message();
                                Toast.makeText(context , response.body().getMessage(), Toast.LENGTH_LONG).show();
                                removeItem(assignment);
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<AddAssignmentModel> call, Throwable t) {

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
    public int getItemCount() {
        return listAssignment.size();
    }
    private void removeItem(AssignmentModel assignmentModel) {
        int currPosition = listAssignment.indexOf(assignmentModel);
        listAssignment.remove(currPosition);
        notifyItemRemoved(currPosition);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView  txtId, txtModuleName, txtClassName, txtTrainerName, txtRegistrationCode;
        private Button btnEdit,btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId =itemView.findViewById(R.id.txtAssId);
            txtModuleName =itemView.findViewById(R.id.txtAssModuleName);
            txtClassName =itemView.findViewById(R.id.txtAssClassName);
            txtTrainerName =itemView.findViewById(R.id.txtAssTrainerName);
            txtRegistrationCode =itemView.findViewById(R.id.txtRegistrationCode);
            btnEdit = itemView.findViewById(R.id.btnEditAssignment);
            btnDelete = itemView.findViewById(R.id.btnDeleteAssignment);

        }
    }

}
