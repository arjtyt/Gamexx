package com.onesignal.common.consistency;

import com.onesignal.common.consistency.enums.IamFetchRywTokenKey;
import com.onesignal.common.consistency.models.ICondition;
import com.onesignal.common.consistency.models.IConsistencyKeyEnum;
import com.onesignal.core.BuildConfig;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IamFetchReadyCondition.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J,\u0010\b\u001a\u0004\u0018\u00010\t2 \u0010\n\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000b0\u000bH\u0016J(\u0010\r\u001a\u00020\u000e2\u001e\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\t0\u000b0\u000bH\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/onesignal/common/consistency/IamFetchReadyCondition;", "Lcom/onesignal/common/consistency/models/ICondition;", "key", "", "(Ljava/lang/String;)V", OutcomeConstants.OUTCOME_ID, "getId", "()Ljava/lang/String;", "getRywData", "Lcom/onesignal/common/consistency/RywData;", "indexedTokens", "", "Lcom/onesignal/common/consistency/models/IConsistencyKeyEnum;", "isMet", "", "Companion", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class IamFetchReadyCondition implements ICondition {
    public static final Companion Companion = new Companion(null);
    public static final String ID = "IamFetchReadyCondition";
    private final String key;

    public IamFetchReadyCondition(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.key = key;
    }

    /* JADX INFO: compiled from: IamFetchReadyCondition.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onesignal/common/consistency/IamFetchReadyCondition$Companion;", "", "()V", "ID", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.onesignal.common.consistency.models.ICondition
    public String getId() {
        return ID;
    }

    @Override // com.onesignal.common.consistency.models.ICondition
    public boolean isMet(Map<String, ? extends Map<IConsistencyKeyEnum, RywData>> map) {
        Intrinsics.checkNotNullParameter(map, "indexedTokens");
        Map<IConsistencyKeyEnum, RywData> map2 = map.get(this.key);
        return (map2 == null || map2.get(IamFetchRywTokenKey.USER) == null) ? false : true;
    }

    @Override // com.onesignal.common.consistency.models.ICondition
    public RywData getRywData(Map<String, ? extends Map<IConsistencyKeyEnum, RywData>> map) {
        Intrinsics.checkNotNullParameter(map, "indexedTokens");
        Map<IConsistencyKeyEnum, RywData> map2 = map.get(this.key);
        Object maxElem$iv = null;
        if (map2 == null) {
            return null;
        }
        Iterable $this$maxByOrNull$iv = CollectionsKt.listOfNotNull(new RywData[]{map2.get(IamFetchRywTokenKey.USER), map2.get(IamFetchRywTokenKey.SUBSCRIPTION)});
        Iterator iterator$iv = $this$maxByOrNull$iv.iterator();
        if (iterator$iv.hasNext()) {
            maxElem$iv = iterator$iv.next();
            if (iterator$iv.hasNext()) {
                RywData it = (RywData) maxElem$iv;
                Comparable rywToken = it.getRywToken();
                if (rywToken == null) {
                    rywToken = "";
                }
                Comparable maxValue$iv = rywToken;
                do {
                    Object e$iv = iterator$iv.next();
                    RywData it2 = (RywData) e$iv;
                    String rywToken2 = it2.getRywToken();
                    if (rywToken2 == null) {
                        rywToken2 = "";
                    }
                    String str = rywToken2;
                    if (maxValue$iv.compareTo(str) < 0) {
                        maxElem$iv = e$iv;
                        maxValue$iv = str;
                    }
                } while (iterator$iv.hasNext());
            }
        }
        return (RywData) maxElem$iv;
    }
}
