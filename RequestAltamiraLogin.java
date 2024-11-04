
package app.altamira.com.minitwitter.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestAltamiraLogin {

    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;

    /**
     * No args constructor for use in serialization
     */
    public RequestAltamiraLogin() {
    }

    /**
     * @param password
     * @param usuario
     * @param latitud
     * @param longitud
     */
    public RequestAltamiraLogin(String usuario, String password, String latitud, String longitud) {
        super();
        this.usuario = usuario;
        this.password = password;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
