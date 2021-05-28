package com.example.androidfeedback.ui.join;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.androidfeedback.R;

import java.util.Random;
import java.util.zip.Inflater;

public class DialogFragmentJoin extends DialogFragment {
    private Button btnClose, btnSubmit;
    private TextView txtCode;

    public View onCreateView(@NonNull LayoutInflater inflater,
                         ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.join_layout, null);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnClose = view.findViewById(R.id.btnCloseJoin);
        btnSubmit = view.findViewById(R.id.btnSubmitJoin);
        txtCode = view.findViewById(R.id.txtCodeInput);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(view.getContext());//khởi tạo alert
                Random random = new Random();
                int r = random.nextInt(1);
                View v = View.inflate(getContext(),R.layout.add_fail_layout,null);

                Button btnOK = v.findViewById(R.id.btnOKAddFail);
                TextView txtMessage = v.findViewById(R.id.txtAddFailMessageSmall);

                alert.setView(v);
                final AlertDialog dialog = alert.create();
                if(r==1){
                    txtMessage.setText("Invalid Registation Code");
                }
                else{
                    txtMessage.setText("You already join this module, please try another!!!");
                }

                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(view.getContext());//khởi tạo alert
                View v = View.inflate(getContext(),R.layout.add_success_layout,null);
                Button btnOK = v.findViewById(R.id.btnAddSuccess);
                TextView txtMessage = v.findViewById(R.id.txtAddSuccess);
                alert.setView(v);
                final AlertDialog dialog = alert.create();
                txtMessage.setTextSize(18);
                txtMessage.setText("Join Succeed!");
                btnOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        getDialog().dismiss();
    }
}
