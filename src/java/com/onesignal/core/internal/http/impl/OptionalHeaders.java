package com.onesignal.core.internal.http.impl;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OptionalHeaders.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0011J>\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/onesignal/core/internal/http/impl/OptionalHeaders;", "", "cacheKey", "", "rywToken", "retryCount", "", "sessionDuration", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;)V", "getCacheKey", "()Ljava/lang/String;", "getRetryCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRywToken", "getSessionDuration", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;)Lcom/onesignal/core/internal/http/impl/OptionalHeaders;", "equals", "", "other", "hashCode", "toString", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OptionalHeaders {
    private final String cacheKey;
    private final Integer retryCount;
    private final String rywToken;
    private final Long sessionDuration;

    public OptionalHeaders() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ OptionalHeaders copy$default(OptionalHeaders optionalHeaders, String str, String str2, Integer num, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = optionalHeaders.cacheKey;
        }
        if ((i & 2) != 0) {
            str2 = optionalHeaders.rywToken;
        }
        if ((i & 4) != 0) {
            num = optionalHeaders.retryCount;
        }
        if ((i & 8) != 0) {
            l = optionalHeaders.sessionDuration;
        }
        return optionalHeaders.copy(str, str2, num, l);
    }

    public final String component1() {
        return this.cacheKey;
    }

    public final String component2() {
        return this.rywToken;
    }

    public final Integer component3() {
        return this.retryCount;
    }

    public final Long component4() {
        return this.sessionDuration;
    }

    public final OptionalHeaders copy(String str, String str2, Integer num, Long l) {
        return new OptionalHeaders(str, str2, num, l);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OptionalHeaders)) {
            return false;
        }
        OptionalHeaders optionalHeaders = (OptionalHeaders) obj;
        return Intrinsics.areEqual(this.cacheKey, optionalHeaders.cacheKey) && Intrinsics.areEqual(this.rywToken, optionalHeaders.rywToken) && Intrinsics.areEqual(this.retryCount, optionalHeaders.retryCount) && Intrinsics.areEqual(this.sessionDuration, optionalHeaders.sessionDuration);
    }

    public int hashCode() {
        return ((((((this.cacheKey == null ? 0 : this.cacheKey.hashCode()) * 31) + (this.rywToken == null ? 0 : this.rywToken.hashCode())) * 31) + (this.retryCount == null ? 0 : this.retryCount.hashCode())) * 31) + (this.sessionDuration != null ? this.sessionDuration.hashCode() : 0);
    }

    public String toString() {
        return "OptionalHeaders(cacheKey=" + this.cacheKey + ", rywToken=" + this.rywToken + ", retryCount=" + this.retryCount + ", sessionDuration=" + this.sessionDuration + ')';
    }

    public OptionalHeaders(String cacheKey, String rywToken, Integer retryCount, Long sessionDuration) {
        this.cacheKey = cacheKey;
        this.rywToken = rywToken;
        this.retryCount = retryCount;
        this.sessionDuration = sessionDuration;
    }

    public /* synthetic */ OptionalHeaders(String str, String str2, Integer num, Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : num, (i & 8) != 0 ? null : l);
    }

    public final String getCacheKey() {
        return this.cacheKey;
    }

    public final String getRywToken() {
        return this.rywToken;
    }

    public final Integer getRetryCount() {
        return this.retryCount;
    }

    public final Long getSessionDuration() {
        return this.sessionDuration;
    }
}
