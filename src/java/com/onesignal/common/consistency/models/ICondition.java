package com.onesignal.common.consistency.models;

import com.onesignal.common.consistency.RywData;
import com.onesignal.core.BuildConfig;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: compiled from: ICondition.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/0.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J,\u0010\u0006\u001a\u0004\u0018\u00010\u00072 \u0010\b\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t0\tH&J(\u0010\u000b\u001a\u00020\f2\u001e\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00070\t0\tH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\r"}, d2 = {"Lcom/onesignal/common/consistency/models/ICondition;", "", OutcomeConstants.OUTCOME_ID, "", "getId", "()Ljava/lang/String;", "getRywData", "Lcom/onesignal/common/consistency/RywData;", "indexedTokens", "", "Lcom/onesignal/common/consistency/models/IConsistencyKeyEnum;", "isMet", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface ICondition {
    String getId();

    RywData getRywData(Map<String, ? extends Map<IConsistencyKeyEnum, RywData>> map);

    boolean isMet(Map<String, ? extends Map<IConsistencyKeyEnum, RywData>> map);
}
