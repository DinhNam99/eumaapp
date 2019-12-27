package com.dell.actapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cuadata implements Serializable {

    @SerializedName("id_cua_data")
    @Expose
    private String idCuaData;
    @SerializedName("tencua_data")
    @Expose
    private String tencuaData;
    @SerializedName("id_henhom")
    @Expose
    private String idHenhom;
    @SerializedName("loaihenhom")
    @Expose
    private String loaihenhom;
    @SerializedName("anhcuadata")
    @Expose
    private String anhcuadata;
    @SerializedName("loaicua")
    @Expose
    private String loaicua;

    public String getIdCuaData() {
        return idCuaData;
    }

    public void setIdCuaData(String idCuaData) {
        this.idCuaData = idCuaData;
    }

    public String getTencuaData() {
        return tencuaData;
    }

    public void setTencuaData(String tencuaData) {
        this.tencuaData = tencuaData;
    }

    public String getIdHenhom() {
        return idHenhom;
    }

    public void setIdHenhom(String idHenhom) {
        this.idHenhom = idHenhom;
    }

    public String getLoaihenhom() {
        return loaihenhom;
    }

    public void setLoaihenhom(String loaihenhom) {
        this.loaihenhom = loaihenhom;
    }

    public String getAnhcuadata() {
        return anhcuadata;
    }

    public void setAnhcuadata(String anhcuadata) {
        this.anhcuadata = anhcuadata;
    }

    public String getLoaicua() {
        return loaicua;
    }

    public void setLoaicua(String loaicua) {
        this.loaicua = loaicua;
    }

}
