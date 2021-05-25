package common.serviceAPI;

import com.example.androidfeedback.ui.question.QuestionViewModel;

import java.util.List;

import common.ModelTestAPI;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CallGet {
    @GET("api/question")
    Call<List<QuestionViewModel>> getListQuestion();
}
