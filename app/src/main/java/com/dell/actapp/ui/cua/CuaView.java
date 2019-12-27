package com.dell.actapp.ui.cua;

import com.dell.actapp.model.Cua;
import com.dell.actapp.model.Cuadata;

import java.util.ArrayList;

public interface CuaView {
    void displayCuabyCt(ArrayList<Cua> cuaArrayList);
    void displayFailer(String text);
    void showProgress();
    void hidProgress();
    void displayCuadata(ArrayList<Cuadata> cuadataArrayList);
}
