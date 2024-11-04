package app.altamira.com.minitwitter.retrofit;

import app.altamira.com.minitwitter.common.Constantes;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthAltamiraClient {
    private static AuthAltamiraClient instance = null;
    private AuthAltamiraService authAltamiraService;
    private Retrofit retrofit;

    public AuthAltamiraClient() {
        // Incluir en la cabecera de la petición el TOKEN que autoriza al usuario
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new AuthInterceptor());
        OkHttpClient cliente = okHttpClientBuilder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_ALTAMIRA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build();

        authAltamiraService = retrofit.create(AuthAltamiraService.class);
    }

    // Patrón Singleton
    public static AuthAltamiraClient getInstance() {
        if(instance == null) {
            instance = new AuthAltamiraClient();
        }
        return instance;
    }

    public AuthAltamiraService getAuthTwitterService() {
        return authAltamiraService;
    }
}
