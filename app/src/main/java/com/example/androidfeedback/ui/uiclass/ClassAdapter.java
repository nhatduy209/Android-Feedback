package com.example.androidfeedback.ui.uiclass;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
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

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private int position;
    private Context context;
    ArrayList<ClassViewModel> listClass;
    private String role ;
    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_recycler_view_item, parent, false);
        return new ClassAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClassAdapter.ViewHolder holder,final int position) {
        final ClassViewModel classes = listClass.get(position);
        holder.classId.setText(String.valueOf(classes.getClassId()));
        holder.className.setText(classes.getClassName());
        holder.startDate.setText(classes.getStartDate());
        holder.endDate.setText(classes.getEndDate());
        holder.capacity.setText(classes.getCapacity());
        holder.numberOfTrainee.setText(String.valueOf(classes.getNumberOfTrainee()));
        holder.btnEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), AddClass.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    // if not , error throw
                    intent.putExtra("className",classes.getClassName());
                    intent.putExtra("classId",classes.getClassId());
                    intent.putExtra("startDate",classes.getStartDate());
                    intent.putExtra("endDate",classes.getEndDate());
                    intent.putExtra("capacity",classes.getCapacity());
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
                txtMessage.setText("Do you want to delete this class ?");
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Retrofit retrofit = RetrofitInstance.getClient();

                        CallDelete callDelete = retrofit.create(CallDelete.class);

                        Call<ClassViewModel> deleteClass = callDelete
                                .deleteClass(classes.getClassId());

                        // call callback
                        deleteClass.enqueue(new Callback<ClassViewModel>() {
                            @Override
                            public void onResponse(Call<ClassViewModel> call, Response<ClassViewModel> response) {
                                String res = response.message();
                                Toast.makeText(context , response.body().getMessage(), Toast.LENGTH_LONG).show();
                                removeItem(classes);
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<ClassViewModel> call, Throwable t) {

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

    public ClassAdapter(Context context, ArrayList<ClassViewModel> listClass){
        this.context = context;
        this.listClass = listClass;
        // get seesion
        SharedPreferences pref = context.getSharedPreferences("GetSession",Context.MODE_PRIVATE);
        role  = pref.getString("role", "");
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView classId, className,startDate, endDate , capacity , numberOfTrainee,
        textViewCapacity,textViewStartDate,textViewEndDate,textViewNumberOfTrainee;
        private Button btnEdit , btnDelete , btnDetail;
        public ViewHolder(@NonNull View itemView){
        super(itemView);
            classId = itemView.findViewById(R.id.feedbackIDView);
            className = itemView.findViewById(R.id.feedbackTitle);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            capacity = itemView.findViewById(R.id.adminID);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            numberOfTrainee = itemView.findViewById(R.id.numberOfTrainee);
            btnDetail = itemView.findViewById(R.id.btn_classDetailTrainee);
            textViewCapacity = itemView.findViewById(R.id.textView7);
                    textViewStartDate =itemView.findViewById(R.id.textView8);
                    textViewEndDate =  itemView.findViewById(R.id.textView9);
                            textViewNumberOfTrainee =   itemView.findViewById(R.id.textView10);
            switch(role){
                case "Admin" :
                    btnDetail.setVisibility(itemView.INVISIBLE);
                    numberOfTrainee.setVisibility(itemView.GONE);
                    textViewNumberOfTrainee.setVisibility(itemView.GONE);
                    break;
                default :
                    btnEdit.setVisibility(itemView.GONE);
                    startDate.setVisibility(itemView.GONE);
                    endDate.setVisibility(itemView.GONE);
                    btnDelete.setVisibility(itemView.GONE);
                    capacity.setVisibility(itemView.GONE);
                    textViewCapacity.setVisibility(itemView.GONE);
                    textViewStartDate.setVisibility(itemView.GONE);
                    textViewEndDate.setVisibility(itemView.GONE);
                    btnEdit.setVisibility(itemView.GONE);
                    break;
            }
        }
    }

    @Override
    public void onViewRecycled(ClassAdapter.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return listClass.size();
    }

    //get position of item
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private void removeItem(ClassViewModel singleClass ) {
        int currPosition = listClass.indexOf(singleClass);
        listClass.remove(currPosition);
        notifyItemRemoved(currPosition);
    }

}
