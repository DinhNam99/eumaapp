package com.dell.actapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Nhom implements Serializable {

    @SerializedName("id_nhom")
    @Expose
    private String idNhom;
    @SerializedName("kbCao")
    @Expose
    private String kbCao;
    @SerializedName("kbRong")
    @Expose
    private String kbRong;
    @SerializedName("soluongKbCao")
    @Expose
    private String soluongKbCao;
    @SerializedName("soluongKbRong")
    @Expose
    private String soluongKbRong;
    @SerializedName("canhcuaquayCao")
    @Expose
    private String canhcuaquayCao;
    @SerializedName("canhcuaquayRong")
    @Expose
    private String canhcuaquayRong;
    @SerializedName("slcanhcuaquayCao")
    @Expose
    private String slcanhcuaquayCao;
    @SerializedName("slcanhcuaquayRong")
    @Expose
    private String slcanhcuaquayRong;
    @SerializedName("nepkinhCao")
    @Expose
    private String nepkinhCao;
    @SerializedName("nepkinhRong")
    @Expose
    private String nepkinhRong;
    @SerializedName("slnepCao")
    @Expose
    private String slnepCao;
    @SerializedName("slnepRong")
    @Expose
    private String slnepRong;
    @SerializedName("id_cua")
    @Expose
    private String idCua;

    public String getIdNhom() {
        return idNhom;
    }

    public void setIdNhom(String idNhom) {
        this.idNhom = idNhom;
    }

    public String getKbCao() {
        return kbCao;
    }

    public void setKbCao(String kbCao) {
        this.kbCao = kbCao;
    }

    public String getKbRong() {
        return kbRong;
    }

    public void setKbRong(String kbRong) {
        this.kbRong = kbRong;
    }

    public String getSoluongKbCao() {
        return soluongKbCao;
    }

    public void setSoluongKbCao(String soluongKbCao) {
        this.soluongKbCao = soluongKbCao;
    }

    public String getSoluongKbRong() {
        return soluongKbRong;
    }

    public void setSoluongKbRong(String soluongKbRong) {
        this.soluongKbRong = soluongKbRong;
    }

    public String getCanhcuaquayCao() {
        return canhcuaquayCao;
    }

    public void setCanhcuaquayCao(String canhcuaquayCao) {
        this.canhcuaquayCao = canhcuaquayCao;
    }

    public String getCanhcuaquayRong() {
        return canhcuaquayRong;
    }

    public void setCanhcuaquayRong(String canhcuaquayRong) {
        this.canhcuaquayRong = canhcuaquayRong;
    }

    public String getSlcanhcuaquayCao() {
        return slcanhcuaquayCao;
    }

    public void setSlcanhcuaquayCao(String slcanhcuaquayCao) {
        this.slcanhcuaquayCao = slcanhcuaquayCao;
    }

    public String getSlcanhcuaquayRong() {
        return slcanhcuaquayRong;
    }

    public void setSlcanhcuaquayRong(String slcanhcuaquayRong) {
        this.slcanhcuaquayRong = slcanhcuaquayRong;
    }

    public String getNepkinhCao() {
        return nepkinhCao;
    }

    public void setNepkinhCao(String nepkinhCao) {
        this.nepkinhCao = nepkinhCao;
    }

    public String getNepkinhRong() {
        return nepkinhRong;
    }

    public void setNepkinhRong(String nepkinhRong) {
        this.nepkinhRong = nepkinhRong;
    }

    public String getSlnepCao() {
        return slnepCao;
    }

    public void setSlnepCao(String slnepCao) {
        this.slnepCao = slnepCao;
    }

    public String getSlnepRong() {
        return slnepRong;
    }

    public void setSlnepRong(String slnepRong) {
        this.slnepRong = slnepRong;
    }

    public String getIdCua() {
        return idCua;
    }

    public void setIdCua(String idCua) {
        this.idCua = idCua;
    }

}