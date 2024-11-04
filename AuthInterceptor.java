package app.altamira.com.minitwitter.retrofit;

import java.io.IOException;

import app.altamira.com.minitwitter.common.Constantes;
import app.altamira.com.minitwitter.common.SharedPreferencesManager;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = SharedPreferencesManager.getSomeStringValue(Constantes.API_AUTORIZATION);
        Request request = chain.request().newBuilder().addHeader("Authorization", "Basic "+token).build();
        return chain.proceed(request);
    }
}
