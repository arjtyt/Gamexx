package com.primexop.webview.baseHelpers;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
public class CustomWebViewClient extends WebViewClient {
    private AppCompatActivity activity;

    public CustomWebViewClient(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();
        Log.d("ContentValues", "shouldOverrideUrlLoading:  " + url);
        if (url.startsWith("whatsapp://send?")) {
            try {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                this.activity.startActivity(intent);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return true;
            }
        }
        return super.shouldOverrideUrlLoading(view, request);
    }
}
