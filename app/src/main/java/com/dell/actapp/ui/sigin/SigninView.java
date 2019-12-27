package com.dell.actapp.ui.sigin;

import android.widget.EditText;

public interface SigninView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void setErrorEditext(EditText editext, String error);

    void setNormalEditext(EditText editext, String hint);


    void showErrorSignin(String message);

    void showSiginSuccess(String text);

}
