package app.altamira.com.minitwitter.retrofit;

import app.altamira.com.minitwitter.retrofit.request.RequestAltamiraLogin;
import app.altamira.com.minitwitter.retrofit.request.RequestLogin;
import app.altamira.com.minitwitter.retrofit.request.RequestSignup;
import app.altamira.com.minitwitter.retrofit.response.ResponseAltamiraLogin;
import app.altamira.com.minitwitter.retrofit.response.ResponseAuth;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MiniTwitterService {

    @POST("auth/login")
    Call<ResponseAuth> doLogin(@Body RequestLogin requestLogin);

    @POST("auth/signup")
    Call<ResponseAuth> doSignUp(@Body RequestSignup requestSignup);


}
