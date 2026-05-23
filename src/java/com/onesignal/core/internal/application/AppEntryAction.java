package com.onesignal.core.internal.application;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: AppEntryAction.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005R\u0011\u0010\u0007\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/onesignal/core/internal/application/AppEntryAction;", "", "(Ljava/lang/String;I)V", "isAppClose", "", "()Z", "isAppOpen", "isNotificationClick", "NOTIFICATION_CLICK", "APP_OPEN", "APP_CLOSE", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum AppEntryAction {
    NOTIFICATION_CLICK,
    APP_OPEN,
    APP_CLOSE;

    public final boolean isNotificationClick() {
        return this == NOTIFICATION_CLICK;
    }

    public final boolean isAppOpen() {
        return this == APP_OPEN;
    }

    public final boolean isAppClose() {
        return this == APP_CLOSE;
    }
}
