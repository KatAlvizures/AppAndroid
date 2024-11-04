
package app.altamira.com.minitwitter;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import app.altamira.com.minitwitter.retrofit.response.Reg;

public class ResponseUsuarioInventario {

    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("ambiente")
    @Expose
    private String ambiente;
    @SerializedName("operacion")
    @Expose
    private String operacion;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("total_regs")
    @Expose
    private String totalRegs;
    @SerializedName("regs")
    @Expose
    private List<Reg> regs = null;
    @SerializedName("transactionid")
    @Expose
    private String transactionid;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseUsuarioInventario() {
    }

    /**
     *
     * @param totalRegs
     * @param ambiente
     * @param regs
     * @param operacion
     * @param error
     * @param version
     * @param transactionid
     */
    public ResponseUsuarioInventario(String version, String ambiente, String operacion, String error, String totalRegs, List<Reg> regs, String transactionid) {
        super();
        this.version = version;
        this.ambiente = ambiente;
        this.operacion = operacion;
        this.error = error;
        this.totalRegs = totalRegs;
        this.regs = regs;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTotalRegs() {
        return totalRegs;
    }

    public void setTotalRegs(String totalRegs) {
        this.totalRegs = totalRegs;
    }

    public List<Reg> getRegs() {
        return regs;
    }

    public void setRegs(List<Reg> regs) {
        this.regs = regs;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

}
