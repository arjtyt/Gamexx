package com.primexop.webview.baseHelpers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
public class AdvancedWebChromeClient extends WebChromeClient {
    private static final int FILE_CHOOSER_RESULT_CODE = 101;
    private static final int REQUEST_CODE = 1234;
    public Activity mActivity;
    private ValueCallback<Uri[]> mFilePathCallback;
    private final ProgressBar mHorizontalProgressBar;
    private final PermissionHandler mPermissionHandler;

    public AdvancedWebChromeClient(Activity activity, PermissionHandler permissionHandler, ProgressBar horizontalProgressBar) {
        this.mActivity = activity;
        this.mHorizontalProgressBar = horizontalProgressBar;
        this.mPermissionHandler = permissionHandler;
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        this.mFilePathCallback = filePathCallback;
        showFileChooser();
        return true;
    }

    public void onRequestPermissionsResultForFile(int requestCode, String[] permissions, int[] grantResults) {
        if (this.mPermissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults, REQUEST_CODE)) {
            showFileChooser();
        }
    }

    private void showFileChooser() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        this.mActivity.startActivityForResult(Intent.createChooser(intent, "Choose File"), 101);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != 101 || this.mFilePathCallback == null) {
            return;
        }
        Uri[] result = null;
        if (resultCode == -1) {
            result = new Uri[]{data.getData()};
            Log.d("LOG", "file choose success");
        }
        this.mFilePathCallback.onReceiveValue(result);
        this.mFilePathCallback = null;
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        this.mHorizontalProgressBar.setProgress(newProgress);
        if (newProgress == 100) {
            this.mHorizontalProgressBar.setVisibility(8);
        } else {
            this.mHorizontalProgressBar.setVisibility(0);
        }
    }
}
