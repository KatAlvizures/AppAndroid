package app.altamira.com.minitwitter.retrofit;

import app.altamira.com.minitwitter.retrofit.request.RequestAltamiraLogin;
import app.altamira.com.minitwitter.retrofit.response.ResponseAltamiraLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthAltamiraService {
    @POST("Kondiser/Kondiser")
    Call<ResponseAltamiraLogin> doLoginAltamira(@Query("token") String one, @Query("method") String two, @Body RequestAltamiraLogin requestAltamiraLogin);

}
