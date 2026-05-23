package com.onesignal.session.internal.outcomes.impl;

import com.onesignal.core.BuildConfig;
import com.onesignal.notifications.internal.bundle.impl.NotificationBundleProcessor;
import com.onesignal.session.internal.influence.InfluenceType;
import com.onesignal.session.internal.outcomes.IOutcomeEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: OutcomeEvent.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \"2\u00020\u0001:\u0001\"B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020\u0007H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\n\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006#"}, d2 = {"Lcom/onesignal/session/internal/outcomes/impl/OutcomeEvent;", "Lcom/onesignal/session/internal/outcomes/IOutcomeEvent;", "session", "Lcom/onesignal/session/internal/influence/InfluenceType;", "notificationIds", "Lorg/json/JSONArray;", "name", "", "timestamp", "", "sessionTime", "weight", "", "(Lcom/onesignal/session/internal/influence/InfluenceType;Lorg/json/JSONArray;Ljava/lang/String;JJF)V", "getName", "()Ljava/lang/String;", "getNotificationIds", "()Lorg/json/JSONArray;", "getSession", "()Lcom/onesignal/session/internal/influence/InfluenceType;", "getSessionTime", "()J", "getTimestamp", "getWeight", "()F", "equals", "", NotificationBundleProcessor.PUSH_MINIFIED_BUTTONS_LIST, "", "hashCode", "", "toJSONObject", "Lorg/json/JSONObject;", "toString", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OutcomeEvent implements IOutcomeEvent {
    public static final Companion Companion = new Companion(null);
    private static final String NOTIFICATION_IDS = "notification_ids";
    private static final String OUTCOME_ID = "id";
    private static final String SESSION = "session";
    private static final String SESSION_TIME = "session_time";
    private static final String TIMESTAMP = "timestamp";
    private static final String WEIGHT = "weight";
    private final String name;
    private final JSONArray notificationIds;
    private final InfluenceType session;
    private final long sessionTime;
    private final long timestamp;
    private final float weight;

    public OutcomeEvent(InfluenceType session, JSONArray notificationIds, String name, long timestamp, long sessionTime, float weight) {
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(name, "name");
        this.session = session;
        this.notificationIds = notificationIds;
        this.name = name;
        this.timestamp = timestamp;
        this.sessionTime = sessionTime;
        this.weight = weight;
    }

    @Override // com.onesignal.session.internal.outcomes.IOutcomeEvent
    public InfluenceType getSession() {
        return this.session;
    }

    @Override // com.onesignal.session.internal.outcomes.IOutcomeEvent
    public JSONArray getNotificationIds() {
        return this.notificationIds;
    }

    @Override // com.onesignal.session.internal.outcomes.IOutcomeEvent
    public String getName() {
        return this.name;
    }

    @Override // com.onesignal.session.internal.outcomes.IOutcomeEvent
    public long getTimestamp() {
        return this.timestamp;
    }

    @Override // com.onesignal.session.internal.outcomes.IOutcomeEvent
    public long getSessionTime() {
        return this.sessionTime;
    }

    @Override // com.onesignal.session.internal.outcomes.IOutcomeEvent
    public float getWeight() {
        return this.weight;
    }

    public final JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("session", getSession());
        json.put("notification_ids", getNotificationIds());
        json.put("id", getName());
        json.put("timestamp", getTimestamp());
        json.put("session_time", getSessionTime());
        json.put("weight", Float.valueOf(getWeight()));
        return json;
    }

    public boolean equals(Object o) {
        boolean z;
        if (this == o) {
            return true;
        }
        if (o == null || !Intrinsics.areEqual(getClass(), o.getClass())) {
            return false;
        }
        OutcomeEvent event = (OutcomeEvent) o;
        if (getSession() == event.getSession() && Intrinsics.areEqual(getNotificationIds(), event.getNotificationIds()) && Intrinsics.areEqual(getName(), event.getName()) && getTimestamp() == event.getTimestamp() && getSessionTime() == event.getSessionTime()) {
            if (getWeight() == event.getWeight()) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Object[] a = {getSession(), getNotificationIds(), getName(), Long.valueOf(getTimestamp()), Long.valueOf(getSessionTime()), Float.valueOf(getWeight())};
        int result = 1;
        int length = a.length;
        for (int i = 0; i < length; i++) {
            Object element = a[i];
            result = (result * 31) + (element != null ? element.hashCode() : 0);
        }
        return result;
    }

    public String toString() {
        return "OutcomeEvent{session=" + getSession() + ", notificationIds=" + getNotificationIds() + ", name='" + getName() + "', timestamp=" + getTimestamp() + ", sessionTime=" + getSessionTime() + ", weight=" + getWeight() + '}';
    }

    /* JADX INFO: compiled from: OutcomeEvent.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/onesignal/session/internal/outcomes/impl/OutcomeEvent$Companion;", "", "()V", "NOTIFICATION_IDS", "", "OUTCOME_ID", "SESSION", "SESSION_TIME", "TIMESTAMP", "WEIGHT", "fromOutcomeEventParamstoOutcomeEvent", "Lcom/onesignal/session/internal/outcomes/impl/OutcomeEvent;", "outcomeEventParams", "Lcom/onesignal/session/internal/outcomes/impl/OutcomeEventParams;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0049  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0080  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.onesignal.session.internal.outcomes.impl.OutcomeEvent fromOutcomeEventParamstoOutcomeEvent(com.onesignal.session.internal.outcomes.impl.OutcomeEventParams r13) {
            /*
                r12 = this;
                java.lang.String r0 = "outcomeEventParams"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
                com.onesignal.session.internal.influence.InfluenceType r0 = com.onesignal.session.internal.influence.InfluenceType.UNATTRIBUTED
                r1 = 0
                com.onesignal.session.internal.outcomes.impl.OutcomeSource r2 = r13.getOutcomeSource()
                if (r2 == 0) goto L80
                com.onesignal.session.internal.outcomes.impl.OutcomeSource r2 = r13.getOutcomeSource()
                com.onesignal.session.internal.outcomes.impl.OutcomeSourceBody r3 = r2.getDirectBody()
                if (r3 == 0) goto L49
                com.onesignal.session.internal.outcomes.impl.OutcomeSourceBody r3 = r2.getDirectBody()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                org.json.JSONArray r3 = r3.getNotificationIds()
                if (r3 == 0) goto L49
                com.onesignal.session.internal.outcomes.impl.OutcomeSourceBody r3 = r2.getDirectBody()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                org.json.JSONArray r3 = r3.getNotificationIds()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                int r3 = r3.length()
                if (r3 <= 0) goto L49
                com.onesignal.session.internal.influence.InfluenceType r0 = com.onesignal.session.internal.influence.InfluenceType.DIRECT
                com.onesignal.session.internal.outcomes.impl.OutcomeSourceBody r3 = r2.getDirectBody()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                org.json.JSONArray r1 = r3.getNotificationIds()
                r4 = r0
                r5 = r1
                goto L82
            L49:
                com.onesignal.session.internal.outcomes.impl.OutcomeSourceBody r3 = r2.getIndirectBody()
                if (r3 == 0) goto L80
                com.onesignal.session.internal.outcomes.impl.OutcomeSourceBody r3 = r2.getIndirectBody()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                org.json.JSONArray r3 = r3.getNotificationIds()
                if (r3 == 0) goto L80
                com.onesignal.session.internal.outcomes.impl.OutcomeSourceBody r3 = r2.getIndirectBody()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                org.json.JSONArray r3 = r3.getNotificationIds()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                int r3 = r3.length()
                if (r3 <= 0) goto L80
                com.onesignal.session.internal.influence.InfluenceType r0 = com.onesignal.session.internal.influence.InfluenceType.INDIRECT
                com.onesignal.session.internal.outcomes.impl.OutcomeSourceBody r3 = r2.getIndirectBody()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                org.json.JSONArray r1 = r3.getNotificationIds()
                r4 = r0
                r5 = r1
                goto L82
            L80:
                r4 = r0
                r5 = r1
            L82:
                com.onesignal.session.internal.outcomes.impl.OutcomeEvent r3 = new com.onesignal.session.internal.outcomes.impl.OutcomeEvent
                java.lang.String r6 = r13.getOutcomeId()
                long r7 = r13.getTimestamp()
                long r9 = r13.getSessionTime()
                float r11 = r13.getWeight()
                r3.<init>(r4, r5, r6, r7, r9, r11)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.session.internal.outcomes.impl.OutcomeEvent.Companion.fromOutcomeEventParamstoOutcomeEvent(com.onesignal.session.internal.outcomes.impl.OutcomeEventParams):com.onesignal.session.internal.outcomes.impl.OutcomeEvent");
        }
    }
}
