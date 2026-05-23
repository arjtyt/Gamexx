package com.onesignal.inAppMessages.internal.triggers;

import com.onesignal.common.modeling.Model;
import com.onesignal.inAppMessages.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TriggerModel.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\u0003\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/onesignal/inAppMessages/internal/triggers/TriggerModel;", "Lcom/onesignal/common/modeling/Model;", "()V", "value", "", "key", "getKey", "()Ljava/lang/String;", "setKey", "(Ljava/lang/String;)V", "", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class TriggerModel extends Model {
    public TriggerModel() {
        super(null, null, 3, null);
    }

    public final String getKey() {
        return getStringProperty("key", new Function0<String>() { // from class: com.onesignal.inAppMessages.internal.triggers.TriggerModel$key$2
            public final String invoke() {
                return "";
            }
        });
    }

    public final void setKey(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setStringProperty$default(this, "key", value, null, false, 12, null);
    }

    public final Object getValue() {
        return getAnyProperty("value", new Function0<Object>() { // from class: com.onesignal.inAppMessages.internal.triggers.TriggerModel$value$2
            public final Object invoke() {
                return "";
            }
        });
    }

    public final void setValue(Object value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setAnyProperty$default(this, "value", value, null, true, 4, null);
    }
}
