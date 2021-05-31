package com.example.androidfeedback.ui.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.androidfeedback.MainActivity;
import com.example.androidfeedback.R;
import com.example.androidfeedback.ui.join.JoinViewModel;
import com.google.android.material.navigation.NavigationView;

import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LogoutDialog extends DialogFragment {
    private Button btnClose, btnLogout;
    private TextView textLogout ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.delete_layout, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnClose = view.findViewById(R.id.btnCancel);
        btnLogout = view.findViewById(R.id.btnYes);
        textLogout = view.findViewById(R.id.txtDeleteMessageSmall);
        final NavigationView navigationView = view.findViewById(R.id.nav_view);
        textLogout.setText("Do you want to LogOut");

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        getContext().getSharedPreferences("GetSession", Context.MODE_PRIVATE).edit().clear().apply();
                        Intent intent=new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                getDialog().dismiss();
            }
        });

    }

}
