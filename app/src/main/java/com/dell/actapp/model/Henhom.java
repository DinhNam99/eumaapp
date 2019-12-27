package com.dell.actapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Henhom {

    @SerializedName("id_henhom")
    @Expose
    private String idHenhom;
    @SerializedName("ten_hn")
    @Expose
    private String tenHn;

    public String getIdHenhom() {
        return idHenhom;
    }

    public void setIdHenhom(String idHenhom) {
        this.idHenhom = idHenhom;
    }

    public String getTenHn() {
        return tenHn;
    }

    public void setTenHn(String tenHn) {
        this.tenHn = tenHn;
    }

}
