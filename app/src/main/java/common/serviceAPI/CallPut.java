package common.serviceAPI;

import com.example.androidfeedback.ui.question.QuestionViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallPut {
    @PUT("api/question/update")
    Call<QuestionViewModel> updateQuestionAPI(@Query("questionID") int questionID , @Query("questionContent") String questionContent);

    @PUT("api/classes/{id}")
    Call<ClassViewModel> updateClassAPI(@Path("id") int classId , @Body ClassViewModel classViewModel);

}
