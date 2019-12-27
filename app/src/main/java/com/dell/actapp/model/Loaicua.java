package com.dell.actapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loaicua {
    @SerializedName("loaicua")
    @Expose
    private String loaicua;

    @SerializedName("id_henhom")
    @Expose
    private String id_henhom;
    @SerializedName("loaihenhom")
    @Expose
    private String loaihenhom;

    public String getLoaicua() {
        return loaicua;
    }

    public void setLoaicua(String loaicua) {
        this.loaicua = loaicua;
    }

    public String getId_henhom() {
        return id_henhom;
    }

    public String getLoaihenhom() {
        return loaihenhom;
    }

    public void setId_henhom(String id_henhom) {
        this.id_henhom = id_henhom;
    }

    public void setLoaihenhom(String loaihenhom) {
        this.loaihenhom = loaihenhom;
    }
}
