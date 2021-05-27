package com.example.androidfeedback.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidfeedback.MainActivity;
import com.example.androidfeedback.R;

import common.ValidationEditText;
import common.serviceAPI.CallPost;
import common.serviceAPI.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
public class LoginActivity extends AppCompatActivity {
    private Button btnLogin ;
    private TextView username , password , errorUsername , errorPassword  ;
    private CheckBox rememberMe;
    private Boolean saveLogin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        username = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtPassword);
        errorUsername = findViewById(R.id.vldUsername);
        errorPassword = findViewById(R.id.vldPassword);


        // handle remember me
        rememberMe = findViewById(R.id.cbRememberMe);
               SharedPreferences loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
               final SharedPreferences.Editor loginPrefsEditor = loginPreferences.edit();
               saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            username.setText(loginPreferences.getString("username", ""));
            password.setText(loginPreferences.getString("password", ""));
            rememberMe.setChecked(true);
        }


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // disable button
                btnLogin.setEnabled(false);

                // validate data
                ValidationEditText validate = new ValidationEditText();
                boolean canAddUsername = validate.validateTextView(username,errorUsername );
                boolean canAddPassword = validate.validateTextView(password,errorPassword );

                if( canAddUsername == false || canAddPassword == false )
                {
                    return;
                }

                // remember me handler
                if (rememberMe.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", username.getText().toString());
                    loginPrefsEditor.putString("password", password.getText().toString());
                } else {
                    loginPrefsEditor.clear();
                }
                loginPrefsEditor.commit();


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
                    if( response.body().getSuccess()){
                        // sharedPreferences and get Session
                        SharedPreferences sharedPreferences= LoginActivity.this.getSharedPreferences("GetSession", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userId", response.body().getUserId() );
                        editor.putString("userName", response.body().getUsername() );
                        editor.putString("role", response.body().getRole() );
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this , MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this ,  response.body().getMessage(), Toast.LENGTH_LONG).show();
                        return;
                    }

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
