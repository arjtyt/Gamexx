package com.onesignal.user.internal.subscriptions;

import com.onesignal.common.modeling.Model;
import com.onesignal.common.modeling.ModelChangeTags;
import com.onesignal.core.BuildConfig;
import com.onesignal.inAppMessages.internal.display.impl.WebViewManager;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SubscriptionModel.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR$\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR$\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR$\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u00138F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\tR$\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0003\u001a\u00020\u001c8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010#\u001a\u00020\"2\u0006\u0010\u0003\u001a\u00020\"8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006("}, d2 = {"Lcom/onesignal/user/internal/subscriptions/SubscriptionModel;", "Lcom/onesignal/common/modeling/Model;", "()V", "value", "", "address", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "appVersion", "getAppVersion", "setAppVersion", "carrier", "getCarrier", "setCarrier", "deviceOS", "getDeviceOS", "setDeviceOS", "", "optedIn", "getOptedIn", "()Z", "setOptedIn", "(Z)V", "sdk", "getSdk", "setSdk", "Lcom/onesignal/user/internal/subscriptions/SubscriptionStatus;", "status", "getStatus", "()Lcom/onesignal/user/internal/subscriptions/SubscriptionStatus;", "setStatus", "(Lcom/onesignal/user/internal/subscriptions/SubscriptionStatus;)V", "Lcom/onesignal/user/internal/subscriptions/SubscriptionType;", WebViewManager.EVENT_TYPE_KEY, "getType", "()Lcom/onesignal/user/internal/subscriptions/SubscriptionType;", "setType", "(Lcom/onesignal/user/internal/subscriptions/SubscriptionType;)V", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SubscriptionModel extends Model {
    public SubscriptionModel() {
        super(null, null, 3, null);
    }

    public final boolean getOptedIn() {
        return Model.getBooleanProperty$default(this, "optedIn", null, 2, null);
    }

    public final void setOptedIn(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "optedIn", value, null, false, 12, null);
    }

    public final SubscriptionType getType() {
        SubscriptionModel this_$iv = this;
        Enum enumValueOf = null;
        Object value$iv$iv = Model.getOptAnyProperty$default(this_$iv, WebViewManager.EVENT_TYPE_KEY, null, 2, null);
        if (value$iv$iv != null) {
            if (value$iv$iv instanceof SubscriptionType) {
                enumValueOf = (Enum) value$iv$iv;
            } else if (value$iv$iv instanceof String) {
                enumValueOf = SubscriptionType.valueOf((String) value$iv$iv);
            } else {
                if (value$iv$iv == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.onesignal.user.internal.subscriptions.SubscriptionType");
                }
                enumValueOf = (SubscriptionType) value$iv$iv;
            }
        }
        if (enumValueOf != null) {
            return (SubscriptionType) enumValueOf;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.onesignal.user.internal.subscriptions.SubscriptionType");
    }

    public final void setType(SubscriptionType value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        SubscriptionModel this_$iv$iv = this;
        this_$iv$iv.setOptAnyProperty(WebViewManager.EVENT_TYPE_KEY, value.toString(), ModelChangeTags.NORMAL, false);
    }

    public final String getAddress() {
        return Model.getStringProperty$default(this, "address", null, 2, null);
    }

    public final void setAddress(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setStringProperty$default(this, "address", value, null, false, 12, null);
    }

    public final SubscriptionStatus getStatus() throws Throwable {
        Enum enumValueOf = null;
        if (!hasProperty("status")) {
            Enum value$iv = SubscriptionStatus.SUBSCRIBED;
            SubscriptionModel this_$iv$iv = this;
            this_$iv$iv.setOptAnyProperty("status", value$iv != null ? value$iv.toString() : null, ModelChangeTags.NORMAL, false);
        }
        SubscriptionModel this_$iv = this;
        Object value$iv$iv = Model.getOptAnyProperty$default(this_$iv, "status", null, 2, null);
        if (value$iv$iv != null) {
            if (value$iv$iv instanceof SubscriptionStatus) {
                enumValueOf = (Enum) value$iv$iv;
            } else if (value$iv$iv instanceof String) {
                enumValueOf = SubscriptionStatus.valueOf((String) value$iv$iv);
            } else {
                if (value$iv$iv == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.onesignal.user.internal.subscriptions.SubscriptionStatus");
                }
                enumValueOf = (SubscriptionStatus) value$iv$iv;
            }
        }
        if (enumValueOf != null) {
            return (SubscriptionStatus) enumValueOf;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.onesignal.user.internal.subscriptions.SubscriptionStatus");
    }

    public final void setStatus(SubscriptionStatus value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        SubscriptionModel this_$iv$iv = this;
        this_$iv$iv.setOptAnyProperty("status", value.toString(), ModelChangeTags.NORMAL, false);
    }

    public final String getSdk() {
        return getStringProperty("sdk", new Function0<String>() { // from class: com.onesignal.user.internal.subscriptions.SubscriptionModel$sdk$2
            public final String invoke() {
                return "";
            }
        });
    }

    public final void setSdk(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setStringProperty$default(this, "sdk", value, null, false, 12, null);
    }

    public final String getDeviceOS() {
        return getStringProperty("deviceOS", new Function0<String>() { // from class: com.onesignal.user.internal.subscriptions.SubscriptionModel$deviceOS$2
            public final String invoke() {
                return "";
            }
        });
    }

    public final void setDeviceOS(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setStringProperty$default(this, "deviceOS", value, null, false, 12, null);
    }

    public final String getCarrier() {
        return getStringProperty("carrier", new Function0<String>() { // from class: com.onesignal.user.internal.subscriptions.SubscriptionModel$carrier$2
            public final String invoke() {
                return "";
            }
        });
    }

    public final void setCarrier(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setStringProperty$default(this, "carrier", value, null, false, 12, null);
    }

    public final String getAppVersion() {
        return getStringProperty("appVersion", new Function0<String>() { // from class: com.onesignal.user.internal.subscriptions.SubscriptionModel$appVersion$2
            public final String invoke() {
                return "";
            }
        });
    }

    public final void setAppVersion(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setStringProperty$default(this, "appVersion", value, null, false, 12, null);
    }
}
