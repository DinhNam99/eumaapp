package com.dell.actapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;
import java.util.List;

public class CongTrinh implements Serializable {

    @SerializedName("id_ct")
    @Expose
    private String idCt;
    @SerializedName("tenct")
    @Expose
    private String tenct;
    @SerializedName("id_user")
    @Expose
    private String idUser;

    public CongTrinh(String idCt, String tenct, String idUser) {
        this.idCt = idCt;
        this.tenct = tenct;
        this.idUser = idUser;
    }

    public String getIdCt() {
        return idCt;
    }

    public void setIdCt(String idCt) {
        this.idCt = idCt;
    }

    public String getTenct() {
        return tenct;
    }

    public void setTenct(String tenct) {
        this.tenct = tenct;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

}
