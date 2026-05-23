package com.onesignal.session.internal.influence;

import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.database.impl.OneSignalDbContract;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InfluenceChannel.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0003J\b\u0010\b\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/onesignal/session/internal/influence/InfluenceChannel;", "", "nameValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "equalsName", "", "otherName", "toString", "IAM", "NOTIFICATION", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public enum InfluenceChannel {
    IAM("iam"),
    NOTIFICATION(OneSignalDbContract.NotificationTable.TABLE_NAME);

    public static final Companion Companion = new Companion(null);
    private final String nameValue;

    @JvmStatic
    public static final InfluenceChannel fromString(String str) {
        return Companion.fromString(str);
    }

    InfluenceChannel(String nameValue) {
        this.nameValue = nameValue;
    }

    public final boolean equalsName(String otherName) {
        Intrinsics.checkNotNullParameter(otherName, "otherName");
        return Intrinsics.areEqual(this.nameValue, otherName);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.nameValue;
    }

    /* JADX INFO: compiled from: InfluenceChannel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/onesignal/session/internal/influence/InfluenceChannel$Companion;", "", "()V", "fromString", "Lcom/onesignal/session/internal/influence/InfluenceChannel;", "value", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
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
        public final com.onesignal.session.internal.influence.InfluenceChannel fromString(java.lang.String r8) {
            /*
                r7 = this;
                if (r8 == 0) goto L22
                r0 = r8
                r1 = 0
                com.onesignal.session.internal.influence.InfluenceChannel[] r2 = com.onesignal.session.internal.influence.InfluenceChannel.values()
                int r3 = r2.length
                int r3 = r3 + (-1)
                if (r3 < 0) goto L1f
            Ld:
                int r4 = r3 + (-1)
                r3 = r2[r3]
                r5 = r3
                r6 = 0
                boolean r5 = r5.equalsName(r8)
                if (r5 == 0) goto L1a
                goto L20
            L1a:
                if (r4 >= 0) goto L1d
                goto L1f
            L1d:
                r3 = r4
                goto Ld
            L1f:
                r3 = 0
            L20:
                if (r3 != 0) goto L24
            L22:
                com.onesignal.session.internal.influence.InfluenceChannel r3 = com.onesignal.session.internal.influence.InfluenceChannel.NOTIFICATION
            L24:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onesignal.session.internal.influence.InfluenceChannel.Companion.fromString(java.lang.String):com.onesignal.session.internal.influence.InfluenceChannel");
        }
    }
}
