package common.serviceAPI;

import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.List;

import common.ModelTestAPI;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CallPost {
    @GET("posts")
    Call<List<ModelTestAPI>> getClassAPI();
}
