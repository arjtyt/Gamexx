package com.onesignal.user.internal.backend;

import com.onesignal.core.BuildConfig;
import java.math.BigDecimal;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PropertiesDeltasObject.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B;\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/onesignal/user/internal/backend/PropertiesDeltasObject;", "", "sessionTime", "", "sessionCount", "", "amountSpent", "Ljava/math/BigDecimal;", "purchases", "", "Lcom/onesignal/user/internal/backend/PurchaseObject;", "(Ljava/lang/Long;Ljava/lang/Integer;Ljava/math/BigDecimal;Ljava/util/List;)V", "getAmountSpent", "()Ljava/math/BigDecimal;", "hasAtLeastOnePropertySet", "", "getHasAtLeastOnePropertySet", "()Z", "getPurchases", "()Ljava/util/List;", "getSessionCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSessionTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PropertiesDeltasObject {
    private final BigDecimal amountSpent;
    private final List<PurchaseObject> purchases;
    private final Integer sessionCount;
    private final Long sessionTime;

    public PropertiesDeltasObject() {
        this(null, null, null, null, 15, null);
    }

    public PropertiesDeltasObject(Long sessionTime, Integer sessionCount, BigDecimal amountSpent, List<PurchaseObject> list) {
        this.sessionTime = sessionTime;
        this.sessionCount = sessionCount;
        this.amountSpent = amountSpent;
        this.purchases = list;
    }

    public /* synthetic */ PropertiesDeltasObject(Long l, Integer num, BigDecimal bigDecimal, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : l, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : bigDecimal, (i & 8) != 0 ? null : list);
    }

    public final Long getSessionTime() {
        return this.sessionTime;
    }

    public final Integer getSessionCount() {
        return this.sessionCount;
    }

    public final BigDecimal getAmountSpent() {
        return this.amountSpent;
    }

    public final List<PurchaseObject> getPurchases() {
        return this.purchases;
    }

    public final boolean getHasAtLeastOnePropertySet() {
        return (this.sessionTime == null && this.sessionCount == null && this.amountSpent == null && this.purchases == null) ? false : true;
    }
}
