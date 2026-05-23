package com.onesignal.session.internal.session;

import com.onesignal.common.modeling.Model;
import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionModel.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\r8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u0007\"\u0004\b\u001a\u0010\t¨\u0006\u001b"}, d2 = {"Lcom/onesignal/session/internal/session/SessionModel;", "Lcom/onesignal/common/modeling/Model;", "()V", "value", "", "activeDuration", "getActiveDuration", "()J", "setActiveDuration", "(J)V", "focusTime", "getFocusTime", "setFocusTime", "", "isValid", "()Z", "setValid", "(Z)V", "", "sessionId", "getSessionId", "()Ljava/lang/String;", "setSessionId", "(Ljava/lang/String;)V", "startTime", "getStartTime", "setStartTime", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SessionModel extends Model {
    public SessionModel() {
        super(null, null, 3, null);
    }

    public final String getSessionId() {
        return Model.getStringProperty$default(this, "sessionId", null, 2, null);
    }

    public final void setSessionId(String value) throws Throwable {
        Intrinsics.checkNotNullParameter(value, "value");
        Model.setStringProperty$default(this, "sessionId", value, null, false, 12, null);
    }

    public final boolean isValid() {
        return getBooleanProperty("isValid", new Function0<Boolean>() { // from class: com.onesignal.session.internal.session.SessionModel.isValid.2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m87invoke() {
                return false;
            }
        });
    }

    public final void setValid(boolean value) throws Throwable {
        Model.setBooleanProperty$default(this, "isValid", value, null, false, 12, null);
    }

    public final long getStartTime() {
        return getLongProperty("startTime", new Function0<Long>() { // from class: com.onesignal.session.internal.session.SessionModel$startTime$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m88invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        });
    }

    public final void setStartTime(long value) throws Throwable {
        Model.setLongProperty$default(this, "startTime", value, null, false, 12, null);
    }

    public final long getFocusTime() {
        return getLongProperty("focusTime", new Function0<Long>() { // from class: com.onesignal.session.internal.session.SessionModel$focusTime$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m86invoke() {
                return Long.valueOf(System.currentTimeMillis());
            }
        });
    }

    public final void setFocusTime(long value) throws Throwable {
        Model.setLongProperty$default(this, "focusTime", value, null, false, 12, null);
    }

    public final long getActiveDuration() {
        return getLongProperty("activeDuration", new Function0<Long>() { // from class: com.onesignal.session.internal.session.SessionModel$activeDuration$2
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Long m85invoke() {
                return 0L;
            }
        });
    }

    public final void setActiveDuration(long value) throws Throwable {
        Model.setLongProperty$default(this, "activeDuration", value, null, false, 12, null);
    }
}
