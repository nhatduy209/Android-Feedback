package common.serviceAPI;

import com.example.androidfeedback.ui.assignment.AddAssignmentModel;
import com.example.androidfeedback.ui.assignment.AssignmentModel;
import com.example.androidfeedback.ui.assignment.TrainerModel;
import com.example.androidfeedback.ui.enrollment.EnrollmentDetailModel;
import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.feedback.FeedbackViewModel;
import com.example.androidfeedback.ui.module.AddModuleSpinner;
import com.example.androidfeedback.ui.module.ModuleViewModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

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
    Call<List<ClassViewModel>> getListClassDetail(@Query("id") String idClass ,@Query("role") String role ,  @Query("userId") String userId );

}
