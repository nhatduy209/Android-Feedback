package common.serviceAPI;

import com.example.androidfeedback.ui.login.LoginModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.List;

import common.ModelTestAPI;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface CallPost {

    @POST("api/authentication/login")
    Call<LoginModel> loginAPI(@Body LoginModel loginModel );

    @POST("api/question/add")
    Call<QuestionViewModel> addQuestionAPI(@Body QuestionViewModel questinViewModel );

}
