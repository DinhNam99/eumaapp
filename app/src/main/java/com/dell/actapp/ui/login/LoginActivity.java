package com.dell.actapp.ui.login;

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
import com.dell.actapp.model.User;
import com.dell.actapp.network.source.AuthData;
import com.dell.actapp.ui.main.MainActivity;
import com.dell.actapp.ui.sigin.SigninAcitivity;
import com.dell.actapp.utils.LoadingCommon;
import com.dell.actapp.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoadingCommon commonUtils;


    @BindView(R.id.btn_Login)
    Button btnLogin;
    @BindView(R.id.ed_username)
    EditText edUsername;

    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.tvDangki)
    TextView tvDangki;

    ProgressDialog progressDialog = null;
    LoginPresenter loginPresenter;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        User user = AuthData.getInstance().getUser();
        if(user != null){
            goToMainAcitivity();
            return;
        }
        innit();
    }


    public void innit(){
        loginPresenter = new LoginPresenter(this);
        commonUtils = new LoadingCommon(this, progressDialog);
        utils = new Utils(getApplicationContext());
        Intent intent = getIntent();
        String tk = intent.getStringExtra("tk");
        String mk = intent.getStringExtra("mk");
        if(tk == null && mk == null){
            edPassword.setText("");
            edUsername.setText("");
        }else{
            edUsername.setText(tk);
            edPassword.setText(mk);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edUsername.getText().toString().trim();
                String passWord = edPassword.getText().toString().trim();


                if (TextUtils.isEmpty(userName)) {
                    setErrorEditext(edUsername, getString(R.string.noti_insert_username));
                    return;
                }

                if (TextUtils.isEmpty(passWord)) {
                    setErrorEditext(edPassword, getString(R.string.noti_insert_password));
                    return;
                }

                setNormalEditext(edUsername, getString(R.string.tk));
                setNormalEditext(edPassword, getString(R.string.mk));
                loginPresenter.login(utils.getUrl(),userName,passWord);
            }
        });

        tvDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SigninAcitivity.class);
                startActivity(intent);
                finish();
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
    public void showErrorLogin(String message) {
        utils.showToast(message);
    }


    @Override
    public void showLoginSuccess(String text) {
        utils.showToast(text);
        goToMainAcitivity();

    }
    public void goToMainAcitivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
