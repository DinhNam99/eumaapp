package com.dell.actapp.model;

import java.io.Serializable;

public class Kinh implements Serializable {

    private int id;
    private float cao;
    private float rong;
    private int soluong;
    private int id_cua;
    private int id_nhom;

    public Kinh(int id, float cao, float rong,int soluong) {
        this.id = id;
        this.cao = cao;
        this.rong = rong;
        this.soluong = soluong;
    }

    public Kinh(int id, float cao, float rong, int soluong, int id_cua,int id_nhom) {
        this.id = id;
        this.cao = cao;
        this.rong = rong;
        this.soluong = soluong;
        this.id_cua = id_cua;
        this.id_nhom = id_nhom;
    }

    public Kinh(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCao() {
        return cao;
    }

    public float getRong() {
        return rong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setCao(float cao) {
        this.cao = cao;
    }

    public int getId_cua() {
        return id_cua;
    }

    public int getId_nhom() {
        return id_nhom;
    }

    public void setId_cua(int id_cua) {
        this.id_cua = id_cua;
    }

    public void setId_nhom(int id_nhom) {
        this.id_nhom = id_nhom;
    }
}

