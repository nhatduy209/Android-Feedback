package common.serviceAPI;

import com.example.androidfeedback.ui.login.LoginModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CallPost {

    @POST("api/authentication/login")
    Call<LoginModel> loginAPI(@Body LoginModel loginModel );

    @POST("api/question/add")
    Call<QuestionViewModel> addQuestionAPI(@Body QuestionViewModel questinViewModel );

    @POST("api/classes")
    Call<ClassViewModel> addClassAPI(@Body ClassViewModel classViewModel );

}
