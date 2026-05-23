package com.onesignal.notifications;

import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: INotificationsManager.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\tH&J\u0010\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\rH&J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0019\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lcom/onesignal/notifications/INotificationsManager;", "", "canRequestPermission", "", "getCanRequestPermission", "()Z", "permission", "getPermission", "addClickListener", "", "listener", "Lcom/onesignal/notifications/INotificationClickListener;", "addForegroundLifecycleListener", "Lcom/onesignal/notifications/INotificationLifecycleListener;", "addPermissionObserver", "observer", "Lcom/onesignal/notifications/IPermissionObserver;", "clearAllNotifications", "removeClickListener", "removeForegroundLifecycleListener", "removeGroupedNotifications", "group", "", "removeNotification", OutcomeConstants.OUTCOME_ID, "", "removePermissionObserver", "requestPermission", "fallbackToSettings", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", com.onesignal.core.BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface INotificationsManager {
    /* JADX INFO: renamed from: addClickListener */
    void mo70addClickListener(INotificationClickListener iNotificationClickListener);

    /* JADX INFO: renamed from: addForegroundLifecycleListener */
    void mo71addForegroundLifecycleListener(INotificationLifecycleListener iNotificationLifecycleListener);

    /* JADX INFO: renamed from: addPermissionObserver */
    void mo72addPermissionObserver(IPermissionObserver iPermissionObserver);

    /* JADX INFO: renamed from: clearAllNotifications */
    void mo73clearAllNotifications();

    /* JADX INFO: renamed from: getCanRequestPermission */
    boolean mo74getCanRequestPermission();

    /* JADX INFO: renamed from: getPermission */
    boolean mo75getPermission();

    /* JADX INFO: renamed from: removeClickListener */
    void mo76removeClickListener(INotificationClickListener iNotificationClickListener);

    /* JADX INFO: renamed from: removeForegroundLifecycleListener */
    void mo77removeForegroundLifecycleListener(INotificationLifecycleListener iNotificationLifecycleListener);

    /* JADX INFO: renamed from: removeGroupedNotifications */
    void mo78removeGroupedNotifications(String str);

    /* JADX INFO: renamed from: removeNotification */
    void mo79removeNotification(int i);

    /* JADX INFO: renamed from: removePermissionObserver */
    void mo80removePermissionObserver(IPermissionObserver iPermissionObserver);

    Object requestPermission(boolean z, Continuation<? super Boolean> continuation);
}
