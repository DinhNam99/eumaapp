package com.dell.actapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cua implements Serializable {

    @SerializedName("id_cua")
    @Expose
    private String idCua;
    @SerializedName("tencua")
    @Expose
    private String tencua;
    @SerializedName("chieucao")
    @Expose
    private String chieucao;
    @SerializedName("chieurong")
    @Expose
    private String chieurong;
    @SerializedName("anhcua")
    @Expose
    private String anhcua;
    @SerializedName("sobo")
    @Expose
    private String sobo;
    @SerializedName("hochancanh")
    @Expose
    private String hochancanh;
    @SerializedName("id_ct")
    @Expose
    private String idCt;
    @SerializedName("id_henhom")
    @Expose
    private String id_henhom;

    public String getIdCua() {
        return idCua;
    }

    public void setIdCua(String idCua) {
        this.idCua = idCua;
    }

    public String getTencua() {
        return tencua;
    }

    public void setTencua(String tencua) {
        this.tencua = tencua;
    }

    public String getChieucao() {
        return chieucao;
    }

    public void setChieucao(String chieucao) {
        this.chieucao = chieucao;
    }

    public String getChieurong() {
        return chieurong;
    }

    public void setChieurong(String chieurong) {
        this.chieurong = chieurong;
    }

    public String getAnhcua() {
        return anhcua;
    }

    public void setAnhcua(String anhcua) {
        this.anhcua = anhcua;
    }

    public String getIdCt() {
        return idCt;
    }

    public void setIdCt(String idCt) {
        this.idCt = idCt;
    }

    public void setHochancanh(String hochancanh) {
        this.hochancanh = hochancanh;
    }

    public void setSobo(String sobo) {
        this.sobo = sobo;
    }

    public String getHochancanh() {
        return hochancanh;
    }

    public String getSobo() {
        return sobo;
    }

    public String getId_henhom() {
        return id_henhom;
    }
}
