package com.onesignal.user.subscriptions;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;

/* JADX INFO: compiled from: IPushSubscription.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000bH&J\b\u0010\u000f\u001a\u00020\u000bH&J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcom/onesignal/user/subscriptions/IPushSubscription;", "Lcom/onesignal/user/subscriptions/ISubscription;", "optedIn", "", "getOptedIn", "()Z", "token", "", "getToken", "()Ljava/lang/String;", "addObserver", "", "observer", "Lcom/onesignal/user/subscriptions/IPushSubscriptionObserver;", "optIn", "optOut", "removeObserver", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IPushSubscription extends ISubscription {
    void addObserver(IPushSubscriptionObserver iPushSubscriptionObserver);

    boolean getOptedIn();

    String getToken();

    void optIn();

    void optOut();

    void removeObserver(IPushSubscriptionObserver iPushSubscriptionObserver);
}
