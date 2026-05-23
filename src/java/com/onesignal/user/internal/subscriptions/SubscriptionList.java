package com.onesignal.user.internal.subscriptions;

import com.onesignal.core.BuildConfig;
import com.onesignal.inAppMessages.internal.prompt.InAppMessagePromptTypes;
import com.onesignal.user.subscriptions.IEmailSubscription;
import com.onesignal.user.subscriptions.IPushSubscription;
import com.onesignal.user.subscriptions.ISmsSubscription;
import com.onesignal.user.subscriptions.ISubscription;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubscriptionList.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0017\u001a\u00020\u0015R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00038F¢\u0006\u0006\u001a\u0004\b\f\u0010\tR\u0011\u0010\r\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/onesignal/user/internal/subscriptions/SubscriptionList;", "", "collection", "", "Lcom/onesignal/user/subscriptions/ISubscription;", "_fallbackPushSub", "Lcom/onesignal/user/subscriptions/IPushSubscription;", "(Ljava/util/List;Lcom/onesignal/user/subscriptions/IPushSubscription;)V", "getCollection", "()Ljava/util/List;", "emails", "Lcom/onesignal/user/subscriptions/IEmailSubscription;", "getEmails", InAppMessagePromptTypes.PUSH_PROMPT_KEY, "getPush", "()Lcom/onesignal/user/subscriptions/IPushSubscription;", "smss", "Lcom/onesignal/user/subscriptions/ISmsSubscription;", "getSmss", "getByEmail", "email", "", "getBySMS", "sms", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubscriptionList {
    private final IPushSubscription _fallbackPushSub;
    private final List<ISubscription> collection;

    /* JADX WARN: Multi-variable type inference failed */
    public SubscriptionList(List<? extends ISubscription> list, IPushSubscription _fallbackPushSub) {
        Intrinsics.checkNotNullParameter(list, "collection");
        Intrinsics.checkNotNullParameter(_fallbackPushSub, "_fallbackPushSub");
        this.collection = list;
        this._fallbackPushSub = _fallbackPushSub;
    }

    public final List<ISubscription> getCollection() {
        return this.collection;
    }

    public final IPushSubscription getPush() {
        Iterable $this$filterIsInstance$iv = this.collection;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof IPushSubscription) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        IPushSubscription iPushSubscription = (IPushSubscription) CollectionsKt.firstOrNull((List) destination$iv$iv);
        return iPushSubscription == null ? this._fallbackPushSub : iPushSubscription;
    }

    public final List<IEmailSubscription> getEmails() {
        Iterable $this$filterIsInstance$iv = this.collection;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof IEmailSubscription) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final List<ISmsSubscription> getSmss() {
        Iterable $this$filterIsInstance$iv = this.collection;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (element$iv$iv instanceof ISmsSubscription) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        return (List) destination$iv$iv;
    }

    public final IEmailSubscription getByEmail(String email) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(email, "email");
        Iterable $this$firstOrNull$iv = getEmails();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                IEmailSubscription it2 = (IEmailSubscription) element$iv;
                if (Intrinsics.areEqual(it2.getEmail(), email)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        return (IEmailSubscription) element$iv;
    }

    public final ISmsSubscription getBySMS(String sms) {
        Object element$iv;
        Intrinsics.checkNotNullParameter(sms, "sms");
        Iterable $this$firstOrNull$iv = getSmss();
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                ISmsSubscription it2 = (ISmsSubscription) element$iv;
                if (Intrinsics.areEqual(it2.getNumber(), sms)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        return (ISmsSubscription) element$iv;
    }
}
