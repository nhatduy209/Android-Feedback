package common.serviceAPI;

import com.example.androidfeedback.ui.login.LoginModel;

import java.util.List;

import common.ModelTestAPI;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CallPost {
    @GET("weatherforecast")
    Call<List<ModelTestAPI>> getClassAPI();

    @POST("api/authentication/login")
    Call<LoginModel> loginAPI(@Body LoginModel loginModel );
}
