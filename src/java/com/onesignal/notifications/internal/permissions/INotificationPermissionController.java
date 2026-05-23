package com.onesignal.notifications.internal.permissions;

import com.onesignal.common.events.IEventNotifier;
import com.onesignal.notifications.BuildConfig;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: INotificationPermissionController.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b`\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0019\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H¦@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lcom/onesignal/notifications/internal/permissions/INotificationPermissionController;", "Lcom/onesignal/common/events/IEventNotifier;", "Lcom/onesignal/notifications/internal/permissions/INotificationPermissionChangedHandler;", "canRequestPermission", "", "getCanRequestPermission", "()Z", "prompt", "fallbackToSettings", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface INotificationPermissionController extends IEventNotifier<INotificationPermissionChangedHandler> {
    boolean getCanRequestPermission();

    Object prompt(boolean z, Continuation<? super Boolean> continuation);
}
