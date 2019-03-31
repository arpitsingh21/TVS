package ne.application.com.tvs;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Harikesh on 28/03/2019.
 */
public interface TvsAPI {

    @POST("/reporting/vrm/api/test_new/int/gettabledata.php")
    Call<LoginResponsePojo> getLogin(@Body LoginBodyPojo loginBodyPojo);
}
