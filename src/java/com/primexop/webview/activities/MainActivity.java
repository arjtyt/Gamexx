package com.primexop.webview.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.primexop.webview.R;
import com.primexop.webview.baseHelpers.AdvancedWebChromeClient;
import com.primexop.webview.baseHelpers.CustomWebViewClient;
import com.primexop.webview.baseHelpers.DetectConnection;
import com.primexop.webview.baseHelpers.JavaScriptInterface;
import com.primexop.webview.baseHelpers.PermissionHandler;
import com.primexop.webview.projectHelpers.config;

/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
public class MainActivity extends AppCompatActivity {
    ProgressBar HorizontalProgressBar;
    public AdvancedWebChromeClient advancedWebChromeClient;
    public CustomWebViewClient customWebViewClient;
    final Handler handler = new Handler();
    FrameLayout horizontalProgressFrameLayout;
    RelativeLayout splashScreenRelativeLayout;
    public WebView webView;
    RelativeLayout webViewRelativeLayout;

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.splashScreenRelativeLayout = (RelativeLayout) findViewById(R.id.splash_screen_relative_layout);
        this.webViewRelativeLayout = (RelativeLayout) findViewById(R.id.web_view_relative_layout);
        this.horizontalProgressFrameLayout = (FrameLayout) findViewById(R.id.frameLayoutHorizontalProgress);
        this.HorizontalProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        this.webView = (WebView) findViewById(R.id.web_view);
        this.webView.setBackgroundColor(0);
        WebSettings webSettings = this.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDatabaseEnabled(true);
        Window window = getWindow();
        this.webView.addJavascriptInterface(new JavaScriptInterface(this, this.webView, window, this.horizontalProgressFrameLayout), "Android");
        PermissionHandler permissionHandler = new PermissionHandler(this);
        this.advancedWebChromeClient = new AdvancedWebChromeClient(this, permissionHandler, this.HorizontalProgressBar);
        this.webView.setWebChromeClient(this.advancedWebChromeClient);
        this.customWebViewClient = new CustomWebViewClient(this);
        this.webView.setWebViewClient(this.customWebViewClient);
        this.webView.loadUrl(getString(R.string.home_page_link));
        this.webView.setLongClickable(false);
        this.webView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.primexop.webview.activities.MainActivity.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View v) {
                return true;
            }
        });
        if (!DetectConnection.checkInternetConnection(this)) {
            Toast.makeText(getApplicationContext(), "No Internet!", 0).show();
            this.webView.evaluateJavascript("alert('Internet Connection Not Available')", null);
        } else {
            this.webView.setWebViewClient(new WebViewClient() { // from class: com.primexop.webview.activities.MainActivity.2
                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView view, String url) {
                    MainActivity.this.splashScreenRelativeLayout.setVisibility(8);
                    MainActivity.this.webViewRelativeLayout.setVisibility(0);
                }
            });
        }
    }

    public void onBackPressed() {
        boolean isMatchedToUrlContainsArray = false;
        String[] strArr = config.urlContainsForDirectExit;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String s = strArr[i];
            if (this.webView.getUrl().contains(s)) {
                isMatchedToUrlContainsArray = true;
                if (config.isLocalEnvironment.booleanValue()) {
                    Log.d("ContentValues", "adarsh  urlContainsForDirectExit Matched " + s);
                }
            } else {
                if (config.isLocalEnvironment.booleanValue()) {
                    Log.d("ContentValues", "adarsh  urlContainsForDirectExit NotMatched " + s);
                }
                i++;
            }
        }
        if (isMatchedToUrlContainsArray) {
            Intent homeIntent = new Intent("android.intent.action.MAIN");
            homeIntent.addCategory("android.intent.category.HOME");
            homeIntent.setFlags(67108864);
            startActivity(homeIntent);
            return;
        }
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.advancedWebChromeClient.onRequestPermissionsResultForFile(requestCode, permissions, grantResults);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.advancedWebChromeClient.onActivityResult(requestCode, resultCode, data);
    }
}
