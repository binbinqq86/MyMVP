package com.binbin.mymvp.activity;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.binbin.mymvp.R;
import com.binbin.mymvp.presenter.TestPresenterImpl;
import com.binbin.mymvp.view.IBaseView;
import com.binbin.mymvp.view.TestView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements TestView, View.OnClickListener {

    private Dialog loadingDialog;
    private TestPresenterImpl testPresenter;
    private TextView tv;
    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        wb= (WebView) findViewById(R.id.wb);
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener(this);
        testPresenter = new TestPresenterImpl(this);
        loadingDialog = new ProgressDialog(this);
        loadingDialog.setTitle("loading...");

        wb.getSettings().setDefaultTextEncodingName("UTF-8") ;
        wb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//缓存
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setDatabaseEnabled(true);
        wb.getSettings().setAppCacheEnabled(true);
        wb.getSettings().setAllowFileAccess(true);
        wb.getSettings().setSavePassword(true);
        wb.getSettings().setSupportZoom(true);
        wb.getSettings().setBuiltInZoomControls(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoadingDialog() {
        loadingDialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        loadingDialog.hide();
    }

    @Override
    public void showError(Object error) {
        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSuccess() {
        Toast.makeText(getApplicationContext(),"success...",Toast.LENGTH_LONG).show();
    }

    @Override
    public void bindData(Object data) {
        tv.setText(data.toString());
        wb.loadData(data.toString(),"text/html","UTF-8");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv:
                testPresenter.loadData();
                break;
        }
    }

}
