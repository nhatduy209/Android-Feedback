package com.example.androidfeedback.ui.join;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.Random;
import java.util.zip.Inflater;

import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
//
//        FrameLayout fl = (FrameLayout) getActivity().findViewById(this.getId());
//        fl.removeAllViews();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if(!txtCode.getText().toString().isEmpty())
                {
                    // get seesion
                    SharedPreferences pref = getActivity().getSharedPreferences("GetSession", Context.MODE_PRIVATE);
                    String userId = pref.getString("userId", "");
                    String userName = pref.getString("userName", "");
                    String role = pref.getString("role", "");

                    JoinViewModel joinViewModel = new JoinViewModel(userId, txtCode.getText().toString());

                    Retrofit retrofit = RetrofitInstance.getClient();

                    CallPost callPost = retrofit.create(CallPost.class);

                    Call<JoinViewModel> addQuestion = callPost.registrationAPI(joinViewModel);

                    // call callback
                    addQuestion.enqueue(new Callback<JoinViewModel>() {
                        @Override
                        public void onResponse(Call<JoinViewModel> call, Response<JoinViewModel> response) {
                            String res = response.message();

                            if (response.body().isSuccess()) {
                                android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(view.getContext());//khởi tạo alert
                                View v = View.inflate(getContext(), R.layout.add_success_layout, null);
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
                                        getDialog().dismiss();
                                    }
                                });
                                dialog.show();
                            } else {
                                android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(view.getContext());//khởi tạo alert
                                View v = View.inflate(getContext(), R.layout.add_fail_layout, null);

                                Button btnOK = v.findViewById(R.id.btnOKAddFail);
                                TextView txtMessage = v.findViewById(R.id.txtAddFailMessageSmall);

                                alert.setView(v);
                                final AlertDialog dialog = alert.create();
                                if (response.body().getMessage().contains("already")) {
                                    txtMessage.setText("You already join this module, please try another!!!");
                                } else {
                                    txtMessage.setText("Invalid Registation Code");
                                }

                                btnOK.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        dialog.dismiss();
                                        getDialog().dismiss();
                                    }
                                });
                                dialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<JoinViewModel> call, Throwable t) {

                        }
                    });
                }
            }
        });
        getDialog().dismiss();
    }
}
