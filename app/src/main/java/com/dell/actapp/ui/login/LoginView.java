package com.dell.actapp.ui.login;

import android.widget.EditText;

public interface LoginView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void setErrorEditext(EditText editext, String error);

    void setNormalEditext(EditText editext, String hint);


    void showErrorLogin(String message);

    void showLoginSuccess(String text);

}
