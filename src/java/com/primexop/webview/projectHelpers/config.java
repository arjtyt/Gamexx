package com.primexop.webview.projectHelpers;

import com.primexop.webview.BuildConfig;

/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
public class config {
    static final int app_v_code = 16;
    public static final Boolean isLocalEnvironment = false;
    public static final String[] urlContainsForDirectExit = {"/play", "/home", "/earn", "/login"};
    static final String appUsername = BuildConfig.APPLICATION_ID.replaceFirst("^com.", "").replaceFirst("\\.web_apk", "");
}
