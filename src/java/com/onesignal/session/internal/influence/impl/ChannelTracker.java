package com.onesignal.session.internal.influence.impl;

import com.onesignal.core.BuildConfig;
import com.onesignal.core.internal.time.ITime;
import com.onesignal.debug.internal.logging.Logging;
import com.onesignal.session.internal.influence.Influence;
import com.onesignal.session.internal.influence.InfluenceType;
import com.onesignal.session.internal.outcomes.impl.OutcomeConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ChannelTracker.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b \u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u00100\u001a\u00020(2\b\u00101\u001a\u0004\u0018\u000102H\u0096\u0002J\u0012\u00103\u001a\u00020\u001c2\b\u00104\u001a\u0004\u0018\u00010\u0014H&J\b\u00105\u001a\u00020\bH\u0016J\b\u00106\u001a\u000207H&J\b\u00108\u001a\u000207H\u0016J\u0010\u00109\u001a\u0002072\u0006\u0010:\u001a\u00020\u001cH&J\u0012\u0010;\u001a\u0002072\b\u00104\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010<\u001a\u00020\u0014H\u0016R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\nR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010)R\u0014\u0010*\u001a\u00020(8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010)R\u0014\u0010+\u001a\u00020(8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010)R\u0014\u0010,\u001a\u00020\u001c8fX¦\u0004¢\u0006\u0006\u001a\u0004\b-\u0010\u001eR\u0014\u0010.\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u001eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/onesignal/session/internal/influence/impl/ChannelTracker;", "Lcom/onesignal/session/internal/influence/impl/IChannelTracker;", "dataRepository", "Lcom/onesignal/session/internal/influence/impl/InfluenceDataRepository;", "timeProvider", "Lcom/onesignal/core/internal/time/ITime;", "(Lcom/onesignal/session/internal/influence/impl/InfluenceDataRepository;Lcom/onesignal/core/internal/time/ITime;)V", "channelLimit", "", "getChannelLimit", "()I", "currentSessionInfluence", "Lcom/onesignal/session/internal/influence/Influence;", "getCurrentSessionInfluence", "()Lcom/onesignal/session/internal/influence/Influence;", "getDataRepository", "()Lcom/onesignal/session/internal/influence/impl/InfluenceDataRepository;", "setDataRepository", "(Lcom/onesignal/session/internal/influence/impl/InfluenceDataRepository;)V", "directId", "", "getDirectId", "()Ljava/lang/String;", "setDirectId", "(Ljava/lang/String;)V", "indirectAttributionWindow", "getIndirectAttributionWindow", "indirectIds", "Lorg/json/JSONArray;", "getIndirectIds", "()Lorg/json/JSONArray;", "setIndirectIds", "(Lorg/json/JSONArray;)V", "influenceType", "Lcom/onesignal/session/internal/influence/InfluenceType;", "getInfluenceType", "()Lcom/onesignal/session/internal/influence/InfluenceType;", "setInfluenceType", "(Lcom/onesignal/session/internal/influence/InfluenceType;)V", "isDirectSessionEnabled", "", "()Z", "isIndirectSessionEnabled", "isUnattributedSessionEnabled", "lastChannelObjects", "getLastChannelObjects", "lastReceivedIds", "getLastReceivedIds", "equals", "other", "", "getLastChannelObjectsReceivedByNewId", OutcomeConstants.OUTCOME_ID, "hashCode", "initInfluencedTypeFromCache", "", "resetAndInitInfluence", "saveChannelObjects", "channelObjects", "saveLastId", "toString", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public abstract class ChannelTracker implements IChannelTracker {
    private InfluenceDataRepository dataRepository;
    private String directId;
    private JSONArray indirectIds;
    private InfluenceType influenceType;
    private ITime timeProvider;

    public abstract int getChannelLimit();

    public abstract int getIndirectAttributionWindow();

    public abstract JSONArray getLastChannelObjects() throws JSONException;

    public abstract JSONArray getLastChannelObjectsReceivedByNewId(String str);

    public abstract void initInfluencedTypeFromCache();

    public abstract void saveChannelObjects(JSONArray jSONArray);

    public ChannelTracker(InfluenceDataRepository dataRepository, ITime timeProvider) {
        Intrinsics.checkNotNullParameter(dataRepository, "dataRepository");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        this.dataRepository = dataRepository;
        this.timeProvider = timeProvider;
    }

    protected final InfluenceDataRepository getDataRepository() {
        return this.dataRepository;
    }

    protected final void setDataRepository(InfluenceDataRepository influenceDataRepository) {
        Intrinsics.checkNotNullParameter(influenceDataRepository, "<set-?>");
        this.dataRepository = influenceDataRepository;
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public InfluenceType getInfluenceType() {
        return this.influenceType;
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public void setInfluenceType(InfluenceType influenceType) {
        this.influenceType = influenceType;
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public JSONArray getIndirectIds() {
        return this.indirectIds;
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public void setIndirectIds(JSONArray jSONArray) {
        this.indirectIds = jSONArray;
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public String getDirectId() {
        return this.directId;
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public void setDirectId(String str) {
        this.directId = str;
    }

    private final boolean isDirectSessionEnabled() {
        return this.dataRepository.isDirectInfluenceEnabled();
    }

    private final boolean isIndirectSessionEnabled() {
        return this.dataRepository.isIndirectInfluenceEnabled();
    }

    private final boolean isUnattributedSessionEnabled() {
        return this.dataRepository.isUnattributedInfluenceEnabled();
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public Influence getCurrentSessionInfluence() {
        Influence sessionInfluence = new Influence(getChannelType(), InfluenceType.DISABLED, null);
        if (getInfluenceType() == null) {
            initInfluencedTypeFromCache();
        }
        InfluenceType currentInfluenceType = getInfluenceType();
        if (currentInfluenceType == null) {
            currentInfluenceType = InfluenceType.DISABLED;
        }
        if (currentInfluenceType.isDirect()) {
            if (isDirectSessionEnabled()) {
                sessionInfluence.setIds(new JSONArray().put(getDirectId()));
                sessionInfluence.setInfluenceType(InfluenceType.DIRECT);
            }
        } else if (currentInfluenceType.isIndirect()) {
            if (isIndirectSessionEnabled()) {
                sessionInfluence.setIds(getIndirectIds());
                sessionInfluence.setInfluenceType(InfluenceType.INDIRECT);
            }
        } else if (isUnattributedSessionEnabled()) {
            sessionInfluence.setInfluenceType(InfluenceType.UNATTRIBUTED);
        }
        return sessionInfluence;
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public JSONArray getLastReceivedIds() {
        JSONArray ids = new JSONArray();
        try {
            JSONArray lastChannelObjectReceived = getLastChannelObjects();
            Logging.debug$default("ChannelTracker.getLastReceivedIds: lastChannelObjectReceived: " + lastChannelObjectReceived, null, 2, null);
            long attributionWindow = ((long) (getIndirectAttributionWindow() * 60)) * 1000;
            long currentTime = this.timeProvider.getCurrentTimeMillis();
            int length = lastChannelObjectReceived.length();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = lastChannelObjectReceived.getJSONObject(i);
                long time = jsonObject.getLong(InfluenceConstants.TIME);
                long difference = currentTime - time;
                if (difference <= attributionWindow) {
                    String id = jsonObject.getString(getIdTag());
                    ids.put(id);
                }
            }
        } catch (JSONException exception) {
            Logging.error("ChannelTracker.getLastReceivedIds: Generating tracker getLastReceivedIds JSONObject ", exception);
        }
        return ids;
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public void resetAndInitInfluence() {
        setDirectId(null);
        setIndirectIds(getLastReceivedIds());
        JSONArray indirectIds = getIndirectIds();
        setInfluenceType((indirectIds != null ? indirectIds.length() : 0) > 0 ? InfluenceType.INDIRECT : InfluenceType.UNATTRIBUTED);
        cacheState();
        Logging.debug$default("ChannelTracker.resetAndInitInfluence: " + getIdTag() + " finish with influenceType: " + getInfluenceType(), null, 2, null);
    }

    @Override // com.onesignal.session.internal.influence.impl.IChannelTracker
    public void saveLastId(String id) {
        Logging.debug$default("ChannelTracker.saveLastId(id: " + id + "): idTag=" + getIdTag(), null, 2, null);
        if (id != null) {
            if (id.length() == 0) {
                return;
            }
            JSONArray lastChannelObjectsReceived = getLastChannelObjectsReceivedByNewId(id);
            Logging.debug$default("ChannelTracker.saveLastId: for " + getIdTag() + " saveLastId with lastChannelObjectsReceived: " + lastChannelObjectsReceived, null, 2, null);
            try {
                ITime $this$saveLastId_u24lambda_u2d3 = this.timeProvider;
                JSONObject newInfluenceId = new JSONObject().put(getIdTag(), id).put(InfluenceConstants.TIME, $this$saveLastId_u24lambda_u2d3.getCurrentTimeMillis());
                lastChannelObjectsReceived.put(newInfluenceId);
                JSONArray channelObjectToSave = lastChannelObjectsReceived;
                if (lastChannelObjectsReceived.length() > getChannelLimit()) {
                    int lengthDifference = lastChannelObjectsReceived.length() - getChannelLimit();
                    channelObjectToSave = new JSONArray();
                    int length = lastChannelObjectsReceived.length();
                    for (int i = lengthDifference; i < length; i++) {
                        try {
                            channelObjectToSave.put(lastChannelObjectsReceived.get(i));
                        } catch (JSONException exception) {
                            Logging.error("ChannelTracker.saveLastId: Generating tracker lastChannelObjectsReceived get JSONObject ", exception);
                        }
                    }
                }
                Logging.debug$default("ChannelTracker.saveLastId: for " + getIdTag() + " with channelObjectToSave: " + channelObjectToSave, null, 2, null);
                saveChannelObjects(channelObjectToSave);
            } catch (JSONException exception2) {
                Logging.error("ChannelTracker.saveLastId: Generating tracker newInfluenceId JSONObject ", exception2);
            }
        }
    }

    public String toString() {
        return "ChannelTracker{tag=" + getIdTag() + ", influenceType=" + getInfluenceType() + ", indirectIds=" + getIndirectIds() + ", directId=" + getDirectId() + '}';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        ChannelTracker tracker = (ChannelTracker) other;
        if (getInfluenceType() == tracker.getInfluenceType() && Intrinsics.areEqual(tracker.getIdTag(), getIdTag())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        InfluenceType influenceType = getInfluenceType();
        int result = influenceType != null ? influenceType.hashCode() : 0;
        return (result * 31) + getIdTag().hashCode();
    }
}
