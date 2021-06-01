package common.serviceAPI;

import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.feedback.FeedbackViewModel;
import com.example.androidfeedback.ui.module.ModuleViewModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.statistic.CommentViewModel;
import com.example.androidfeedback.ui.statistic.PieBaseOnTopic;
import com.example.androidfeedback.ui.statistic.PieChartViewModel;
import com.example.androidfeedback.ui.statistic.StatisticViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallGet {
    @GET("api/question")
    Call<List<QuestionViewModel>> getListQuestion();

    @GET("api/classes")
    Call<List<ClassViewModel>> getListClass( @Query("role") String role ,  @Query("userId") String userId);

    @GET("api/enrollment/{filter}")
    Call<List<EnrollmentViewModel>> getListEnrollment(@Path("filter") String filter);

    @GET("api/module")
    Call<List<ModuleViewModel>> getListModule();

    @GET("api/feedback/")
    Call<List<FeedbackViewModel>> getListFeedback();

    @GET("api/statistic/admin")
    Call<StatisticViewModel> getSelectList();

    @GET("api/statistic/pie")
    Call<ArrayList<PieChartViewModel>> getPieChart(@Query("classID") int classID, @Query("moduleID") int moduleID);

    @GET("api/statistic/topic")
    Call<ArrayList<PieBaseOnTopic>> getPieChartBaseOnTopic(@Query("classID") int classID, @Query("moduleID") int moduleID);

    @GET("api/statistic/comment")
    Call<ArrayList<CommentViewModel>> getListComment(@Query("classID") int classID, @Query("moduleID") int moduleID);

}
