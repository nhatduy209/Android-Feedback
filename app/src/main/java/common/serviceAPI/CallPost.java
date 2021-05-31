package common.serviceAPI;

import com.example.androidfeedback.ui.assignment.AddAssignmentModel;
import com.example.androidfeedback.ui.assignment.AssignmentModel;
import com.example.androidfeedback.ui.assignment.EditAssignmentModel;
import com.example.androidfeedback.ui.join.JoinViewModel;
import com.example.androidfeedback.ui.login.LoginModel;
import com.example.androidfeedback.ui.module.ModuleViewModel;
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

    @POST("api/module/add")
    Call<ModuleViewModel> addModuleAPI(@Body ModuleViewModel moduleViewModel );

    @POST("api/assignment")
    Call<AddAssignmentModel> addAssignmentAPI(@Body AddAssignmentModel addAssignmentModel );

    @POST("api/registration")
    Call<JoinViewModel> registrationAPI(@Body JoinViewModel joinViewModel );
}
