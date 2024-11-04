
package app.altamira.com.minitwitter.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RequestUsuarioInventario {

    @SerializedName("usuario")
    @Expose
    private String usuario;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RequestUsuarioInventario() {
    }

    /**
     * 
     * @param usuario
     */
    public RequestUsuarioInventario(String usuario) {
        super();
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

}
