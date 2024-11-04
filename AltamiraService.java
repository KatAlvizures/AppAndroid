package app.altamira.com.minitwitter.retrofit;

import app.altamira.com.minitwitter.ResponseUsuarioInventario;
import app.altamira.com.minitwitter.retrofit.request.RequestAltamiraLogin;
import app.altamira.com.minitwitter.retrofit.request.RequestLogoutApp;
import app.altamira.com.minitwitter.retrofit.request.RequestMarcaInventario;
import app.altamira.com.minitwitter.retrofit.request.RequestUsuarioApp;
import app.altamira.com.minitwitter.retrofit.request.RequestUsuarioInventario;
import app.altamira.com.minitwitter.retrofit.response.ResponseAltamiraLogin;
import app.altamira.com.minitwitter.retrofit.response.ResponseLogoutApp;
import app.altamira.com.minitwitter.retrofit.response.ResponseMarcaInventario;
import app.altamira.com.minitwitter.retrofit.response.ResponseUsuarioApp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AltamiraService {

    @Headers({
            "Content-type: application/json",
            "Content-length: 50",
            "Authorization: Basic S29uZGlzZXI6SyEwbmQnMXMzUi4kMTA1Mw=="
    })
    @POST("Kondiser/Kondiser")
    Call<ResponseAltamiraLogin> doLoginAltamira(@Query("token") String one, @Query("method") String two, @Body RequestAltamiraLogin requestAltamiraLogin);

    @Headers({
            "Content-type: application/json",
            "Content-length: 50",
            "Authorization: Basic S29uZGlzZXI6SyEwbmQnMXMzUi4kMTA1Mw=="
    })
    @POST("Kondiser/Kondiser")
    Call<ResponseUsuarioInventario> doUsuarioInventario(@Query("token") String one, @Query("method") String two, @Body RequestUsuarioInventario requestUsuarioInventario);

    @Headers({
            "Content-type: application/json",
            "Content-length: 50",
            "Authorization: Basic S29uZGlzZXI6SyEwbmQnMXMzUi4kMTA1Mw=="
    })
    @POST("Kondiser/Kondiser")
    Call<ResponseMarcaInventario> doMarcaInventario(@Query("token") String one, @Query("method") String two, @Body RequestMarcaInventario requestMarcaInventario);

    @Headers({
            "Content-type: application/json",
            "Content-length: 50",
            "Authorization: Basic S29uZGlzZXI6SyEwbmQnMXMzUi4kMTA1Mw=="
    })
    @POST("Kondiser/Kondiser")
    Call<ResponseUsuarioApp> doUsuarioApp(@Query("token") String one, @Query("method") String two, @Body RequestUsuarioApp requestUsuarioApp);

    @Headers({
            "Content-type: application/json",
            "Content-length: 50",
            "Authorization: Basic S29uZGlzZXI6SyEwbmQnMXMzUi4kMTA1Mw=="
    })
    @POST("Kondiser/Kondiser")
    Call<ResponseLogoutApp> doLogoutUser(@Query("token") String one, @Query("method") String two, @Body RequestLogoutApp requestLogoutApp);


}
