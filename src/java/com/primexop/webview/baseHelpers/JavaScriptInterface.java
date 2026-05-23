package com.primexop.webview.baseHelpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import org.json.JSONObject;

/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
public class JavaScriptInterface {
    public String externalIdString = "null";
    private final Context mContext;
    public FrameLayout mHorizontalProgressFrameLayout;
    public WebView mWebView;
    public Window mWindow;

    public JavaScriptInterface(Context context, WebView webView, Window window, FrameLayout horizontalProgressFrameLayout) {
        this.mContext = context;
        this.mWebView = webView;
        this.mWindow = window;
        this.mHorizontalProgressFrameLayout = horizontalProgressFrameLayout;
    }

    @JavascriptInterface
    public void passObj(final String data, final String jsCallback) {
        try {
            new Handler().postDelayed(new Runnable() { // from class: com.primexop.webview.baseHelpers.JavaScriptInterface.1
                @Override // java.lang.Runnable
                public void run() {
                    StringBuffer buf = new StringBuffer(data);
                    final String s = buf.reverse().toString();
                    ((Activity) JavaScriptInterface.this.mContext).runOnUiThread(new Runnable() { // from class: com.primexop.webview.baseHelpers.JavaScriptInterface.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            JavaScriptInterface.this.mWebView.loadUrl("javascript:" + jsCallback + "(true,'" + s + "');void(0);");
                        }
                    });
                }
            }, 5000L);
        } catch (Exception e) {
            this.mWebView.loadUrl("javascript:" + jsCallback + "(false,error);void(0);");
        }
    }

    @JavascriptInterface
    public void showToast(String msg) {
        Log.d("ContentValues", " primeTest ToastMsg:" + msg);
        Toast.makeText(this.mContext, msg, 1).show();
    }

    @JavascriptInterface
    public void setStatusBarColor(String hexString) {
        this.mWindow.setStatusBarColor(Color.parseColor(hexString));
        this.mHorizontalProgressFrameLayout.setBackgroundColor(Color.parseColor(hexString));
    }

    @JavascriptInterface
    public void openInBrowser(String url) {
        Log.d("ContentValues", " primeTest openInBrowser:" + url);
        Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        this.mContext.startActivity(browserIntent);
    }

    @JavascriptInterface
    public void passData(String jsonData) {
        Log.d("ContentValues", " primeTest jsonData:" + jsonData);
        try {
            JSONObject data = new JSONObject(jsonData);
            Log.d("ContentValues", " primeTest data:" + data);
            String name = data.getString("name");
            Number number = Integer.valueOf(data.getInt("number"));
            Log.d("ContentValues", " primeTest name  number:" + name + number);
        } catch (Exception e) {
            Log.d("ContentValues", "Pxop Error" + e);
        }
    }

    @JavascriptInterface
    public void shareMessage(String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            Log.d("ContentValues", " primeTest data:" + data);
            String shareMessage = data.getString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE);
            Intent shareIntent = new Intent("android.intent.action.SEND");
            shareIntent.setType("text/plain");
            shareIntent.putExtra("android.intent.extra.SUBJECT", "appName");
            shareIntent.putExtra("android.intent.extra.TEXT", shareMessage);
            this.mContext.startActivity(shareIntent);
        } catch (Exception e) {
            Log.d("ContentValues", "Pxop Error" + e);
        }
    }

    @JavascriptInterface
    public void initOnesignal___(String jsonData) {
    }

    @JavascriptInterface
    public void getOnesignalPlayerId() {
    }

    @JavascriptInterface
    public void setOnesignalExternalId(String jsonData) {
        try {
            JSONObject data = new JSONObject(jsonData);
            String externalId = data.getString("externalId");
            Log.d("ContentValues", "onesignal setOnesignalExternalId" + externalId + "-" + this.externalIdString);
            if (!externalId.equals(this.externalIdString)) {
                ((ApplicationClass) this.mContext.getApplicationContext()).getOneSignalManager().setExternalUserId(externalId);
                this.externalIdString = externalId;
            }
        } catch (Exception e) {
            Log.d("ContentValues", "Pxop Error" + e);
        }
    }

    @JavascriptInterface
    public void removeOnesignalExternalId() {
        try {
            ((ApplicationClass) this.mContext.getApplicationContext()).getOneSignalManager().removeExternalUserId();
            this.externalIdString = "null";
            Log.d("ContentValues", "onesignal removeExternalUserId");
        } catch (Exception e) {
            Log.d("ContentValues", "Pxop Error" + e);
        }
    }

    @JavascriptInterface
    public void shareImage(String imageUrl) {
        new DownloadAndShareTask(this.mContext).execute(imageUrl);
    }
}
