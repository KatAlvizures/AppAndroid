
package app.altamira.com.minitwitter.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseUsuarioApp {

    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("ambiente")
    @Expose
    private String ambiente;
    @SerializedName("operacion")
    @Expose
    private String operacion;
    @SerializedName("resultado")
    @Expose
    private String resultado;
    @SerializedName("transactionid")
    @Expose
    private String transactionid;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseUsuarioApp() {
    }

    /**
     * 
     * @param resultado
     * @param ambiente
     * @param operacion
     * @param version
     * @param transactionid
     */
    public ResponseUsuarioApp(String version, String ambiente, String operacion, String resultado, String transactionid) {
        super();
        this.version = version;
        this.ambiente = ambiente;
        this.operacion = operacion;
        this.resultado = resultado;
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

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

}
