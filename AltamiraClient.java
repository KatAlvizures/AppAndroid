package app.altamira.com.minitwitter.retrofit;

import app.altamira.com.minitwitter.common.Constantes;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AltamiraClient {
    private static AltamiraClient instance = null;
    private AltamiraService altamiraService;
    private Retrofit retrofit;

    public AltamiraClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_ALTAMIRA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //se realiza la instancia del servicio
        altamiraService = retrofit.create(AltamiraService.class);
    }

    //implemento patron singleton, devuelve la instancia del cliente de altamira
    public static AltamiraClient getInstance() {
        if(instance == null){
            instance = new AltamiraClient();
        }
        return instance;
    }

    //permite consumir los servicios
    public AltamiraService getAltamiraService(){
        return altamiraService;
    }

}
