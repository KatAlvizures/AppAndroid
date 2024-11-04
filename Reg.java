
package app.altamira.com.minitwitter.retrofit.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Reg {

    @SerializedName("idinventario")
    @Expose
    private String idinventario;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("r")
    @Expose
    private String r;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Reg() {
    }

    /**
     * 
     * @param r
     * @param idinventario
     * @param titulo
     */
    public Reg(String idinventario, String titulo, String r) {
        super();
        this.idinventario = idinventario;
        this.titulo = titulo;
        this.r = r;
    }

    public String getIdinventario() {
        return idinventario;
    }

    public void setIdinventario(String idinventario) {
        this.idinventario = idinventario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

}
