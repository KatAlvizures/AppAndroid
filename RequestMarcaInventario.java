
package app.altamira.com.minitwitter.retrofit.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestMarcaInventario {

    @SerializedName("codigo")
    @Expose
    private String codigo;
    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("inventario")
    @Expose
    private String inventario;
    @SerializedName("latitud")
    @Expose
    private String latitud;
    @SerializedName("longitud")
    @Expose
    private String longitud;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;


    /**
     * No args constructor for use in serialization
     */
    public RequestMarcaInventario() {
    }

    /**
     * @param codigo
     * @param usuario
     * @param inventario
     * @param latitud
     * @param longitud
     * @param descripcion
     */
    public RequestMarcaInventario(String codigo, String usuario, String inventario, String latitud, String longitud, String descripcion) {
        super();
        this.codigo = codigo;
        this.usuario = usuario;
        this.inventario = inventario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getInventario() {
        return inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
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

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

}
