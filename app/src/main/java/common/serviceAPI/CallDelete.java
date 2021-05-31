package common.serviceAPI;

import com.example.androidfeedback.ui.assignment.AddAssignmentModel;
import com.example.androidfeedback.ui.assignment.AssignmentModel;
import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.feedback.FeedbackViewModel;
import com.example.androidfeedback.ui.module.ModuleViewModel;
import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallDelete {

    @DELETE("/api/assignment/{ClassId}/{ModuleId}/{TrainerId}")
    Call<AddAssignmentModel> deleteAssignment(@Path("ClassId") int ClassId , @Path("ModuleId") int ModuleId, @Path("TrainerId") String TrainerId );
    @DELETE("api/enrollment")
    Call<EnrollmentViewModel> deleteEnrollment(@Query("classId") int classId , @Query("traineeId") String traineeId );

    @DELETE("api/question/{id}")
    Call<QuestionViewModel> deleteQuestion(@Path("id") int questionId );

    @DELETE("api/module/{id}")
    Call<ModuleViewModel> deleteModule(@Path("id") int moduleId );

    @DELETE("api/classes/{id}")
    Call<ClassViewModel> deleteClass(@Path("id") int classId );


    @DELETE("api/feedback/{id}")
    Call<FeedbackViewModel> deleteFeedback(@Path("id") int feedback );
}
