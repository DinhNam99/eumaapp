package com.dell.actapp.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.dell.actapp.R;


public final class LoadingCommon {

    private static final String TAG = "LoadingCommon";

    private Context mContext;

    private ProgressDialog progressDialog;

    public LoadingCommon(Context mContext, ProgressDialog progressDialog) {
        this.mContext = mContext;
        this.progressDialog = progressDialog;
    }

    public ProgressDialog showLoadingDialog() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public void HideLoadingDialog() {
        progressDialog.dismiss();
    }



}
