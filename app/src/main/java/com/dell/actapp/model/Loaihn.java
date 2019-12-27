package com.dell.actapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Loaihn {
    @SerializedName("id_henhom")
    @Expose
    private String idHenhom;
    @SerializedName("id_loaihn")
    @Expose
    private String id_loaihn;
    @SerializedName("ten_loaihn")
    @Expose
    private String tenLoaihn;

    public String getIdHenhom() {
        return idHenhom;
    }

    public void setIdHenhom(String idHenhom) {
        this.idHenhom = idHenhom;
    }

    public void setId_loaihn(String id_loaihn) {
        this.id_loaihn = id_loaihn;
    }

    public void setTenLoaihn(String tenLoaihn) {
        this.tenLoaihn = tenLoaihn;
    }

    public String getId_loaihn() {
        return id_loaihn;
    }

    public String getTenLoaihn() {
        return tenLoaihn;
    }
}
