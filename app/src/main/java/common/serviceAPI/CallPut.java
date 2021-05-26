package common.serviceAPI;

import com.example.androidfeedback.ui.question.QuestionViewModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface CallPut {
    @PUT("api/question/update")
    Call<QuestionViewModel> updateQuestionAPI(@Body QuestionViewModel questionViewModel );
}
