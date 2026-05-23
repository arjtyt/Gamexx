package com.primexop.webview.baseHelpers;

import android.app.Application;
import android.util.Log;
import com.onesignal.OneSignal;
import com.onesignal.notifications.IPermissionObserver;
import com.primexop.webview.R;

/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
public class ApplicationClass extends Application {
    private OneSignalManager oneSignalManager;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        String ONESIGNAL_APP_ID = getString(R.string.onesignal_app_id);
        Log.d("ContentValues", "AC onesignal  " + ONESIGNAL_APP_ID);
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);
        this.oneSignalManager = new OneSignalManager(this);
        this.oneSignalManager.checkAndRequestPermission();
        OneSignal.getNotifications().mo72addPermissionObserver(new IPermissionObserver() { // from class: com.primexop.webview.baseHelpers.ApplicationClass$$ExternalSyntheticLambda0
            @Override // com.onesignal.notifications.IPermissionObserver
            public final void onNotificationPermissionChange(boolean z) {
                Log.d("ContentValues", "Notification permission changed: " + z);
            }
        });
    }

    public OneSignalManager getOneSignalManager() {
        return this.oneSignalManager;
    }
}
