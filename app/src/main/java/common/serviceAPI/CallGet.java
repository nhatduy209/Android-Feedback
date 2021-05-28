package common.serviceAPI;

import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.module.ModuleViewModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.List;

import common.ModelTestAPI;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallGet {
    @GET("api/question")
    Call<List<QuestionViewModel>> getListQuestion();

    @GET("api/classes")
    Call<List<ClassViewModel>> getListClass( @Query("role") String role ,  @Query("userId") String userId);

    @GET("api/enrollment")
    Call<List<EnrollmentViewModel>> getListEnrollment();

    @GET("api/module")
    Call<List<ModuleViewModel>> getListModule();

}
