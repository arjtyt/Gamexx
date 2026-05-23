package com.onesignal.inAppMessages.internal;

import com.onesignal.core.internal.time.ITime;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.inAppMessages.BuildConfig;
import com.onesignal.session.internal.influence.impl.InfluenceConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: InAppMessageRedisplayStats.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0017\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bB\r\u0012\u0006\u0010\f\u001a\u00020\u0007¢\u0006\u0002\u0010\rJ\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u0000J\u0006\u0010%\u001a\u00020\u001bJ\u0006\u0010&\u001a\u00020\nJ\b\u0010'\u001a\u00020(H\u0016R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0015\"\u0004\b\u0019\u0010\u0017R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001cR\u001e\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012¨\u0006*"}, d2 = {"Lcom/onesignal/inAppMessages/internal/InAppMessageRedisplayStats;", "", "displayQuantity", "", "lastDisplayTime", "", InfluenceConstants.TIME, "Lcom/onesignal/core/internal/time/ITime;", "(IJLcom/onesignal/core/internal/time/ITime;)V", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;Lcom/onesignal/core/internal/time/ITime;)V", "_time", "(Lcom/onesignal/core/internal/time/ITime;)V", "displayDelay", "getDisplayDelay", "()J", "setDisplayDelay", "(J)V", "displayLimit", "getDisplayLimit", "()I", "setDisplayLimit", "(I)V", "getDisplayQuantity", "setDisplayQuantity", "isDelayTimeSatisfied", "", "()Z", "<set-?>", "isRedisplayEnabled", "getLastDisplayTime", "setLastDisplayTime", "incrementDisplayQuantity", "", "setDisplayStats", "displayStats", "shouldDisplayAgain", "toJSONObject", "toString", "", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class InAppMessageRedisplayStats {
    public static final Companion Companion = new Companion(null);
    private static final String DISPLAY_DELAY = "delay";
    private static final String DISPLAY_LIMIT = "limit";
    private final ITime _time;
    private long displayDelay;
    private int displayLimit;
    private int displayQuantity;
    private boolean isRedisplayEnabled;
    private long lastDisplayTime;

    public InAppMessageRedisplayStats(ITime _time) {
        Intrinsics.checkNotNullParameter(_time, "_time");
        this._time = _time;
        this.lastDisplayTime = -1L;
        this.displayLimit = 1;
    }

    public final long getLastDisplayTime() {
        return this.lastDisplayTime;
    }

    public final void setLastDisplayTime(long j) {
        this.lastDisplayTime = j;
    }

    public final int getDisplayQuantity() {
        return this.displayQuantity;
    }

    public final void setDisplayQuantity(int i) {
        this.displayQuantity = i;
    }

    public final int getDisplayLimit() {
        return this.displayLimit;
    }

    public final void setDisplayLimit(int i) {
        this.displayLimit = i;
    }

    public final long getDisplayDelay() {
        return this.displayDelay;
    }

    public final void setDisplayDelay(long j) {
        this.displayDelay = j;
    }

    public final boolean isRedisplayEnabled() {
        return this.isRedisplayEnabled;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InAppMessageRedisplayStats(int displayQuantity, long lastDisplayTime, ITime time) {
        this(time);
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
        this.displayQuantity = displayQuantity;
        this.lastDisplayTime = lastDisplayTime;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public InAppMessageRedisplayStats(JSONObject json, ITime time) throws JSONException {
        this(time);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(time, InfluenceConstants.TIME);
        this.isRedisplayEnabled = true;
        Object displayLimit = json.get(DISPLAY_LIMIT);
        Object displayDelay = json.get(DISPLAY_DELAY);
        if (displayLimit instanceof Integer) {
            this.displayLimit = ((Number) displayLimit).intValue();
        }
        if (displayDelay instanceof Long) {
            this.displayDelay = ((Number) displayDelay).longValue();
        } else if (displayDelay instanceof Integer) {
            this.displayDelay = ((Number) displayDelay).intValue();
        }
    }

    public final void setDisplayStats(InAppMessageRedisplayStats displayStats) {
        Intrinsics.checkNotNullParameter(displayStats, "displayStats");
        this.lastDisplayTime = displayStats.lastDisplayTime;
        this.displayQuantity = displayStats.displayQuantity;
    }

    public final void incrementDisplayQuantity() {
        this.displayQuantity++;
    }

    public final boolean shouldDisplayAgain() {
        boolean result = this.displayQuantity < this.displayLimit;
        Logging.debug$default("OSInAppMessage shouldDisplayAgain: " + result, null, 2, null);
        return result;
    }

    public final boolean isDelayTimeSatisfied() {
        if (this.lastDisplayTime < 0) {
            return true;
        }
        long currentTimeInSeconds = this._time.getCurrentTimeMillis() / ((long) 1000);
        long diffInSeconds = currentTimeInSeconds - this.lastDisplayTime;
        Logging.debug$default("OSInAppMessage lastDisplayTime: " + this.lastDisplayTime + " currentTimeInSeconds: " + currentTimeInSeconds + " diffInSeconds: " + diffInSeconds + " displayDelay: " + this.displayDelay, null, 2, null);
        return diffInSeconds >= this.displayDelay;
    }

    public final JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        try {
            json.put(DISPLAY_LIMIT, this.displayLimit);
            json.put(DISPLAY_DELAY, this.displayDelay);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return json;
    }

    public String toString() {
        return "OSInAppMessageDisplayStats{lastDisplayTime=" + this.lastDisplayTime + ", displayQuantity=" + this.displayQuantity + ", displayLimit=" + this.displayLimit + ", displayDelay=" + this.displayDelay + '}';
    }

    /* JADX INFO: compiled from: InAppMessageRedisplayStats.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/onesignal/inAppMessages/internal/InAppMessageRedisplayStats$Companion;", "", "()V", "DISPLAY_DELAY", "", "DISPLAY_LIMIT", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
