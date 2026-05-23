package com.onesignal.session.internal.influence;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: InfluenceType.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lcom/onesignal/session/internal/influence/InfluenceType;", "", "(Ljava/lang/String;I)V", "isAttributed", "", "isDirect", "isDisabled", "isIndirect", "isUnattributed", "DIRECT", "INDIRECT", "UNATTRIBUTED", "DISABLED", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum InfluenceType {
    DIRECT,
    INDIRECT,
    UNATTRIBUTED,
    DISABLED;

    public static final Companion Companion = new Companion(null);

    @JvmStatic
    public static final InfluenceType fromString(String str) {
        return Companion.fromString(str);
    }

    public final boolean isAttributed() {
        return isDirect() || isIndirect();
    }

    public final boolean isDirect() {
        return this == DIRECT;
    }

    public final boolean isIndirect() {
        return this == INDIRECT;
    }

    public final boolean isUnattributed() {
        return this == UNATTRIBUTED;
    }

    public final boolean isDisabled() {
        return this == DISABLED;
    }

    /* JADX INFO: compiled from: InfluenceType.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/onesignal/session/internal/influence/InfluenceType$Companion;", "", "()V", "fromString", "Lcom/onesignal/session/internal/influence/InfluenceType;", "value", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
        @kotlin.jvm.JvmStatic
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.onesignal.session.internal.influence.InfluenceType fromString(java.lang.String r10) {
            /*
                r9 = this;
                if (r10 == 0) goto L27
                r0 = r10
                r1 = 0
                com.onesignal.session.internal.influence.InfluenceType[] r2 = com.onesignal.session.internal.influence.InfluenceType.values()
                int r3 = r2.length
                int r3 = r3 + (-1)
                if (r3 < 0) goto L24
            Ld:
                int r4 = r3 + (-1)
                r3 = r2[r3]
                r5 = r3
                r6 = 0
                java.lang.String r7 = r5.name()
                r8 = 1
                boolean r5 = kotlin.text.StringsKt.equals(r7, r10, r8)
                if (r5 == 0) goto L1f
                goto L25
            L1f:
                if (r4 >= 0) goto L22
                goto L24
            L22:
                r3 = r4
                goto Ld
            L24:
                r3 = 0
            L25:
                if (r3 != 0) goto L29
            L27:
                com.onesignal.session.internal.influence.InfluenceType r3 = com.onesignal.session.internal.influence.InfluenceType.UNATTRIBUTED
            L29:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.session.internal.influence.InfluenceType.Companion.fromString(java.lang.String):com.onesignal.session.internal.influence.InfluenceType");
        }
    }
}
