package com.dell.actapp.fragment.congtrinh;

import com.dell.actapp.model.CongTrinh;

import java.util.ArrayList;

public interface CongtrinhView {

    void displayCongTrinh(ArrayList<CongTrinh>congTrinhList);
    void displayFailer(String text);
    void themmoiThanhcong(String text,String tenct,String id_ct);
    void themmoiThatbai(String text);
    void showProgress();
    void hidProgress();
}
