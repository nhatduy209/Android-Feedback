package com.example.androidfeedback.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidfeedback.MainActivity;
import com.example.androidfeedback.R;

import common.Config;
import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin ;
    private Config config = new Config();
    private TextView username , password ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // disable button
                btnLogin.setEnabled(false);

                // get value user type

                LoginModel loginModel = new LoginModel(username.getText().toString(),password.getText().toString(), "Admin");
                // call API login
                Retrofit retrofit = RetrofitInstance.getClient();

            CallPost callPost = retrofit.create(CallPost.class);

            Call<LoginModel> login = callPost.loginAPI(loginModel);

            login.enqueue(new Callback<LoginModel>() {
                @Override
                public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                    String res = response.message();
                    Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<LoginModel> call, Throwable t) {
                    String err = t.getMessage();
                }
            });
            }
        });
    }
}
