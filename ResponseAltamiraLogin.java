
package app.altamira.com.minitwitter.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAltamiraLogin {

    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("ambiente")
    @Expose
    private String ambiente;
    @SerializedName("operacion")
    @Expose
    private String operacion;
    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("transactionid")
    @Expose
    private String transactionid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseAltamiraLogin() {
    }

    /**
     * 
     * @param ambiente
     * @param usuario
     * @param operacion
     * @param version
     * @param nombre
     * @param transactionid
     */
    public ResponseAltamiraLogin(String version, String ambiente, String operacion, String usuario, String nombre, String transactionid) {
        super();
        this.version = version;
        this.ambiente = ambiente;
        this.operacion = operacion;
        this.usuario = usuario;
        this.nombre = nombre;
        this.transactionid = transactionid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

}
