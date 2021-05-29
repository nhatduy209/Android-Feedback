package com.example.androidfeedback.ui.module;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
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

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder>{
    private Context context;
    ArrayList<ModuleViewModel> listModule;
    private int position;

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements DatePickerDialog.OnDateSetListener{
        private TextView txtModuleID, txtModuleName,txtModuleAdminID, txtModuleStartDate;
        private TextView txtModuleEndDate, txtModuleFBTitle,txtModuleFBStartDate, txtModuleFBEndDate;
        private Button btnEdit,btnDelete;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtModuleID = itemView.findViewById(R.id.txtModuleID);
            txtModuleName = itemView.findViewById(R.id.txtModuleName);
            txtModuleAdminID = itemView.findViewById(R.id.txtModuleAdminID);
            txtModuleStartDate = itemView.findViewById(R.id.txtModuleStartDate);
            txtModuleEndDate = itemView.findViewById(R.id.txtModuleEndDate);
            txtModuleFBTitle = itemView.findViewById(R.id.txtModuleFBTitle);
            txtModuleFBStartDate = itemView.findViewById(R.id.txtModuleFBStartDate);
            txtModuleFBEndDate = itemView.findViewById(R.id.txtModuleFBEndDate);
            btnDelete = itemView.findViewById(R.id.btnDeleteModule);
            btnEdit = itemView.findViewById(R.id.btnEditModule);
        }
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        }
    }
    public ModuleAdapter(Context context, ArrayList<ModuleViewModel> listModule){
        this.context = context;
        this.listModule = listModule;
    }
    @NonNull
    @Override

    public ModuleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_recycler_view_item, parent, false);
        return new ModuleAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ModuleAdapter.ViewHolder holder, final int position){
        final ModuleViewModel module = listModule.get(position);
        holder.txtModuleID.setText(String.valueOf(module.getModuleId()));
        holder.txtModuleName.setText(module.getModuleName());
        holder.txtModuleAdminID.setText(String.valueOf(module.getAdminId()));
        holder.txtModuleStartDate.setText(module.getStartDate());
        holder.txtModuleEndDate.setText(module.getEndDate());
        holder.txtModuleFBTitle.setText(module.getFbTitle());
        holder.txtModuleFBStartDate.setText(module.getFbStartDate());
        holder.txtModuleFBEndDate.setText(module.getFbEndDate());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(),AddModule.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("moduleID",module.getModuleId());
                intent.putExtra("moduleName",module.getModuleName());
                intent.putExtra("adminID",String.valueOf(module.getAdminId()));
                intent.putExtra("fbTitle",module.getFbTitle());
                intent.putExtra("endDate",module.getEndDate());
                intent.putExtra("startDate",module.getStartDate());
                intent.putExtra("fbEndDate",module.getFbEndDate());
                intent.putExtra("fbStartDate",module.getFbStartDate());
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
                txtMessage.setText("Do you want to delete this module?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallDelete callDelete = retrofit.create(CallDelete.class);

                        Call<ModuleViewModel> deleteModule = callDelete
                                .deleteModule(module.getModuleId());

                        // call callback
                        deleteModule.enqueue(new Callback<ModuleViewModel>() {
                            @Override
                            public void onResponse(Call<ModuleViewModel> call, Response<ModuleViewModel> response) {
                                String res = response.message();
                                Toast.makeText(context , response.body().getMessage(), Toast.LENGTH_LONG).show();
                                removeItem(module);
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<ModuleViewModel> call, Throwable t) {

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
    public void onViewRecycled(ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listModule.size();
    }

    private void removeItem(ModuleViewModel module) {
        int currPosition = listModule.indexOf(module);
        listModule.remove(currPosition);
        notifyItemRemoved(currPosition);
    }
}
