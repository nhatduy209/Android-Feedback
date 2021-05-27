package com.example.androidfeedback.ui.uiclass;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfeedback.R;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private int position;
    private Context context;
    ArrayList<ClassViewModel> listClass;
    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_recycler_view_item, parent, false);
        return new ClassAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ClassAdapter.ViewHolder holder,final int position) {
        final ClassViewModel classes = listClass.get(position);
        holder.classId.setText(classes.getClassId());
        holder.className.setText(classes.getClassName());
        holder.startDate.setText(classes.getStartDate());
        holder.endDate.setText(classes.getEndDate());
        holder.capacity.setText(classes.getCapacity());
        holder.cbtest.setText(android.text.Html.fromHtml("<p>I am satisfied with the topic/course's content (tôi hài lòng với nội dung môn học/khóa học)</p>"));
        holder.btnEdit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context.getApplicationContext(), AddClass.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    // if not , error throw
                    intent.putExtra("className",classes.getClassName());
                    intent.putExtra("classId",classes.getClassName());
                    intent.putExtra("startDate",classes.getStartDate());
                    intent.putExtra("endDate",classes.getEndDate());
                    intent.putExtra("capacity",classes.getCapacity());
                    context.startActivity(intent);
            }
        });
    }

    public ClassAdapter(Context context, ArrayList<ClassViewModel> listClass){
        this.context = context;
        this.listClass = listClass;
    }

    //create view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements DatePickerDialog.OnDateSetListener {
        private TextView classId, className,startDate, endDate , capacity ;
        private Button btnEdit;
        private CheckBox cbtest;
        public ViewHolder(@NonNull View itemView){
        super(itemView);
            classId = itemView.findViewById(R.id.feedbackIDView);
            className = itemView.findViewById(R.id.feedbackTitle);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            capacity = itemView.findViewById(R.id.adminID);
            cbtest = itemView.findViewById(R.id.cbtest);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

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

}
