
package app.altamira.com.minitwitter.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestLogoutApp {

    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestLogoutApp() {
    }

    /**
     * 
     * @param latitud
     * @param longitud
     * @param usuario
     */
    public RequestLogoutApp(String usuario, String latitud, String longitud) {
        super();
        this.usuario = usuario;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

}
