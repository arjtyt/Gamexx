package com.onesignal.notifications.internal;

import android.content.Context;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.notifications.BuildConfig;
import com.onesignal.notifications.INotificationReceivedEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationReceivedEvent.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/onesignal/notifications/internal/NotificationReceivedEvent;", "Lcom/onesignal/notifications/INotificationReceivedEvent;", "context", "Landroid/content/Context;", OneSignalDbContract.NotificationTable.TABLE_NAME, "Lcom/onesignal/notifications/internal/Notification;", "(Landroid/content/Context;Lcom/onesignal/notifications/internal/Notification;)V", "getContext", "()Landroid/content/Context;", "discard", "", "getDiscard", "()Z", "setDiscard", "(Z)V", "isPreventDefault", "setPreventDefault", "getNotification", "()Lcom/onesignal/notifications/internal/Notification;", "preventDefault", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class NotificationReceivedEvent implements INotificationReceivedEvent {
    private final Context context;
    private boolean discard;
    private boolean isPreventDefault;
    private final Notification notification;

    public NotificationReceivedEvent(Context context, Notification notification) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notification, OneSignalDbContract.NotificationTable.TABLE_NAME);
        this.context = context;
        this.notification = notification;
    }

    @Override // com.onesignal.notifications.INotificationReceivedEvent
    public Context getContext() {
        return this.context;
    }

    @Override // com.onesignal.notifications.INotificationReceivedEvent
    public Notification getNotification() {
        return this.notification;
    }

    public final boolean isPreventDefault() {
        return this.isPreventDefault;
    }

    public final void setPreventDefault(boolean z) {
        this.isPreventDefault = z;
    }

    public final boolean getDiscard() {
        return this.discard;
    }

    public final void setDiscard(boolean z) {
        this.discard = z;
    }

    @Override // com.onesignal.notifications.INotificationReceivedEvent
    public void preventDefault() throws Exception {
        preventDefault(false);
    }

    @Override // com.onesignal.notifications.INotificationReceivedEvent
    public void preventDefault(boolean discard) throws Exception {
        Logging.debug$default("NotificationReceivedEvent.preventDefault(" + discard + ')', null, 2, null);
        if (this.isPreventDefault && discard) {
            getNotification().getDisplayWaiter().wake(false);
        }
        this.isPreventDefault = true;
        this.discard = discard;
    }
}
