package com.dell.actapp.ui.sigin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dell.actapp.R;
import com.dell.actapp.ui.login.LoginActivity;
import com.dell.actapp.ui.login.LoginPresenter;
import com.dell.actapp.ui.main.MainActivity;
import com.dell.actapp.utils.LoadingCommon;
import com.dell.actapp.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SigninAcitivity extends AppCompatActivity implements SigninView{

    private LoadingCommon commonUtils;


    @BindView(R.id.btnSignin)
    Button btnSignin;
    @BindView(R.id.edTk)
    EditText edUsername;

    @BindView(R.id.edMK)
    EditText edPassword;

    @BindView(R.id.edMK2)
    EditText edMk2;
    String userName;
    String passWord;

    ProgressDialog progressDialog = null;
    SigninPresenter signinPresenter;
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_acitivity);
        ButterKnife.bind(this);
        init();
    }
    public void init(){
        signinPresenter = new SigninPresenter(this);
        commonUtils = new LoadingCommon(this, progressDialog);
        utils = new Utils(getApplicationContext());

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userName = edUsername.getText().toString().trim();
                passWord = edPassword.getText().toString().trim();
                String pw2 = edMk2.getText().toString().trim();


                if (TextUtils.isEmpty(userName)) {
                    setErrorEditext(edUsername, getString(R.string.noti_insert_username));
                    return;
                }

                if (TextUtils.isEmpty(passWord)) {
                    setErrorEditext(edPassword, getString(R.string.noti_insert_password));
                    return;
                }
                if (TextUtils.isEmpty(pw2)) {
                    setErrorEditext(edMk2, getString(R.string.noti_insert_password2));
                    return;
                }
                if(!pw2.equals(passWord)){
                    setErrorEditext(edMk2, getString(R.string.noti_insert_password3));
                    return;
                }

                setNormalEditext(edUsername, getString(R.string.tk));
                setNormalEditext(edPassword, getString(R.string.mk));
                setNormalEditext(edMk2, getString(R.string.nhaplaimk));
                signinPresenter.signin(utils.getUrl(),userName,passWord,"");
            }
        });
    }

    @Override
    public void showLoadingDialog() {
        commonUtils.showLoadingDialog();
    }

    @Override
    public void hideLoadingDialog() {
        commonUtils.HideLoadingDialog();
    }

    @Override
    public void setErrorEditext(EditText editext, String error) {

        editext.setHint(error);
        editext.setHintTextColor(Color.RED);
        editext.requestFocus();
    }

    @Override
    public void setNormalEditext(EditText editext, String hint) {
        editext.setHintTextColor(getResources().getColor(R.color.colorwhite64));
        editext.setHint(hint);
    }

    @Override
    public void showErrorSignin(String message) {
        utils.showToast(message);
    }

    @Override
    public void showSiginSuccess(String text) {
        utils.showToast(text);
        goToLogin();

    }
    public void goToLogin(){
        Intent intent = new Intent(SigninAcitivity.this, LoginActivity.class);
        intent.putExtra("tk",userName);
        intent.putExtra("mk",passWord);
        startActivity(intent);
        finish();
    }
}
