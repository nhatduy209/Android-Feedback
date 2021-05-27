package common.serviceAPI;

import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.List;

import common.ModelTestAPI;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CallGet {
    @GET("api/question")
    Call<List<QuestionViewModel>> getListQuestion();

    @GET("api/classes")
    Call<List<ClassViewModel>> getListClass();

    @GET("api/enrollment")
    Call<List<EnrollmentViewModel>> getListEnrollment();
}
