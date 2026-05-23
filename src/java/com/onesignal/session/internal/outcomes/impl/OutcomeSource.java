package com.onesignal.session.internal.outcomes.impl;

import com.onesignal.core.BuildConfig;
import kotlin.Metadata;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: OutcomeSource.kt */
/* JADX INFO: loaded from: /storage/emulated/0/Documents/jadec/sources/com.gamex.gaming_app/dex-files/1.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\b\u001a\u00020\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003J\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0010"}, d2 = {"Lcom/onesignal/session/internal/outcomes/impl/OutcomeSource;", "", "directBody", "Lcom/onesignal/session/internal/outcomes/impl/OutcomeSourceBody;", "indirectBody", "(Lcom/onesignal/session/internal/outcomes/impl/OutcomeSourceBody;Lcom/onesignal/session/internal/outcomes/impl/OutcomeSourceBody;)V", "getDirectBody", "()Lcom/onesignal/session/internal/outcomes/impl/OutcomeSourceBody;", "setDirectBody", "(Lcom/onesignal/session/internal/outcomes/impl/OutcomeSourceBody;)V", "getIndirectBody", "setIndirectBody", "toJSONObject", "Lorg/json/JSONObject;", "toString", "", BuildConfig.LIBRARY_PACKAGE_NAME}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class OutcomeSource {
    private OutcomeSourceBody directBody;
    private OutcomeSourceBody indirectBody;

    public OutcomeSource(OutcomeSourceBody directBody, OutcomeSourceBody indirectBody) {
        this.directBody = directBody;
        this.indirectBody = indirectBody;
    }

    public final OutcomeSourceBody getDirectBody() {
        return this.directBody;
    }

    /* JADX INFO: renamed from: setDirectBody, reason: collision with other method in class */
    public final void m83setDirectBody(OutcomeSourceBody outcomeSourceBody) {
        this.directBody = outcomeSourceBody;
    }

    public final OutcomeSourceBody getIndirectBody() {
        return this.indirectBody;
    }

    /* JADX INFO: renamed from: setIndirectBody, reason: collision with other method in class */
    public final void m84setIndirectBody(OutcomeSourceBody outcomeSourceBody) {
        this.indirectBody = outcomeSourceBody;
    }

    public final JSONObject toJSONObject() throws JSONException {
        JSONObject json = new JSONObject();
        OutcomeSourceBody it = this.directBody;
        if (it != null) {
            json.put("direct", it.toJSONObject());
        }
        OutcomeSourceBody it2 = this.indirectBody;
        if (it2 != null) {
            json.put(OutcomeConstants.INDIRECT, it2.toJSONObject());
        }
        return json;
    }

    public final OutcomeSource setDirectBody(OutcomeSourceBody directBody) {
        OutcomeSource $this$setDirectBody_u24lambda_u2d2 = this;
        $this$setDirectBody_u24lambda_u2d2.directBody = directBody;
        return this;
    }

    public final OutcomeSource setIndirectBody(OutcomeSourceBody indirectBody) {
        OutcomeSource $this$setIndirectBody_u24lambda_u2d3 = this;
        $this$setIndirectBody_u24lambda_u2d3.indirectBody = indirectBody;
        return this;
    }

    public String toString() {
        return "OutcomeSource{directBody=" + this.directBody + ", indirectBody=" + this.indirectBody + '}';
    }
}
