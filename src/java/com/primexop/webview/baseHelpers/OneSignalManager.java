package com.primexop.webview.baseHelpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.onesignal.Continue;
import com.onesignal.ContinueResult;
import com.onesignal.OneSignal;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
public class OneSignalManager {
    private static final String KEY_HAS_SHOWN_PROMPT = "has_shown_notification_prompt";
    private static final String PREFS_NAME = "onesignal_prefs";
    private static final String TAG = "OneSignalManager";
    private final Context context;
    private final SharedPreferences prefs;

    public OneSignalManager(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences(PREFS_NAME, 0);
    }

    public void checkAndRequestPermission() {
        this.prefs.getBoolean(KEY_HAS_SHOWN_PROMPT, false);
        if (!OneSignal.getNotifications().mo75getPermission()) {
            requestNotificationPermission();
            this.prefs.edit().putBoolean(KEY_HAS_SHOWN_PROMPT, true).apply();
        }
    }

    private void requestNotificationPermission() {
        try {
            OneSignal.getNotifications().requestPermission(true, Continue.with(new Consumer() { // from class: com.primexop.webview.baseHelpers.OneSignalManager$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.m100lambda$requestNotificationPermission$0$comprimexopwebviewbaseHelpersOneSignalManager((ContinueResult) obj);
                }
            }));
        } catch (Exception e) {
            Log.e(TAG, "Error requesting notification permission", e);
        }
    }

    /* JADX INFO: renamed from: lambda$requestNotificationPermission$0$com-primexop-webview-baseHelpers-OneSignalManager, reason: not valid java name */
    /* synthetic */ void m100lambda$requestNotificationPermission$0$comprimexopwebviewbaseHelpersOneSignalManager(ContinueResult r) {
        if (r.isSuccess()) {
            handlePermissionResult(((Boolean) r.getData()).booleanValue());
        } else {
            handlePermissionError(r.getThrowable());
        }
    }

    private void handlePermissionResult(boolean granted) {
        if (granted) {
            Log.d(TAG, "Notification permission granted");
        } else {
            Log.d(TAG, "Notification permission denied");
        }
    }

    private void handlePermissionError(Throwable throwable) {
        Log.e(TAG, "Error requesting notification permission", throwable);
    }

    public void setExternalUserId(String userId) {
        if (userId != null && !userId.isEmpty()) {
            OneSignal.login(userId);
        }
    }

    public void removeExternalUserId() {
        OneSignal.logout();
    }

    public boolean isPermissionGranted() {
        return OneSignal.getNotifications().mo75getPermission();
    }
}
