package com.onesignal.common.consistency;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RywData.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\bJ$\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/onesignal/common/consistency/RywData;", "", "rywToken", "", "rywDelay", "", "(Ljava/lang/String;Ljava/lang/Long;)V", "getRywDelay", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRywToken", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Long;)Lcom/onesignal/common/consistency/RywData;", "equals", "", "other", "hashCode", "", "toString", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class RywData {
    private final Long rywDelay;
    private final String rywToken;

    public static /* synthetic */ RywData copy$default(RywData rywData, String str, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rywData.rywToken;
        }
        if ((i & 2) != 0) {
            l = rywData.rywDelay;
        }
        return rywData.copy(str, l);
    }

    public final String component1() {
        return this.rywToken;
    }

    public final Long component2() {
        return this.rywDelay;
    }

    public final RywData copy(String str, Long l) {
        Intrinsics.checkNotNullParameter(str, "rywToken");
        return new RywData(str, l);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RywData)) {
            return false;
        }
        RywData rywData = (RywData) obj;
        return Intrinsics.areEqual(this.rywToken, rywData.rywToken) && Intrinsics.areEqual(this.rywDelay, rywData.rywDelay);
    }

    public int hashCode() {
        return (this.rywToken.hashCode() * 31) + (this.rywDelay == null ? 0 : this.rywDelay.hashCode());
    }

    public String toString() {
        return "RywData(rywToken=" + this.rywToken + ", rywDelay=" + this.rywDelay + ')';
    }

    public RywData(String rywToken, Long rywDelay) {
        Intrinsics.checkNotNullParameter(rywToken, "rywToken");
        this.rywToken = rywToken;
        this.rywDelay = rywDelay;
    }

    public final Long getRywDelay() {
        return this.rywDelay;
    }

    public final String getRywToken() {
        return this.rywToken;
    }
}
