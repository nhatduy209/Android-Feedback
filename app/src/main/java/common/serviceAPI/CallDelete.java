package common.serviceAPI;

import com.example.androidfeedback.ui.enrollment.EnrollmentViewModel;
import com.example.androidfeedback.ui.uiclass.ClassViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallDelete {

    @DELETE("api/enrollment")
    Call<EnrollmentViewModel> deleteEnrollment(@Query("classId") int classId , @Query("traineeId") String traineeId );
}
