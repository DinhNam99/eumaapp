package com.dell.actapp.ui.henhom;

import com.dell.actapp.model.Henhom;

import java.util.ArrayList;

public interface HenhomView {

    void displayHenhom(ArrayList<Henhom> henhomArrayList);
    void displayFailer(String text);
    void showProgress();
    void hidProgress();
}
