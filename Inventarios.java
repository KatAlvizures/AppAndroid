package app.altamira.com.minitwitter.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inventarios {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nombre")
    @Expose
    private String nombre;

    /**
     * No args constructor for use in serialization
     *
     */
    public Inventarios() {
    }

    /**
     *
     * @param id
     * @param nombre
     */
    public Inventarios(Integer id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString(){
        return nombre;
    }
}
