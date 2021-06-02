package common.serviceAPI;

import com.example.androidfeedback.ui.assignment.AddAssignmentModel;
import com.example.androidfeedback.ui.assignment.AssignmentModel;
import com.example.androidfeedback.ui.assignment.TrainerModel;
import com.example.androidfeedback.ui.enrollment.EnrollmentDetailModel;
import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.feedback.FeedbackViewModel;
import com.example.androidfeedback.ui.feedback.TopicFeedbackModel;
import com.example.androidfeedback.ui.feedback.TopicResult;
import com.example.androidfeedback.ui.feedback.TypeFeedbackModel;
import com.example.androidfeedback.ui.module.AddModuleSpinner;
import com.example.androidfeedback.ui.module.ModuleViewModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.statistic.CommentViewModel;
import com.example.androidfeedback.ui.statistic.PieBaseOnTopic;
import com.example.androidfeedback.ui.statistic.PieChartViewModel;
import com.example.androidfeedback.ui.statistic.StatisticDetail;
import com.example.androidfeedback.ui.statistic.StatisticViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;
import com.example.androidfeedback.ui.uiclass.trainee.ClassDetailModel;
import com.example.androidfeedback.ui.uiclass.trainee.DetailClassTrainee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallGet {
    @GET("api/question/{filter}")
    Call<List<QuestionViewModel>> getListQuestion(@Path("filter") String filter);

    @GET("api/classes")
    Call<List<ClassViewModel>> getListClass( @Query("role") String role ,  @Query("userId") String userId);

    @GET("api/enrollment/{filter}")
    Call<List<EnrollmentViewModel>> getListEnrollment(@Path("filter") String filter);

    @GET("api/module")
    Call<List<ModuleViewModel>> getListModule();

    @GET("api/module/add")
    Call<AddModuleSpinner> getListAddModule();

    @GET("api/feedback/")
    Call<List<FeedbackViewModel>> getListFeedback();

    @GET("api/statistic/admin")
    Call<StatisticViewModel> getSelectList();

    @GET("api/statistic/trainer")
    Call<StatisticViewModel> getSelectListForTrainer(@Query("trainerID") String trainerID);

    @GET("api/statistic/pie")
    Call<ArrayList<PieChartViewModel>> getPieChart(@Query("classID") int classID, @Query("moduleID") int moduleID);

    @GET("api/statistic/topic")
    Call<ArrayList<PieBaseOnTopic>> getPieChartBaseOnTopic(@Query("classID") int classID, @Query("moduleID") int moduleID);

    @GET("api/statistic/answer")
    Call<ArrayList<StatisticDetail>> getStatisticAnswer(@Query("classID") int classID, @Query("moduleID") int moduleID);

    @GET("api/statistic/comment")
    Call<ArrayList<CommentViewModel>> getListComment(@Query("classID") int classID, @Query("moduleID") int moduleID);
    @GET("api/feedback/add")
    Call<TopicResult> getListTopicFeedback();

    @GET("api/assignment")
    Call<List<AssignmentModel>> getListAssignment();

    @GET("api/assignment/getListTrainer")
    Call<List<TrainerModel>> getListTrainer();

    @GET("api/assignment/{inputText}")
    Call<List<AssignmentModel>> searchAssignment(@Path("inputText")String inputText);

    @GET("api/assignment/getId/{moduleName}/{className}/{trainerName}")
    Call<AddAssignmentModel> getAssigmentId(@Path("moduleName") String moduleName , @Path("className") String className, @Path("trainerName") String trainerName );


    @GET("api/enrollment/getDetail/{id}/{trainneID}")
    Call<EnrollmentDetailModel> getDetailEnrollment(@Path("id") String idClass , @Path("trainneID") String traineeID );

    @GET("api/module/trainee")
    Call<List<ModuleViewModel>> getListModuleTrainee(@Query("traineeID") String TraineeID );

    @GET("api/classes/detail")
    Call<List<ClassDetailModel>> getListClassDetail(@Query("id") int idClass , @Query("role") String role , @Query("userId") String userId );

    @GET("api/module/trainer")
    Call<List<ModuleViewModel>> getListModuleTrainer(@Query("trainerID") String TraineeID );

    @GET("api/assignment/trainer/{trainerId}")
    Call<List<AssignmentModel>> getListAssignmentByTrainer(@Path("trainerId") String trainerId);

    @GET("api/assignment/trainer/{trainerId}/{inputText}")
    Call<List<AssignmentModel>> searchAssignmentByTrainer(@Path("trainerId") String trainerId,@Path("inputText")String inputText);



}
