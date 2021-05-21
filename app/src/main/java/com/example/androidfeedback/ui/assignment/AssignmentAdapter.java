package com.example.androidfeedback.ui.assignment;

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
import com.example.androidfeedback.ui.module.AddModule;

import java.util.ArrayList;

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
        final AssignmentModel assignment = listAssignment.get(position);
        holder.txtId.setText(assignment.getModuleName());
        holder.txtModuleName.setText(assignment.getModuleName());
        holder.txtClassName.setText(assignment.getClassName());
        holder.txtTrainerName.setText(assignment.getTrainerID());
        holder.txtRegistrationCode.setText(assignment.getRegistrationCode());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), EditAssignment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("moduleID",module.getModuleId());
                intent.putExtra("moduleName",assignment.getModuleName());
                intent.putExtra("className",assignment.getClassName());
//                intent.putExtra("classID",assignment.get);
                intent.putExtra("trainerID",assignment.getTrainerID());
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
