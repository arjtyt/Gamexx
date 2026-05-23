package com.onesignal.inAppMessages;

import kotlin.Metadata;

/* JADX INFO: compiled from: IInAppMessageLifecycleListener.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/onesignal/inAppMessages/IInAppMessageLifecycleListener;", "", "onDidDismiss", "", "event", "Lcom/onesignal/inAppMessages/IInAppMessageDidDismissEvent;", "onDidDisplay", "Lcom/onesignal/inAppMessages/IInAppMessageDidDisplayEvent;", "onWillDismiss", "Lcom/onesignal/inAppMessages/IInAppMessageWillDismissEvent;", "onWillDisplay", "Lcom/onesignal/inAppMessages/IInAppMessageWillDisplayEvent;", com.onesignal.core.BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IInAppMessageLifecycleListener {
    void onDidDismiss(IInAppMessageDidDismissEvent iInAppMessageDidDismissEvent);

    void onDidDisplay(IInAppMessageDidDisplayEvent iInAppMessageDidDisplayEvent);

    void onWillDismiss(IInAppMessageWillDismissEvent iInAppMessageWillDismissEvent);

    void onWillDisplay(IInAppMessageWillDisplayEvent iInAppMessageWillDisplayEvent);
}
