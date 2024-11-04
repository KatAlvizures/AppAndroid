package app.altamira.com.minitwitter.retrofit;

import app.altamira.com.minitwitter.common.Constantes;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MiniTwitterClient {
    private static MiniTwitterClient instance = null;
    private MiniTwitterService miniTwitterService;
    private Retrofit retrofit;

    public MiniTwitterClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_MINITWITTER_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //instanciamos el servicio
        miniTwitterService = retrofit.create(MiniTwitterService.class);
    }

    // Patrón Singleton, devuelve una instancia del minitwitter cliente
    public static MiniTwitterClient getInstance() {
        if(instance == null) {
            instance = new MiniTwitterClient();
        }
        return instance;
    }

    //permite consumir los servicios
    public MiniTwitterService getMiniTwitterService() {
        return miniTwitterService;
    }


}
